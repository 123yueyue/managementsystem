package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.Entity.StudentDO;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.SchoolDAO;
import com.aynu.data.web.core.adminDAO.StudentDAO;
import com.aynu.data.web.core.adminIService.ISchoolService;
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
public class SchoolServiceImpl implements ISchoolService {
    @Autowired
    private SchoolDAO schoolDAO;

    @Override
    public PageInfo getSchoolList(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        List list = schoolDAO.getSchoolList(genericBean);
        return new PageInfo(list);
    }

    @Override
    public void addSchool(GenericBean genericBean) {
        schoolDAO.addSchool(genericBean);
    }

    @Override
    public void updateSchool(GenericBean genericBean) {
        schoolDAO.updateSchool(genericBean);
    }

    @Override
    public void deleteSchool(GenericBean genericBean) {
        schoolDAO.deleteSchool(genericBean);
    }

    @Override
    public int checkAccount(GenericBean genericBean) {
        return schoolDAO.checkAccount(genericBean);
    }

    @Override
    public int checkUserName(GenericBean genericBean) {
        return schoolDAO.checkUserName(genericBean);
    }
}
