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

    List getStudentList(GenericBean genericBean);

    void addStudent(GenericBean genericBean);

    void updateStudent(GenericBean genericBean);

    void deleteStudent(GenericBean genericBean);

    int checkName(GenericBean genericBean);
}
