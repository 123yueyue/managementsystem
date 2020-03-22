package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.Entity.StudentDO;
import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface StudentDAO {
    List<StudentDO> getName();

    List getSchoolList(GenericBean genericBean);

    void addSchool(GenericBean genericBean);
}
