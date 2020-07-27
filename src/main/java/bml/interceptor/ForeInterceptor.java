package bml.interceptor;


import bml.entity.BmlLog;
import bml.entity.BmlUser;
import bml.service.BmlLogService;
import bml.util.BrowserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 月影
 * Date 2020/2/16 10:32
 */
public class ForeInterceptor implements HandlerInterceptor {

    @Resource
    BmlLogService logService;

    private BmlLog bmlLog = new BmlLog();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取登录凭证
        Subject subject = SecurityUtils.getSubject();
        // 访问者的IP
        String ip = request.getRemoteAddr();
        // 访问地址
        String url = request.getRequestURL().toString();
        //得到用户的浏览器名
        String userBrowser = BrowserUtil.getBrowserInfo(request);
        //得到用户的系统版本
        String userOs = BrowserUtil.getOsInfo(request);
        // 给SysLog增加字段
        bmlLog.setUsername(((BmlUser) subject.getPrincipal()).getUsername());
        bmlLog.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        bmlLog.setOsVersion(StringUtils.isEmpty(userOs) ? "获取系统信息失败" : userOs);
        bmlLog.setBrowser(StringUtils.isEmpty(userBrowser) ? "获取浏览器名失败" : userBrowser);
        bmlLog.setOperateUrl(StringUtils.isEmpty(url) ? "获取URL失败" : url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            bmlLog.setRemark(method.getName());
            logService.save(bmlLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}