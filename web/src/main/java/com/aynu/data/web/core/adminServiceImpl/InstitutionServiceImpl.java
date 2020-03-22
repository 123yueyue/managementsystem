package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.InstitutionDAO;
import com.aynu.data.web.core.adminDAO.SchoolDAO;
import com.aynu.data.web.core.adminIService.IInstitutionService;
import com.aynu.data.web.core.adminIService.ISchoolService;
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
public class InstitutionServiceImpl implements IInstitutionService {
    @Autowired
    private InstitutionDAO institutionDAO;


    @Override
    public void addInstitution(GenericBean genericBean) {
        institutionDAO.addInstitution(genericBean);
    }

    @Override
    public PageInfo getInstitutionList(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        List<GenericBean> list = institutionDAO.getInstitutionList(genericBean);
        return new PageInfo(list);
    }

    @Override
    public void updateInstitution(GenericBean genericBean) {
        institutionDAO.updateInstitution(genericBean);
    }

    @Override
    public void deleteInstitution(GenericBean genericBean) {
        institutionDAO.deleteInstitution(genericBean);
    }

    @Override
    public PageInfo schoolList(GenericBean genericBean) {
        return new PageInfo(institutionDAO.schoolList());
    }

    @Override
    public int checkName(GenericBean genericBean) {
        return institutionDAO.checkName(genericBean);
    }
}
