package com.aynu.data.common.global;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/7
 * @Description: 全局变量
 */
public class Global {
    //登录时判断查询哪个表
    public static String sysAdminM = "sys_admin_m";         //admin表
    public static String sysTeacherM = "sys_teacher_m";     //teacher表
    public static String sysStudentM = "sys_student_m";     //student表

    /**
     * jwt-sso单点配置信息
     */
    public static final String jwtTokenCookieName = "token";
    public static final String signingKey = "management_v587";



}
