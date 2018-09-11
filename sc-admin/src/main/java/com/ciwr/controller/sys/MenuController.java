package com.ciwr.controller.sys;

import com.ciwr.global.common.utils.G;
import com.ciwr.modle.sys.SysMenu;
import com.ciwr.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sys/menu")
public class MenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @RequestMapping("rootMenu")
    public G rootMenu(){
        List<SysMenu> rooMenu = sysMenuService.findRooMenu();
        return G.ok().put("rooMenu",rooMenu);
    }
}
