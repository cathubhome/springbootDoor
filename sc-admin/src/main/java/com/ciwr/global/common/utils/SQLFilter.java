package com.ciwr.global.common.utils;

import com.ciwr.global.constants.Constants;
import com.ciwr.global.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;

public class SQLFilter {
    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param str
     * @return
     *  true 表示参数不存在SQL注入风险
     *  false 表示参数存在SQL注入风险
     */
    public static String sqlInject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        //字符转换成大写
        str = str.toUpperCase();
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        for (String sqlIllegal : Constants.ILLEGALSQL) {
            if(sqlIllegal.indexOf(sqlIllegal)!=-1){
                throw new GlobalException("参数包含非法字符!");
            }
        }
        return str;
    }

}
