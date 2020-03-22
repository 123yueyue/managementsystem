package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.SchoolDAO;
import com.aynu.data.web.core.adminDAO.TeacherDAO;
import com.aynu.data.web.core.adminIService.ISchoolService;
import com.aynu.data.web.core.adminIService.ITeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherDAO teacherDAO;


    @Override
    public void addTeacher(GenericBean genericBean) {
        teacherDAO.addTeacher(genericBean);
    }

    @Override
    public PageInfo getTeacherList(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        List list = teacherDAO.getTeacherList(genericBean);
        return new PageInfo(list);
    }

    @Override
    public void updateTeacher(GenericBean genericBean) {
        teacherDAO.updateTeacher(genericBean);
    }

    @Override
    public void deleteTeacher(GenericBean genericBean) {
        teacherDAO.deleteTeacher(genericBean);
    }

    @Override
    public int checkAccount(GenericBean genericBean) {
        return 0;
    }
}
