package bml.controller;

import bml.entity.BmlLogCount;
import bml.entity.BmlUser;
import bml.service.BmlLogCountService;
import bml.service.BmlLogService;
import bml.util.DateFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 月影
 * Date 2020/3/21 21:59
 */
@Controller
public class PagesController {

    @Resource
    BmlLogService logService;

    @Resource
    BmlLogCountService logCountService;

    /**
     * 前台登录跳转模块
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 后台页面跳转模块
     */
    @GetMapping("/admin/index")
    public String adminPages(Model model) {
        Subject subject = SecurityUtils.getSubject();
        //这行代码可以获取到登陆者信息
        BmlUser user= (BmlUser) subject.getPrincipal();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        model.addAttribute("user",user);
        return "admin/index";
    }

    @GetMapping("/admin/console")
    public String console(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        model.addAttribute("count",logService.count(null));
        QueryWrapper<BmlLogCount> wrapper = new QueryWrapper<>();
        wrapper.eq("some_day", DateFormatUtil.getStringTime(new Date()));
        model.addAttribute("todayCount",logCountService.getOne(wrapper).getRecordCount());
        return "admin/console";
    }

    @GetMapping("/admin/userInfo")
    public String userInfo(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user", user);
        return "admin/userInfo";
    }

    @GetMapping("/admin/log")
    public String log(Model model) {
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/log";
    }

    @GetMapping("/admin/workAdd")
    public String workAdd(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/workAdd";
    }

    @GetMapping("/admin/work")
    public String work(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/work";
    }

    @GetMapping("/admin/category")
    public String category(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/category";
    }

    @GetMapping("/admin/role")
    public String role(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/role";
    }

    @GetMapping("/admin/permission")
    public String permission(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/permission";
    }

    @GetMapping("/admin/consumer")
    public String user(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/user";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    /*@GetMapping("/error/{page}")
    public String error(@PathVariable String page){
        return "/error/"+page;
    }*/

}
