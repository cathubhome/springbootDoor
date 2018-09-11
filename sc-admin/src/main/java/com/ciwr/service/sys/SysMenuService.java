package com.ciwr.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.ciwr.modle.sys.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu>findRooMenu();
}
