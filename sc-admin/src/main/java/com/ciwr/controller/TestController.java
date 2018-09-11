package com.ciwr.controller;

import com.alibaba.fastjson.JSON;
import com.ciwr.global.annotation.ParameterModel;
import com.ciwr.global.common.utils.G;
import com.ciwr.global.common.utils.ShiroUtils;
import com.ciwr.global.constants.Constants;
import com.ciwr.modle.Student;
import com.ciwr.modle.Teacher;
import com.ciwr.modle.sys.SysLog;
import com.ciwr.modle.sys.SysUser;
import com.ciwr.service.sys.SysLogService;
import com.ciwr.service.sys.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserService sysUserService;

    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("login")
    @RequiresPermissions("user:test")
    public String login() {
        logger.debug("...................");
        return "index";
    }

    @GetMapping("index")
    @RequiresPermissions("user:test1")
    public String index() {
        logger.debug("...................");
        return "welcome";
    }


    @GetMapping("resp")
    @ResponseBody
    public G resp(String name, String addr) {
        logger.debug("...................");
        List<SysLog> sysLog = sysLogService.selectByMap(null);
        return G.ok().put("result", sysLog);
    }

    @GetMapping("sysuser/save")
    @ResponseBody
    public Map<String, String> saveSysUser(SysUser sysUser) {
        logger.debug("创建新用户");
        String salt = RandomStringUtils.randomAlphabetic(6);
        String confusionPassword = ShiroUtils.confusion(Constants.HASHALGORITHMNAME_SHA, sysUser.getPassword(), salt, Constants.HASHITERATIONS);
        sysUser.setSalt(salt);
        sysUser.setPassword(confusionPassword);
        sysUserService.insert(sysUser);
        Map<String, String> stringMap = new HashMap<>(2);
        stringMap.put("原始密码", sysUser.getPassword());
        stringMap.put("新 密码", confusionPassword);
        return stringMap;
    }


    /**
     * 装载参数测试
     *
     * @return
     */
    @RequestMapping(value = "/resolver")
    @ResponseBody
    public String resolver(@ParameterModel Teacher teacher, @ParameterModel Student student) {
        return "教师名称：" + JSON.toJSON(teacher.getName()) + "，学生名称：" + student.getName() + "，学生年龄：" + student.getAge();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String test(){
        return "hello";
    }

}
