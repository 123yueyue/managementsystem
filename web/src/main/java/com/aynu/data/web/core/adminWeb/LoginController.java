package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.common.global.Global;
import com.aynu.data.common.redis.RedisDao;
import com.aynu.data.common.sessionFilter.CookieUtil;
import com.aynu.data.common.sessionFilter.JwtUtil;
import com.aynu.data.common.sessionFilter.SessionFilter;
import com.aynu.data.web.core.adminIService.ILoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
@RestController
@RequestMapping("/data/login")
public class LoginController extends BaseController {

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private RedisDao redisDao;


    @PostMapping
    @ApiOperation(value = "login", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam( paramType = "query" ,name = "username" , value = "账号", required = true, dataType = "UserInfoDO"),
            @ApiImplicitParam( paramType = "query" ,name = "password" , value = "密码", required = true, dataType = "UserInfoDO")
    })
    public ResponseEntity login(@RequestBody GenericBean genericBean, HttpServletRequest request, HttpServletResponse response){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            String username = genericBean.getString("username");
            String password = genericBean.getString("password");
            if (StringUtils.isEmpty(username)){
                responseEntity.setStatus(1);
                responseEntity.setMsg("账号为空，登录失败");
                return responseEntity;
            }
            if(StringUtils.isEmpty(password)){
                responseEntity.setStatus(1);
                responseEntity.setMsg("密码为空，登录失败");
                return responseEntity;
            }
            UserDO userInfoDO = iLoginService.getUserInfo(genericBean);

            if(userInfoDO == null){
                responseEntity.setStatus(1);
                responseEntity.setMsg("账号或密码错误");
                return responseEntity;
            }else{
                String token = JwtUtil.generateToken(Global.signingKey, userInfoDO.getAccount());
                SessionFilter.saveSessionInnerUser(request, response, userInfoDO,token);
                request.getSession().setAttribute("account", userInfoDO.getAccount());
                CookieUtil.create(response, Global.jwtTokenCookieName, token, false, -1, "localhost");
                responseEntity.setResponseObject(userInfoDO);
                HashMap data = new HashMap();
                data.put("token",token);
                responseEntity.put("data",data);
                responseEntity.setStatus(0);
                responseEntity.setCode(20000);
                responseEntity.setMsg("登录成功");
            }
        }catch (Exception e ){
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            responseEntity.setStatus(1);
        }

        return responseEntity;
    }

    @GetMapping
    @ApiOperation(value = "", notes = "获取登录信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam( paramType = "query" ,name = "username" , value = "账号", required = true, dataType = "UserInfoDO"),
            @ApiImplicitParam( paramType = "query" ,name = "password" , value = "密码", required = true, dataType = "UserInfoDO")
    })
    public ResponseEntity getInfo(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            Object userInfo = redisDao.vGet(genericBean.getString("token"));
            if(userInfo != null){
                responseEntity.setCode(20000);
                responseEntity.put("data",userInfo);
                responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
                responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
            }else{
                responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
                responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            }
        }catch (Exception e ){
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            responseEntity.setStatus(1);
        }

        return responseEntity;
    }

    @PostMapping("/logout")
    @ApiOperation(value = "logout", notes = "退出接口")
    @ApiImplicitParams({
            @ApiImplicitParam( paramType = "query" ,name = "username" , value = "账号", required = true, dataType = "UserInfoDO"),
    })
    public ResponseEntity logout(HttpServletRequest request){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            String token = request.getHeader("X-Token");
            redisDao.delete(token);
            responseEntity.setCode(20000);
        }catch (Exception e){
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
        }


        return responseEntity;

    }
}
