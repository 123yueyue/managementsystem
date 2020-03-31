package com.aynu.data.web.core.adminServiceImpl;

import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminDAO.ApplyDAO;
import com.aynu.data.web.core.adminDAO.ClassDAO;
import com.aynu.data.web.core.adminIService.IApplyService;
import com.aynu.data.web.core.adminIService.IClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Service
public class ApplyServiceImpl implements IApplyService {
    @Autowired
    private ApplyDAO applyDAO;

    @Override
    public PageInfo getApplyInfo() {
        List<GenericBean> fistLevel = applyDAO.getFirstLevel();
        List<GenericBean> secondLevel = new ArrayList<>();
        List<GenericBean> thirdLevel = new ArrayList<>();
        for (GenericBean first:fistLevel){
            secondLevel = applyDAO.getSecondLevel(first.getString("value"));
            for(GenericBean second:secondLevel){
                thirdLevel = applyDAO.getSecondLevel(second.getString("value"));
                second.put("children",thirdLevel);
            }
            first.put("children",secondLevel);
        }
        return new PageInfo(fistLevel);
    }

    @Override
    public PageInfo getSecondOptions(GenericBean genericBean) {
        genericBean.put("classId",genericBean.getString("2"));
        List secondLevel = applyDAO.getSecondOptions(genericBean);
        return new PageInfo(secondLevel);
    }

    @Override
    public GenericBean getStandardInfo(GenericBean genericBean) {
        GenericBean standardInfo = applyDAO.getStandardInfo(genericBean);
        return standardInfo;
    }

    @Override
    public void addApply(GenericBean genericBean) {
        applyDAO.addApply(genericBean);
    }

    @Override
    public PageInfo getApplyDetail(GenericBean genericBean) {
        PageHelper.startPage(genericBean.getInt("pageNum"),genericBean.getInt("pageSize"));
        List<GenericBean> list = applyDAO.getApplyDetail(genericBean);
        for (GenericBean info :list){
            info.put("classStandard",info.getString("className")+"-"+info.getString("standardName"));
        }
        return new PageInfo(list);
    }
}
