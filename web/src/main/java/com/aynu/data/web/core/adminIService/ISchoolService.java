package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public interface ISchoolService {

    PageInfo getSchoolList(GenericBean genericBean);

    void addSchool(GenericBean genericBean);

    void updateSchool(GenericBean genericBean);

    void deleteSchool(GenericBean genericBean);

    int checkAccount(GenericBean genericBean);

    int checkUserName(GenericBean genericBean);
}
