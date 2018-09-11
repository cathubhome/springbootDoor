package com.ciwr.global.common.utils;

import com.ciwr.global.exception.GlobalException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

/**
 * shiro工具类
 */
public class ShiroUtils {

    public static Session session() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject subject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 登出
     */
    public static void logout() {
        if (subject().isAuthenticated()) {
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject().logout();
        }
    }

    /**
     * 获取验证码的值
     *
     * @param key
     * @return
     */
    public static String getKaptcha(String key) {
        Object kaptcha = session().getAttribute(key);
        if (kaptcha == null) {
            throw new GlobalException("验证码已过期!");
        }
        session().removeAttribute(key);
        return kaptcha.toString();
    }

    /**
     * 混淆原始密码
     *
     * @param hashAlgorithmName 加密方式
     * @param original          原始密码
     * @param salt              加密盐
     * @param hashIterations    加密次数
     * @return
     */
    public static String confusion(String hashAlgorithmName, String original, String salt, Integer hashIterations) {
        if (hashAlgorithmName == null || "".equalsIgnoreCase(hashAlgorithmName)) {
            return null;
        }
        if (hashIterations == null) {
            return null;
        }
        if (original == null || "".equalsIgnoreCase(original)) {
            return null;
        }
        String simpleHash = new SimpleHash(hashAlgorithmName, original, ByteSource.Util.bytes(salt), hashIterations).toHex();
        return simpleHash;
    }

}
