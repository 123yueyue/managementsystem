package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.bean.GenericBean;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface TeacherDAO {

    List getTeacherList(GenericBean genericBean);

    void addTeacher(GenericBean genericBean);

    void updateTeacher(GenericBean genericBean);

    void deleteTeacher(GenericBean genericBean);
}
