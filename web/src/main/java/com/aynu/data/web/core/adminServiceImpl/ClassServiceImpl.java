package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.ClassDAO;
import com.aynu.data.web.core.adminDAO.InstitutionDAO;
import com.aynu.data.web.core.adminIService.IClassService;
import com.aynu.data.web.core.adminIService.IInstitutionService;
import com.github.pagehelper.Page;
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
public class ClassServiceImpl implements IClassService {
    @Autowired
    private ClassDAO classDAO;


    @Override
    public PageInfo getClassList(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        return new PageInfo(classDAO.getClassList(genericBean));
    }

    @Override
    public PageInfo getInstitutionList(GenericBean genericBean) {
        return new PageInfo(classDAO.getInstitutionList());
    }

    @Override
    public void addClass(GenericBean genericBean) {
        classDAO.addClass(genericBean);
    }

    @Override
    public void updateClass(GenericBean genericBean) {
        classDAO.updateClass(genericBean);
    }

    @Override
    public void deleteClass(GenericBean genericBean) {
        classDAO.deleteClass(genericBean);
    }

    @Override
    public int checkName(GenericBean genericBean) {
        return classDAO.checkName(genericBean);
    }
}
