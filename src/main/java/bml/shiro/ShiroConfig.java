package bml.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : 月影
 * Date: 2019/12/27 12:46
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤集合
        Map<String,String> filterMap = new LinkedHashMap<>(100);
        //只允许以下请求无权限也可访问
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/check","anon");
        filterMap.put("/user/add","anon");
        filterMap.put("/user/captcha","anon");

        //swagger相关
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/v2/**","anon");
        filterMap.put("/webjars/**","anon");
        filterMap.put("/configuration/security","anon");
        filterMap.put("/configuration/ui","anon");

        /*
          授权过滤器 当授权拦截后 shiro会自动跳转到一个错误界面 下面可以自定义
          此处的意思是 rootPlus 请求只有权限为rootPlus的用户才能访问成功
         */
        filterMap.put("/bml", "perms[bml]");
        //除上面的之外 前端所有请求都需要权限
        filterMap.put("/*","authc");
        //后端所有请求都需要权限 测试环境下使用记住我功能
        filterMap.put("/admin/*","user");

        //添加Shiro内置拦截器 把上面的自定义规则添加进来
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改调整后的登录界面 默认是login.jsp页面 修改为login.html
        shiroFilterFactoryBean.setLoginUrl("/login");
        //如果你点击了高权限会发出 error 请求 跳转到指定界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        //设置记住我功能
        securityManager.setRememberMeManager(rememberMeManager());
        return new DefaultWebSecurityManager(userRealm);
    }

    /**
     * 创建realm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect 用于thymeleaf和Shiro标签使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }



    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间1天,单位秒;
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // cookieRememberMeManager.setCipherKey用来设置加密的Key,参数类型byte[],字节数组长度要求16
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 记住我配置
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    /**
     * 以下三个Bean解决Shiro注解在Controller上不生效的情况
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
