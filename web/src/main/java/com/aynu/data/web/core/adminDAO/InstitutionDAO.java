package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.bean.GenericBean;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface InstitutionDAO {


    void addInstitution(GenericBean genericBean);

    List<GenericBean> getInstitutionList(GenericBean genericBean);

    void updateInstitution(GenericBean genericBean);

    void deleteInstitution(GenericBean genericBean);

    int checkName(GenericBean genericBean);

    List schoolList();
}
