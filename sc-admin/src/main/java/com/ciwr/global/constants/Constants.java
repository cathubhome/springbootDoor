package com.ciwr.global.constants;

public class Constants {
    // 日期时间格式
    public static final String DEFAULT_YEAR_DATETIME = "yyyy";
    public static final String DEFAULT_MONTH_DATETIME = "MM";
    public static final String DEFAULT_FULL_DATETIME = "yyyy-MM-dd";
    public static final String DEFAULT_FULL_DATETIME1 = "yyyy-MM";
    public static final String DEFAULT_SIMPLE_DATE= "yyyy-MM-dd HH24:mi:ss";
    public static final String DEFAULT_SIMPLE_DATE_OFTEN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_ERROR_DATETIME = "0000-00-00";

    //分页默认参数
    public static final Integer DEFAULT_PAGE_INDEX=1;
    public static final Integer DEFAULT_PAGE_COUNT=30;

    //数据库方言
    public static final String MYSQL_DIALECT="mysql";
    //格式化代码
    public static final boolean FORMAT_SQL=true;
    //是否不显示SQL
    public static final boolean SHOW_SQL=true;
    //失败默认返回的url
    public static final  String ERRORURL="error";
    //设置sessionkeyPREFIX
    public static final  String DEFAULT_SESSION_KEY_PREFIX="shiro:session:";
    //验证码的名称
    public static final  String KAPTCHA_KEY="kaptcha";
    //非法sql
    public static final  String[] ILLEGALSQL={"DELETE","ASCII","UPDATE","SELECT","'","SUBSTR(","COUNT(","OR","AND","DROP","EXECUTE","EXEC","TRUNCATE","INTO","DECLARE","MASTER"};

    //加密方式
    public static final String HASHALGORITHMNAME_MD5="MD5";
    public static final String HASHALGORITHMNAME_SHA="SHA-256";
    //加密盐
    public static final String SALT=null;
    //加密次数
    public static final Integer HASHITERATIONS=1024;

    //mongodb Collection
    public static final String CUSTEOMER_COLLECTION_NAME="customer";
}
