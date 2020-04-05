package bml.shiro;

import bml.entity.BmlUser;
import bml.service.BmlRoleService;
import bml.service.BmlUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 月影
 * Date: 2019/12/27 12:48
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private BmlUserService userService;

    @Autowired
    private BmlRoleService roleService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前实体
        Subject subject = SecurityUtils.getSubject();
        //从主体传过来的认证信息中，获得当前用户对象
        BmlUser user = (BmlUser)subject.getPrincipal();
        //根据查询出的用户角色赋值
        info.addRole(roleService.getById(user.getRoleId()).getRoleName());
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //此处编写判断逻辑 用于判断账号密码是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据登录的名字查询出用户的所有信息
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",token.getUsername());
        BmlUser user = userService.getOne(wrapper);
        //用户为空，则表示用户名不存在 返回null 底层会自动返回未知账号异常
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}
