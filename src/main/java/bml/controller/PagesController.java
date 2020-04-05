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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 后台页面跳转模块
     */
    @RequestMapping(value = "/admin/index", method=RequestMethod.GET)
    public String adminPages(Model model) {
        Subject subject = SecurityUtils.getSubject();
        //这行代码可以获取到登陆者信息
        BmlUser user= (BmlUser) subject.getPrincipal();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        model.addAttribute("user",user);
        return "admin/index";
    }

    @RequestMapping(value = "/admin/console",method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/userInfo",method = RequestMethod.GET)
    public String userInfo(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        Session session = subject.getSession();
        session.setAttribute("user",user);
        model.addAttribute("user", user);
        return "admin/userInfo";
    }


    @RequestMapping(value = "/admin/log",method = RequestMethod.GET)
    public String log(Model model) {
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/log";
    }


    @RequestMapping(value = "/admin/workAdd",method = RequestMethod.GET)
    public String workAdd(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/workAdd";
    }


    @RequestMapping(value = "/admin/work",method = RequestMethod.GET)
    public String work(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/work";
    }


    @RequestMapping(value = "/admin/category",method = RequestMethod.GET)
    public String category(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/category";
    }


    @RequestMapping(value = "/admin/role",method = RequestMethod.GET)
    public String role(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/role";
    }

    @RequestMapping(value = "/admin/permission",method = RequestMethod.GET)
    public String permission(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/permission";
    }

    @RequestMapping(value = "/admin/consumer",method = RequestMethod.GET)
    public String user(Model model){
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        model.addAttribute("user",user);
        return "admin/user";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    /*@RequestMapping(value = "/error/{page}",method = RequestMethod.GET)
    public String error(@PathVariable String page){
        return "/error/"+page;
    }*/

}
