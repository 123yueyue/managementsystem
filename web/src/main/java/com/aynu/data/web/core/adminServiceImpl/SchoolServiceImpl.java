package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.Entity.StudentDO;
import com.aynu.data.web.core.adminDAO.StudentDAO;
import com.aynu.data.web.core.adminIService.ISchoolService;
import com.aynu.data.web.core.adminIService.IStudentService;
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
public class SchoolServiceImpl implements ISchoolService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public PageInfo getName() {
        List<StudentDO> list = studentDAO.getName();
        return new PageInfo(list);
    }
}
