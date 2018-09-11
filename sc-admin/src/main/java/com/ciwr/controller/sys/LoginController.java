package com.ciwr.controller.sys;

import com.ciwr.global.annotation.AnnoSysLog;
import com.ciwr.global.annotation.Revoke;
import com.ciwr.global.common.utils.G;
import com.ciwr.global.common.utils.ShiroUtils;
import com.ciwr.global.constants.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private ServletContext servletContext;


    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;


    @PostMapping("/ajaxLogin")
    @ResponseBody
    @AnnoSysLog
    @ApiOperation(value = "登录验证",notes = "ajax提交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "登录名",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "登录密码",dataType = "String",required = true),
            @ApiImplicitParam(name = "kaptcha",value = "验证码",dataType = "String",required = true),
            @ApiImplicitParam(name = "rememberMe",value = "记住密码",defaultValue = "false",dataType = "boolean",required = false)
    })
     public G kaptchaLogin(String username, String password, String kaptcha, boolean rememberMe, HttpServletRequest request) {
        if (kaptcha == null || "".equals(kaptcha)) {
            return G.error("验证码不能为空");
        }
        if (!kaptcha.equalsIgnoreCase(ShiroUtils.getKaptcha(Constants.KAPTCHA_KEY))) {
            return G.error("验证码不正确!");
        }
        UsernamePasswordToken token = null;
        try {
            token = new UsernamePasswordToken(username, password.toCharArray(), rememberMe);
            ShiroUtils.subject().login(token);
        } catch (IncorrectCredentialsException e) {
            return G.error("用户名或密码错误!");
        } catch (LockedAccountException e) {
            e.printStackTrace();
            return G.error("账户被锁定!");
        } catch (AuthenticationException e) {
            return G.error("用户名或密码错误!");
        } catch (Exception e) {
            e.printStackTrace();
            return G.error();
        }
        String successUrl = servletContext.getContextPath()+ shiroFilterFactoryBean.getSuccessUrl();
        Map<String,Object> resultMap = new HashMap<>(1);
        resultMap.put("back_url",successUrl);
        return G.ok(resultMap);
    }

    /**
     * 获取验证码 的 请求路径
     *
     * @throws Exception
     */
    @Revoke
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 禁止服务器端缓存
        response.setDateHeader("Expires", 0);
        // 设置标准的 HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准 HTTP/1.0 不缓存图片
        response.setHeader("Pragma", "no-cache");
        // 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        response.setContentType("images/jpeg");
        // 为图片创建文本
        String capText = kaptchaProducer.createText();
        // 将文本保存在session中，这里就使用包中的静态变量吧
        ShiroUtils.session().setAttribute(Constants.KAPTCHA_KEY, capText);
        // 创建带有文本的图片
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 图片数据输出
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
