package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.Entity.StudentDO;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.StudentDAO;
import com.aynu.data.web.core.adminIService.IStudentService;
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
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentDAO studentDAO;


    @Override
    public void addStudent(GenericBean genericBean) {
        studentDAO.addStudent(genericBean);
    }

    @Override
    public PageInfo getStudentList(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        return new PageInfo(studentDAO.getStudentList(genericBean));
    }

    @Override
    public void updateStudent(GenericBean genericBean) {
        studentDAO.updateStudent(genericBean);
    }

    @Override
    public void deleteStudent(GenericBean genericBean) {
        studentDAO.deleteStudent(genericBean);
    }

    @Override
    public int checkName(GenericBean genericBean) {
        return studentDAO.checkName(genericBean);
    }
}
