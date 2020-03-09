package com.aynu.data.web.core.adminIService;

import com.aynu.data.common.Entity.UserDO;
import com.aynu.data.common.bean.GenericBean;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
public interface ILoginService {

    UserDO getUserInfo(GenericBean genericBean);

    void logout();
}
