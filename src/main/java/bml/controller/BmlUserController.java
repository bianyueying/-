package bml.controller;


import bml.entity.BmlUser;
import bml.others.BmlResult;
import bml.others.UserPasswordDto;
import bml.service.BmlUserService;
import bml.util.Md5Util;
import bml.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Map<String, Object> listAllUsers(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,String username,String gender,String phone,String email,String time) {
        List<BmlUser> users = userService.listUsers(page,limit,username,gender,phone,email,time);
        Map<String, Object> resultMap = new HashMap<>(1000);
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",userService.getUserCount(username,gender,phone,email,time));
        resultMap.put("data",users);
        return resultMap;
    }

    @ApiOperation("更改用户资料 包括头像")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public BmlResult updateUser(@RequestBody BmlUser user) {
        try {
            userService.updateById(user);
            return BmlResult.ok("更新成功");
        }catch (Exception e) {
            return BmlResult.error("更新失败");
        }
    }

    @ApiOperation("查找用户资料 用于实时回显")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public BmlUser getUser(@RequestParam("id") String id) {
        return userService.getById(Long.parseLong(id));
    }

    @ApiOperation("删除用户 不是逻辑删除")
    @RequiresRoles(value = "root",logical = Logical.AND)
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public BmlResult deleteUser(@RequestParam("id") String id) {
        try {
            userService.removeById(Long.parseLong(id));
            return BmlResult.ok("删除成功");
        }catch (Exception e) {
            return BmlResult.error("删除失败");
        }
    }

    @ApiOperation("更改用户密码")
    @RequestMapping(value = "/user/password", method = RequestMethod.PUT)
    public BmlResult updatePassword(@RequestBody UserPasswordDto passwordDto) {
        BmlUser user = userService.getById(passwordDto.getId());
        if (StringUtil.checkPassword(passwordDto.getOldPassword()) && StringUtil.checkPassword(passwordDto.getNewPassword())) {
            if (user.getPassword().equals(Md5Util.encrypt(user.getUsername(),passwordDto.getOldPassword()))) {
                user.setPassword(Md5Util.encrypt(user.getUsername(),passwordDto.getNewPassword()));
                userService.updateById(user);
                return new BmlResult(200,"更新成功 已自动为您刷新");
            } else {
                return new BmlResult(202,"原密码错误");
            }
        } else {
            return new BmlResult(201,"密码或新密码仅限数字与字母");
        }
    }
}

