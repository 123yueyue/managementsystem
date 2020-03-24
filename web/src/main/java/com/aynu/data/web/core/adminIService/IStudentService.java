package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface IStudentService {
    
    void addStudent(GenericBean genericBean);
    
    PageInfo getStudentList(GenericBean genericBean);

    void updateStudent(GenericBean genericBean);

    void deleteStudent(GenericBean genericBean);

    int checkName(GenericBean genericBean);
}
