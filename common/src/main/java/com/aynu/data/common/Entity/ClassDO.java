package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/23
 * @Description:
 */
@Data
public class ClassDO implements Serializable {


    private static final long serialVersionUID = 5929135083083123823L;
    private Integer id;
    private String className;
    private String grade;
    private String intro;
    private String adminId;
    private String institutionId;
    private Date createTime;


}
