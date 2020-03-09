package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.common.global.Global;
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

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
@RestController
@RequestMapping("/data/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;



    @PostMapping
    @ApiOperation(value = "login", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam( paramType = "query" ,name = "userInfo" , value = "用户实体", required = true, dataType = "UserInfoDO")
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
                SessionFilter.saveSessionInnerUser(request, response, userInfoDO);
                request.getSession().setAttribute("account", userInfoDO.getAccount());
                String token = JwtUtil.generateToken(Global.signingKey, userInfoDO.getAccount());
                CookieUtil.create(response, Global.jwtTokenCookieName, token, false, -1, "localhost");
                responseEntity.setResponseObject(userInfoDO);
                responseEntity.put(Global.jwtTokenCookieName,token);
                responseEntity.setStatus(0);
                responseEntity.setMsg("登录成功");
            }
        }catch (Exception e ){
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            responseEntity.setStatus(1);
        }

        return responseEntity;
    }

    @GetMapping
    public ResponseEntity logout(){
        ResponseEntity responseEntity = new ResponseEntity();

        iLoginService.logout();

        return responseEntity;

    }
}
