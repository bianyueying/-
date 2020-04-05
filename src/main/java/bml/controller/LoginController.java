package bml.controller;

import bml.entity.BmlUser;
import bml.others.BmlResult;
import bml.service.BmlUserService;
import bml.util.Md5Util;
import bml.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 月影
 * Date 2020/3/21 21:52
 */
@RestController
public class LoginController {

    @Resource
    BmlUserService userService;

    @ApiOperation("登录验证逻辑")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public BmlResult login(String username, String password, String code, Boolean rememberMe, HttpServletRequest request){
        //获取实体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Util.encrypt(username, password));
        //如果验证码正确
        if (CaptchaUtil.ver(code, request)) {
            try {
                if (rememberMe != null) {
                    token.setRememberMe(rememberMe);
                }
                subject.login(token);
                // 此处设置登录时间
                userService.setLoginTime(username);
                return new BmlResult(200,"登录成功");
            } catch (UnknownAccountException e) {
                return new BmlResult(201,"账号不存在");
            } catch (IncorrectCredentialsException e) {
                return new BmlResult(202,"账号名或密码错误");
            } catch (AuthenticationException e) {
                return new BmlResult(203,"未知错误");
            }
        } else {
            CaptchaUtil.clear(request);
            return new BmlResult(100,"验证码错误");
        }
    }

    @ApiOperation("检查用户名是否重复")
    @RequestMapping(value = "/user/check", method = RequestMethod.POST)
    public BmlResult check(String username) {
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        BmlUser user = userService.getOne(wrapper);
        if (StringUtil.checkUsername(username)) {
            /*如果为空则表明用户名不重复*/
            if (user == null) {
                return new BmlResult(200,"当前用户名可用");
            } else {
                return new BmlResult(201,"当前用户名已被占用");
            }
        } else {
            return new BmlResult(202,"用户名不符合条件");
        }
    }


    @ApiOperation("注册用户")
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public BmlResult add(@RequestBody BmlUser user) {
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        BmlUser one = userService.getOne(wrapper);
        //先判断输入的用户名和密码是否满足条件
        if (StringUtil.checkUsername(user.getUsername()) && StringUtil.checkPassword(user.getPassword())) {
            //如果用户为空 则表明用户名可用
            if (one == null) {
                user.setPassword(Md5Util.encrypt(user.getUsername(), user.getPassword()));
                userService.save(user);
                return new BmlResult(200,"注册成功 3秒后为您自动跳转");
            } else {
                return new BmlResult(202,"用户名重复 无法注册");
            }
        } else {
            return new BmlResult(203,"账号名或密码仅限字母数字组合");
        }
    }

    @ApiOperation("动态生成验证码")
    @RequestMapping(value = "/user/captcha",method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setHeader("Pragma", "No-cache");
        response.setContentType("image/gif");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setFont(Captcha.FONT_1);
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }
}
