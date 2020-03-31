package com.aynu.data.web.core.adminDAO;

import com.aynu.data.common.bean.GenericBean;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/29
 * @Description:
 */
public interface ApplyDAO {


    List getFirstLevel();

    List<GenericBean> getSecondLevel(String classId);

    List getSecondOptions(GenericBean genericBean);

    GenericBean getStandardInfo(GenericBean genericBean);

    void addApply(GenericBean genericBean);

    List<GenericBean> getApplyDetail(GenericBean genericBean);
}
