package com.ciwr.controller.sys;

import com.ciwr.global.annotation.Revoke;
import com.ciwr.global.common.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转到各个页面的控制类
 */
@Controller
@RequestMapping("")
public class PageController {
    /*
    * 首页
    * */
    @Revoke
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 登录页面
     * @return
     */
    @Revoke
    @RequestMapping("/login")
    public String login() {
        if (ShiroUtils.subject().isAuthenticated()) {
            return "index";
        }
        return "login";
    }
}
