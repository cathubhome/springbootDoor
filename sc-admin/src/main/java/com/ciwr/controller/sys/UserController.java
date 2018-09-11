package com.ciwr.controller.sys;

import com.ciwr.global.common.utils.G;
import com.ciwr.global.common.utils.PageUtils;
import com.ciwr.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("sys/user")
public class  UserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("list")
    public G list(@RequestParam Map<String,Object> param){
        PageUtils pageUtils = sysUserService.queryPage(param);
        return G.ok().put("page",pageUtils);
    }
}
