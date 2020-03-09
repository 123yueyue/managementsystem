package com.aynu.data.common.bean;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;

/**
 * <pre>
 * 对象功能:BaseController基类
 * 开发人员:xslee
 * 创建时间:2018-11-2
 * </pre>
 */
public class BaseController {

	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public GenericBean getPageData() {

		HttpServletRequest request = this.getRequest();
		GenericBean returnPd = new GenericBean(request);
//		UserInfoDO innerUser = SessionFilter.getSessionInnerUser(request);
//		if (innerUser != null) {
//			returnPd.put("createUserId", innerUser.getId());
//			returnPd.put("updateUserId", innerUse                                                                                                                                   r.getId());
//			returnPd.put("institutionId",innerUser.getInstitutionId());
//		}
		return returnPd;
	}

//	/**
//	 * 获取session存储的user信息
//	 *
//	 * @return UserInfoDO
//	 */
//	public UserInfoDO getInnerUser() {
//
//		HttpServletRequest request = this.getRequest();
//		UserInfoDO innerUser = SessionFilter.getSessionInnerUser(request);
//		if (innerUser == null) {
//			return new UserInfoDO();
//		}
//		return innerUser;
//	}
//
	/**
	 * 得到request对象
	 *
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
//
	/**
	 * 得到response对象
	 *
	 * @return
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();

		return response;
	}
//
//	/**
//	 * 设置MyBatis分页参数
//	 *
//	 * @param request
//	 */
//	public void setStartPage(HttpServletRequest request) throws Exception {
//		// 分页获取列表信息,pageNum:当前页码;pageSize:每页个数
//		String pageNum = request.getParameter("pageNum");
//		String pageSize = request.getParameter("pageSize");
//		if (StringUtils.isNotEmpty(pageNum) && StringUtils.isNotEmpty(pageSize)) {
//			// 如果加上该PageHelper.startPage(MyBatis自带分页操作),那么下一个执行的sql语句会执行分页操作(第二条sql不受影响)
//			PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
//		} else {
//			throw new Exception("pageNum或pageSize为空,无法获取分页数据~");
//		}
//	}

//	/**
//	 * 设置MyBatis分页参数
//	 */
//	public void setStartPage() throws Exception {
//		setStartPage(this.getRequest());
//	}
//
//	/**
//	 * 常量定义
//	 */
//	private static final String ROOT = "root";
//
//	/* 根对象 */
//	private Object root;
//
//	/**
//	 * 集合元素名称、类型映射表
//	 */
//	private Map<String, Class<?>> elementClazzMap = new HashMap<String, Class<?>>();
//
//	public void doDataBind(Object targetObj) {
//		this.doDataBind(this.getRequest(), targetObj);
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:表单数据绑定
//	 * </p>
//	 *
//	 * @param request
//	 * @param targetObj
//	 *            目标对象
//	 */
//	@SuppressWarnings("rawtypes")
//	public void doDataBind(HttpServletRequest request, Object targetObj) {
//		String ROOT = "root";
//		OgnlContext context = new OgnlContext();
//		Object root = targetObj;
//		this.root = targetObj;
//		context.put(ROOT, root);
//
//		Enumeration paramNameEnum = request.getParameterNames();
//		List<String> paramNames = new ArrayList<String>();
//		while (paramNameEnum.hasMoreElements()) {
//			String paramKey = (String) paramNameEnum.nextElement();
//			paramNames.add(0, paramKey);
//		}
//		Map map = request.getParameterMap();
//		for (String paramKey : paramNames) {
//			Object paramValue = map.get(paramKey);
//			if (paramValue != null) {
//				// 绑定绑定
//				this.doBindField(paramKey, paramValue, context);
//			}
//		}
//
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:表单数据绑定
//	 * </p>
//	 *
//	 * @param request
//	 * @param targetObj
//	 *            目标对象
//	 *            目标对象中集合元素类型，有多个集合元素
//	 */
//	@SuppressWarnings({ "rawtypes", "unused" })
//	private void doDataBind(HttpServletRequest request, Object targetObj, String[] elementNames, Class[] elementClazzs) {
//		String ROOT = "root";
//		OgnlContext context = new OgnlContext();
//		Object root = targetObj;
//		this.root = targetObj;
//		context.put(ROOT, root);
//
//		// 集合元素类型映射表
//		if (elementNames != null && elementClazzs != null && elementNames.length == elementClazzs.length) {
//			for (int i = 0; i < elementNames.length; i++) {
//				elementClazzMap.put(elementNames[i], elementClazzs[i]);
//			}
//		}
//		Enumeration paramNameEnum = request.getParameterNames();
//		List<String> paramNames = new ArrayList<String>();
//		while (paramNameEnum.hasMoreElements()) {
//			String paramKey = (String) paramNameEnum.nextElement();
//			paramNames.add(0, paramKey);
//		}
//		Map map = request.getParameterMap();
//		for (String paramKey : paramNames) {
//			Object paramValue = map.get(paramKey);
//			if (paramValue != null) {
//				// 绑定绑定
//				this.doBindField(paramKey, paramValue, context);
//			}
//		}
//
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:字段数据绑定
//	 * </p>
//	 *
//	 * @param key
//	 *            键,如personVo.personId、bizEntityItemVo.dictVo.dictId
//	 * @param value
//	 *            值
//	 * @param context
//	 *            上下文对象
//	 */
//	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
//	private void doBindField(String key, Object value, OgnlContext context) {
//		// 绑定思路:
//		// 1、从外往内按路径依次遍历
//		// 1.1、对于非叶子节点，也即上级属性，如上级List/Object，则需判断是否为空，对于为空的则根据其类型进行实例化
//		// 1.2、对于叶子节点，也即真正要绑定数据的属性字段，基于OGNL机制进行绑定
//		// 以personVo.personId为例，下同
//		key = ROOT.concat(".").concat(key);
//		String[] ks = key.split("\\.");// root、personVo、personId
//		// 是否叶子节点元素
//		boolean isLeafElement = false;
//		// 从外往内依次处理personVo、personVo.personId属性
//		try {
//			String listElClassName = null;// 集合元素的类名
//			for (int i = 1; i < ks.length; i++) {
//				if (i == (ks.length - 1)) {
//					isLeafElement = true;
//				}
//				String fieldName = ks[i];
//				String fieldPath = this.getFieldPath(i, ks);
//				String fieldParentPath = this.getFieldPath(i - 1, ks);
//				Object fieldParentObj = null;
//				// 非叶子节点，若为空则根据其类型进行实例化
//				if (!isLeafElement) {
//					Object fieldObj = null;
//					try {
//						fieldObj = Ognl.getValue(fieldPath, context);
//					} catch (Exception e) {
//					} finally {
//						if (fieldObj == null) {
//							if (!this.isListElement(fieldName)) {// 非集合元素
//								fieldParentObj = Ognl.getValue(fieldParentPath, context);
//								Field field = null;
//								try {
//									field = fieldParentObj.getClass().getDeclaredField(fieldName);
//								} catch (NoSuchFieldException e) {
//									field = fieldParentObj.getClass().getSuperclass().getDeclaredField(fieldName);
//								} finally {
//									if (field != null) {
//										fieldObj = Class.forName(field.getType().getName()).newInstance();
//										Ognl.setValue(fieldPath, context, fieldObj);
//									}
//								}
//
//							} else {// 集合元素
//								int beginIndex = fieldName.lastIndexOf("[");
//								int endIndex = fieldName.lastIndexOf("]");
//								Integer elementIndex = Integer.parseInt(fieldName.substring(beginIndex + 1, endIndex));
//								String listFieldName = fieldName.substring(0, beginIndex);
//								listElClassName = this.elementClazzMap.get(listFieldName).getName();
//								String listFieldPath = fieldParentPath.concat(".").concat(listFieldName);
//								Object listObj = Ognl.getValue(listFieldPath, context);
//								if (listObj != null && listObj instanceof List) {
//									List list = (List) listObj;
//									for (int j = 0; j < elementIndex + 1; j++) {
//										try {
//											list.get(j);
//										} catch (IndexOutOfBoundsException ioe) {
//											list.add(j, elementClazzMap.get(listFieldName).newInstance());
//										}
//									}
//								}
//							}
//
//						} else {
//							listElClassName = fieldObj.getClass().getName();
//						}
//					}
//
//				} else {// 叶子节点，基于OGNL进行绑定
//					String[] values = (String[]) value;
//					if (values != null && values.length > 0) {
//						String clazzName = null;
//						Class clazz = null;
//						Field field = null;
//						if (StringUtils.isNotEmpty(listElClassName)) {
//							clazzName = listElClassName;
//						} else if (fieldParentObj == null) {
//							clazzName = this.root.getClass().getName();
//						} else {
//							clazzName = fieldParentObj.getClass().getName();
//						}
//						clazz = Class.forName(clazzName);
//						try {
//							field = clazz.getDeclaredField(fieldName);
//						} catch (Exception ex) {
//						}
//						if (field != null) {
//							if (field.getType().getName().equals("java.util.Date")) {
//								convertValueToDate(context, fieldPath, values[0]);
//							} else if (field.getType().getName().equals("java.sql.Time")) {
//								convertValueToTime(context, fieldPath, values[0]);
//							} else if (field.getType().getName().equals("boolean")) {
//								Ognl.setValue(fieldPath, context, Boolean.valueOf(values[0]));
//							} else {
//								Ognl.setValue(fieldPath, context, values[0]);
//							}
//						} else {
//							Ognl.setValue(fieldPath, context, values[0]);
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//		}
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:转换值到time
//	 * </p>
//	 *
//	 * @param context
//	 * @param fieldPath
//	 * @param value
//	 * @throws OgnlException
//	 *
//	 */
//	private void convertValueToTime(OgnlContext context, String fieldPath, String value) throws OgnlException {
//		Time time = Time.valueOf(value);
//		Ognl.setValue(fieldPath, context, time);
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:转换值到Date
//	 * </p>
//	 *
//	 * @param context
//	 * @param fieldPath
//	 * @param value
//	 * @throws OgnlException
//	 * @throws ParseException
//	 *
//	 */
//	@SuppressWarnings("deprecation")
//	private void convertValueToDate(OgnlContext context, String fieldPath, String value)
//			throws OgnlException, ParseException {
//		/*
//		 * SimpleDateFormat sdf =null; if (value.length()>10) { sdf=new
//		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); }else{ sdf=new
//		 * SimpleDateFormat("yyyy-MM-dd"); } value = value.replace("T", " ");
//		 * Ognl.setValue(fieldPath, context, sdf.parse(value));
//		 */
//		Ognl.setValue(fieldPath, context, new Date(value));
//	}
//
//	/**
//	 *
//	 * <p>
//	 * 功能描述:获取字段路径
//	 * </p>
//	 *
//	 * @param k
//	 *            索引
//	 * @param ks
//	 *            字段数组
//	 * @return
//	 */
//	private String getFieldPath(int k, String[] ks) {
//		StringBuffer buf = new StringBuffer();
//		for (int i = 0; i < k + 1; i++) {
//			buf.append(ks[i]).append(".");
//		}
//		buf.delete(buf.length() - 1, buf.length());
//		return buf.toString();
//	}


}
