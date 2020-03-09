package com.aynu.data.common.sessionFilter;

import com.alibaba.fastjson.JSONObject;
import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.common.global.Global;
import com.aynu.data.common.redis.RedisDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

	Logger log = Logger.getLogger(SessionFilter.class);

	/**
	 * 保存登录地址信息的Session键值
	 */
	private static final String FILTER_REQUEST = "@@filter_request";


	/**
	 * session命名
	 */
	public static final String SESSION_NAME = "sessionId";

	/**
	 * 用户对象存储redis的路径
	 */
	public static final String USERREDISKEY = "account:manager:";

	/**
	 * 不进行登录验证的url
	 */
	private static String[] excludeUrls = null;

	/**
	 * 不进行登录验证的url资源
	 */
	private static String[] excludeStaticFiles = null;

	/**
	 * redis对象
	 */
	@Autowired
	private static RedisDao redisDao;

	@Override
	public void init(FilterConfig config) {

		excludeUrls = "/labelData/login,/labelData/logout".split(",");
		excludeStaticFiles = ".html,.css,.js,.gif,.png,.jpg,.ico,.swf,.eot,.woff,.ttf,.pdf,.xls,.csv,.xlsx,/swagger-resources/configuration/ui".split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		String requestUrl = httpRequest.getRequestURI();

		// 保证该过滤器在一次请求中只被调用一次
		if (request.getAttribute(FILTER_REQUEST) != null) {
			chain.doFilter(request, response);
		} else {
			// 设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTER_REQUEST, Boolean.TRUE);
			String account = JwtUtil.getSubject(httpRequest, Global.jwtTokenCookieName, Global.signingKey);
			// 用户未登录, 且当前URI资源需要登录才能访问
			//未登录，访问的是需被拦截的
			if ((account == null && !this.isExcludeUrl(requestUrl)
					&& !this.isExcludeStaticFile(requestUrl, httpRequest)) || requestUrl.contains("swagger")) {

				ResponseEntity responseEntity = new ResponseEntity();
				responseEntity.setStatus(ConstantMsgUtil.getSessionTimeOutStatus());
				responseEntity.setMsg("请登录后重试");
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.setContentType("application/json; charset=utf-8");
				httpResponse.setDateHeader("Expires", 0); // 在代理服务器端防止缓冲
				PrintWriter out = httpResponse.getWriter();
				out.print(JSONObject.toJSONString(responseEntity));// session失效
				out.flush();

			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 获取Session中的InnerUser对象
	 * 
	 * @param request
	 */
	public static UserDO getSessionInnerUser(HttpServletRequest request) {
		UserDO UserInfoDO = null;
		try {
			// 获取cookie中的sid
			Cookie cookieByName = getCookieByName(request, SESSION_NAME);
			if (cookieByName == null) {
				return null;
			}
			if (redisDao == null) {
				WebApplicationContext wct = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext());
				redisDao = (RedisDao) wct.getBean("redisDao");
			}
			Object object = redisDao.vGet(USERREDISKEY + cookieByName.getValue());
			if (object != null) {
				UserInfoDO = (UserDO) object;
				redisDao.vSetUpdate(USERREDISKEY + cookieByName.getValue(), (long) (3 * 60 * 60));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserInfoDO;
	}

	/**
	 * 保存Session信息
	 * 
	 * @param request
	 */
	public static void saveSessionInnerUser(HttpServletRequest request, HttpServletResponse response,
                                            UserDO userInfoDO) throws Exception {
		// 设置cookie
		String sessionId = request.getSession().getId();
		Cookie cookie = new Cookie(SessionFilter.SESSION_NAME, sessionId);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		if (redisDao == null) {
			WebApplicationContext wct = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext());
			redisDao = (RedisDao) wct.getBean("redisDao");
		}
		redisDao.vSet(SessionFilter.USERREDISKEY + sessionId, userInfoDO, Long.valueOf(3 * 60 * 60));
	}

	/**
	 * 清除Session信息
	 */
	public static void removeSessionInnerUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (redisDao == null) {
				WebApplicationContext wct = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession().getServletContext());
				redisDao = (RedisDao) wct.getBean("redisDao");
			}
			Cookie cookie = getCookieByName(request, SESSION_NAME);
			if (cookie != null) {
				// 从cookie里获取sessionid，并到redis中销毁
				// byte[] key = SerializationUtils.serialize(cookie.getValue());
				// 立即销毁session
				redisDao.delete(USERREDISKEY + cookie.getValue());
				request.getSession().invalidate();
				// 立即销毁cookie
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断请求url是否为过滤的
	 * 
	 * @param requestUrl
	 * @return boolean
	 */
	public boolean isExcludeUrl(String requestUrl) {
		for (String excludeUrl : excludeUrls) {
			if (requestUrl.contains(excludeUrl.trim())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 当前URI资源是否需要登录才能访问
	 *
	 * @param requestURI
	 * @param request
	 * @return
	 */
	public boolean isExcludeStaticFile(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI)
				|| (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
			return true;
		}
		if (excludeStaticFiles != null) {
			for (String uri : excludeStaticFiles) {
				if (requestURI != null && requestURI.indexOf(uri) >= 0) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * //相同帐号登录控制
	 * 
	 * @param httpRequest
	 * @param InnerUser
	 * @return
	 */
/*	private boolean accountControl(HttpServletRequest request, UserInfoDO userInfoDO) {
		WebApplicationContext wct = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		InnerUserService InnerUserService = (InnerUserService) wct.getBean("innerUserService");
		// 获取mysql中的用户对象
		InnerUser findUser = InnerUserService.findById(InnerUser.getUserId());
		// 登陆时间为空
		if (InnerUser.getLastTime() == null || findUser.getLastTime() == null) {
			return true;
		}
		// 比较两次登录时间是否相同，如果相同表示没有多次登录，不同相同账号多次登录
		if (InnerUser.getLastTime().equals(findUser.getLastTime())) {
			return false;
		} else {
			return true;
		}
	}*/

}
