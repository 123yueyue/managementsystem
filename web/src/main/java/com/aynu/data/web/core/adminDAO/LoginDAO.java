package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.GenericBean;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
public interface LoginDAO {

    UserDO getAdminInfo(GenericBean genericBean);

    UserDO getTeacherInfo(GenericBean genericBean);

    UserDO getStudentInfo(GenericBean genericBean);
}
