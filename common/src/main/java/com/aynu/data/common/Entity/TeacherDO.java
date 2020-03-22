package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/20
 * @Description:教师实体类
 */
@Data
public class TeacherDO implements Serializable {

    private static final long serialVersionUID = 4040671162170332186L;

    private Integer id;
    private String userName;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String summary;
    private String disabled;
    private String adminId;
    private String institutionId;//学院id
    private Date createTime;

}
