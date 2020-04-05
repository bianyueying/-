package bml.controller;


import bml.entity.BmlRole;
import bml.others.BmlResult;
import bml.service.BmlRoleService;
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
 * 角色相关信息表 前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/admin")
public class BmlRoleController {

    @Resource
    BmlRoleService roleService;


    @ApiOperation("返回所有角色信息")
    @RequestMapping(value = "/user/roles",method = RequestMethod.GET)
    public Map<String, Object> listAllLog(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String name) {
        List<BmlRole> roles = roleService.listRoles(page,limit,name);
        Map<String, Object> resultMap = new HashMap<>(1000);
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",roleService.getRoleCount(name));
        resultMap.put("data",roles);
        return resultMap;
    }

    @ApiOperation("根据ID删除一条角色信息")
    @RequestMapping(value = "/user/role",method = RequestMethod.DELETE)
    public BmlResult deleteRole(String id){
        try {
            roleService.removeById(Integer.parseInt(id));
            return BmlResult.ok("删除成功!");
        } catch (Exception e){
            return BmlResult.error("删除失败...");
        }
    }

    @ApiOperation("根据ID查询书一条角色信息 用户回显")
    @RequestMapping(value = "/user/role",method = RequestMethod.GET)
    public BmlRole getRole(String id) {
        return roleService.getById(Integer.parseInt(id));
    }

    @ApiOperation("根据ID数组删除信息")
    @RequestMapping(value = "/user/roles",method = RequestMethod.DELETE)
    public BmlResult deleteRoles(@RequestParam("ids") String[] ids){
        try {
            for (String id: ids) {
                roleService.removeById(Integer.parseInt(id));
            }
            return BmlResult.ok("删除成功!");
        }catch (Exception e) {
            return BmlResult.error("删除失败...");
        }
    }


    @ApiOperation("添加一条角色信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @RequestMapping(value = "/user/role",method = RequestMethod.POST)
    public BmlResult addRole(@RequestBody BmlRole bmlRole){
        try {
            roleService.save(bmlRole);
            return BmlResult.ok("添加成功!");
        } catch (Exception e) {
            return BmlResult.error("添加失败...");
        }
    }

    @ApiOperation("更新一条角色信息")
    @RequestMapping(value = "/user/role",method = RequestMethod.PUT)
    public BmlResult updateRole(@RequestBody BmlRole bmlRole) {
        System.out.println(bmlRole);
        try {
            roleService.updateById(bmlRole);
            return BmlResult.ok("更新成功!");
        } catch (Exception e) {
            return BmlResult.error("更新失败...");
        }
    }





}

