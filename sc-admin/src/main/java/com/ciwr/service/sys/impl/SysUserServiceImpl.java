package com.ciwr.service.sys.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciwr.global.common.utils.PageUtils;
import com.ciwr.global.common.utils.QueryParam;
import com.ciwr.mapper.sys.SysUserMapper;
import com.ciwr.modle.sys.SysUser;
import com.ciwr.service.sys.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysUser> sysUserPage = this.selectPage(
                new QueryParam<SysUser>(params).getPage(),
                new EntityWrapper<SysUser>()
        );
        return new PageUtils(sysUserPage);
    }


    @Override
    public SysUser findUserByName(String userName) {
        if(userName==null){
            return  null;
        }
        SysUser user = baseMapper.findUserByName(userName);
        return user;
    }

    @Override
    public List<String> findUserAuthority(String userID) {
        List<String> userAuthority = baseMapper.findUserAuthority(userID);
        return userAuthority;
    }
}
