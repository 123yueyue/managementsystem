package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.bean.GenericBean;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/29
 * @Description:
 */
public interface IApplyService {

    PageInfo getApplyInfo();

    PageInfo getSecondOptions(GenericBean genericBean);

    GenericBean getStandardInfo(GenericBean genericBean);

    void addApply(GenericBean genericBean);

    PageInfo getApplyDetail(GenericBean genericBean);
}
