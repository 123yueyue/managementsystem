package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/22
 * @Description:
 */
@Data
public class InstitutionDO implements Serializable {

    private static final long serialVersionUID = 7051123175993956098L;

    private Integer id;
    private String institutionName;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String intro;
    private String adminId;
    private Date createTime;


}
