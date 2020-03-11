package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.global.Global;
import com.aynu.data.common.redis.RedisDao;
import com.aynu.data.web.core.adminDAO.LoginDAO;
import com.aynu.data.web.core.adminIService.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private RedisDao redisDao;

    @Override
    public UserDO getUserInfo(GenericBean genericBean) {
        UserDO userDO = new UserDO();
        genericBean.put("tabelName", Global.sysAdminM);
        userDO = loginDAO.getAdminInfo(genericBean);    //判断是不是超级管理员
        List list = new ArrayList<>();
        if(userDO == null){
            genericBean.put("tabelName",Global.sysTeacherM);//查询是否是教师登录
            userDO = loginDAO.getAdminInfo(genericBean);
            if(userDO == null){
                genericBean.put("tabelName",Global.sysStudentM);//查询是否是学生登录
                userDO = loginDAO.getAdminInfo(genericBean);
                if(userDO == null) {
                    return null;
                }else{
                    list.add("task");
                    userDO.setRoles(list);    //学生登录，进入前台
                }
            }else{  //如果是教师登录，则进入后台
                list.add("admin");
                userDO.setRoles(list);
            }
        }else{  //如果是超级管理员，跳入后台
            list.add("admin");
            userDO.setRoles(list);
        }
        return userDO;
    }


}
