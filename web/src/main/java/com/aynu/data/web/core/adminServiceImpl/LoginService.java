package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.global.Global;
import com.aynu.data.common.redis.RedisDao;
import com.aynu.data.web.core.adminDAO.LoginDAO;
import com.aynu.data.web.core.adminIService.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginDAO loginDAO;

    private RedisDao redisDao;

    @Override
    public UserDO getUserInfo(GenericBean genericBean) {
        UserDO userDO = new UserDO();
        genericBean.put("tabelName", Global.sysAdminM);
        userDO = loginDAO.getAdminInfo(genericBean);    //判断是不是超级管理员
        if(userDO == null){
            genericBean.put("tabelName",Global.sysTeacherM);//查询是否是教师登录
            userDO = loginDAO.getTeacherInfo(genericBean);
            if(userDO == null){
                genericBean.put("tabelName",Global.sysStudentM);//查询是否是学生登录
                userDO = loginDAO.getStudentInfo(genericBean);
                userDO.setBigRole("taskCenter");    //学生登录，进入前台
            }else{  //如果是教师登录，则进入后台
                userDO.setBigRole("admin");
            }
        }else{  //如果是超级管理员，跳入后台
            userDO.setBigRole("admin");
        }
        return userDO;
    }

    @Override
    public void logout() {
        redisDao.vSet("account",1);
    }
}
