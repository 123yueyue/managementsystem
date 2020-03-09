package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description:
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = -5395059168486910765L;

    private Integer id;
    private String userName;
    private String password;
    private String account;
    private String adminId;//学校id
    private String institutionId;//学院id
    private String classId;//班级id
    private String bigRole;//进入前台还是后台

}
