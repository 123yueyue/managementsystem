package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Data
public class StudentDO implements Serializable {

    private static final long serialVersionUID = -671617411937230081L;

    private Integer id;
    private String userName;
    private String password;
    private String account;
    private String phone;
    private String email;
    private String isMonitor;
    private String summary;
    private String adminId;
    private String institutionId;
    private String classId;
    private Date createTime;
}
