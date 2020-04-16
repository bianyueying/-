package bml.controller;


import bml.entity.BmlUser;
import bml.others.BmlResult;
import bml.others.UserPasswordDto;
import bml.service.BmlUserService;
import bml.util.Md5Util;
import bml.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/admin")
public class BmlUserController {

    @Resource
    BmlUserService userService;

    @ApiOperation("返回所有用户信息")
    @GetMapping("/users")
    public BmlResult<Object> listAllUsers(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,String username,String gender,String phone,String email,String time) {
        List<BmlUser> users = userService.listUsers(page,limit,username,gender,phone,email,time);
        Map<String, Object> resultMap = new HashMap<>(500);
        resultMap.put("count",userService.getUserCount(username,gender,phone,email,time));
        resultMap.put("data",users);
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("更改用户资料 包括头像")
    @PutMapping("/user")
    public BmlResult<Object> updateUser(@RequestBody BmlUser user) {
        try {
            //更新用户信息
            userService.updateById(user);
            //Shiro更改用户信息同时更改subject中的信息
            Subject subject = SecurityUtils.getSubject();
            PrincipalCollection principals = subject.getPrincipals();
            String realName = principals.getRealmNames().iterator().next();
            BmlUser newUser = userService.getById(user.getId());
            PrincipalCollection  collection = new SimplePrincipalCollection(newUser, realName);
            subject.runAs(collection);
            //这时subject中的用户信息就已经更新成功了
            return new BmlResult<>(200,"更新成功!");
        }catch (Exception e) {
            return new BmlResult<>(400,"更新失败...");
        }
    }

    @ApiOperation("查找用户资料 用于实时回显")
    @GetMapping("/user")
    public BmlUser getUser(@RequestParam("id") String id) {
        return userService.getById(Long.parseLong(id));
    }

    @ApiOperation("删除用户 不是逻辑删除")
    @RequiresRoles("root")
    @DeleteMapping("/user")
    public BmlResult<Object> deleteUser(@RequestParam("id") String id) {
        try {
            userService.removeById(Long.parseLong(id));
            return new BmlResult<>(200,"删除成功!");
        }catch (Exception e) {
            return new BmlResult<>(400,"删除失败...");
        }
    }

    @ApiOperation("更改用户密码")
    @PutMapping("/user/password")
    public BmlResult<Object> updatePassword(@RequestBody UserPasswordDto passwordDto) {
        BmlUser user = userService.getById(passwordDto.getId());
        if (StringUtil.checkPassword(passwordDto.getOldPassword()) && StringUtil.checkPassword(passwordDto.getNewPassword())) {
            if (user.getPassword().equals(Md5Util.encrypt(user.getUsername(),passwordDto.getOldPassword()))) {
                user.setPassword(Md5Util.encrypt(user.getUsername(),passwordDto.getNewPassword()));
                userService.updateById(user);
                return new BmlResult<>(200,"更新成功 已自动为您刷新");
            } else {
                return new BmlResult<>(202,"原密码错误");
            }
        } else {
            return new BmlResult<>(201,"密码或新密码仅限数字与字母");
        }
    }
}

