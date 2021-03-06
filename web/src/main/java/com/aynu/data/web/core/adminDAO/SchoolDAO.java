package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.Entity.StudentDO;
import com.aynu.data.common.bean.GenericBean;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface SchoolDAO {

    List getSchoolList(GenericBean genericBean);

    void addSchool(GenericBean genericBean);

    void updateSchool(GenericBean genericBean);

    void deleteSchool(GenericBean genericBean);

    int checkAccount(GenericBean genericBean);

    int checkUserName(GenericBean genericBean);
}
