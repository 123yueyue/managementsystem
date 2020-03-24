package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.bean.GenericBean;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/23
 * @Description:
 */
public interface ClassDAO {

    List getClassList(GenericBean genericBean);

    void addClass(GenericBean genericBean);

    void updateClass(GenericBean genericBean);

    void deleteClass(GenericBean genericBean);

    int checkName(GenericBean genericBean);

    List getInstitutionList();
}
