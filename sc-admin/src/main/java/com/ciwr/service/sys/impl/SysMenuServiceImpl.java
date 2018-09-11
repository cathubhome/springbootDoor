package com.ciwr.service.sys.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciwr.mapper.sys.SysMenuMapper;
import com.ciwr.modle.sys.SysMenu;
import com.ciwr.service.sys.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> findRooMenu() {
        return baseMapper.findRooMenu();
    }
}
