package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface IInstitutionService {

    void addInstitution(GenericBean genericBean);

    PageInfo getInstitutionList(GenericBean genericBean);

    void updateInstitution(GenericBean genericBean);

    void deleteInstitution(GenericBean genericBean);

    PageInfo schoolList(GenericBean genericBean);

    int checkName(GenericBean genericBean);
}
