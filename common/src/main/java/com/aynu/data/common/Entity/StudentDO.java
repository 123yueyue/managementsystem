package com.aynu.data.common.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Data
public class StudentDO implements Serializable {

    private static final long serialVersionUID = -671617411937230081L;

    private Integer id;
    private String name;

}
