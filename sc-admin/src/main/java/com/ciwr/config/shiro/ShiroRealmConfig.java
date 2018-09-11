package com.ciwr.config.shiro;

import com.ciwr.global.common.utils.RedisUtil;
import com.ciwr.global.common.utils.ShiroUtils;
import com.ciwr.modle.sys.SysUser;
import com.ciwr.service.sys.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm实现认证
 */
public class ShiroRealmConfig extends AuthorizingRealm {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //根据用户名去数据库查询是否有该用户  
        SysUser user = sysUserService.findUserByName(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUserName(),
                //密码
                user.getPassword(),
                //盐
                ByteSource.Util.bytes(user.getSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权校验
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = ShiroUtils.subject().getPrincipal();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        //获取用户的信息
        //将权限信息封装到AuthorizationInfo中，并返回
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        List<String> userAuthority = sysUserService.findUserAuthority("1");
        //根据用户信息查询用户有哪些权限
        Set<String> permissionSet = new HashSet<String>();
        if (userAuthority != null && userAuthority.size() > 0) {
            userAuthority.forEach(authority -> {
                permissionSet.add(authority);
            });
        }
        // 将查询到的授权信息填充到SimpleAuthorizationInfo中
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }
}