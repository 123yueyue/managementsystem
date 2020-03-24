package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/23
 * @Description:
 */
public interface IClassService {

    PageInfo getClassList(GenericBean genericBean);

    PageInfo getInstitutionList(GenericBean genericBean);

    void addClass(GenericBean genericBean);

    void updateClass(GenericBean genericBean);

    void deleteClass(GenericBean genericBean);

    int checkName(GenericBean genericBean);
}
