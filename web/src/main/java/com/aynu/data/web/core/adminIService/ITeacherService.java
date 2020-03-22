package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/22
 * @Description:
 */
public interface ITeacherService {

    void addTeacher(GenericBean genericBean);

    PageInfo getTeacherList(GenericBean genericBean);

    void updateTeacher(GenericBean genericBean);

    void deleteTeacher(GenericBean genericBean);

    int checkAccount(GenericBean genericBean);
}
