package com.ciwr.service.sys.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciwr.mapper.sys.SysRoleMapper;
import com.ciwr.modle.sys.SysRole;
import com.ciwr.service.sys.SysRoleService;
import org.springframework.stereotype.Service;

@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
