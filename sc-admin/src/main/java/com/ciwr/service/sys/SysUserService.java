package com.ciwr.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.ciwr.global.common.utils.PageUtils;
import com.ciwr.modle.sys.SysUser;

import java.util.List;
import java.util.Map;


public interface SysUserService extends IService<SysUser> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 根据登录名称查询用户
     * @param userName
     * @return
     */
    SysUser findUserByName(String userName);

    /**
     * 根据用户ID查询用户所拥有的菜单权限
     * @param userID 用户ID
     * @return
     */
    List<String> findUserAuthority(String userID);
}
