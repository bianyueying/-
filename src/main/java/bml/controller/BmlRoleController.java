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
    @GetMapping("/user/roles")
    public BmlResult<Object> listAllLog(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String name) {
        List<BmlRole> roles = roleService.listRoles(page,limit,name);
        Map<String, Object> resultMap = new HashMap<>(1000);
        resultMap.put("count",roleService.getRoleCount(name));
        resultMap.put("data",roles);
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("根据ID删除一条角色信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @DeleteMapping("/user/role")
    public BmlResult<Object> deleteRole(String id){
        try {
            roleService.removeById(Integer.parseInt(id));
            return new BmlResult<>(200,"删除成功!");
        } catch (Exception e){
            return new BmlResult<>(400,"删除失败...");
        }
    }

    @ApiOperation("根据ID查询出一条角色信息 用户回显")
    @GetMapping("/user/role")
    public BmlRole getRole(String id) {
        return roleService.getById(Integer.parseInt(id));
    }

    @ApiOperation("根据ID数组批量删除角色信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @DeleteMapping("/user/roles")
    public BmlResult<Object> deleteRoles(@RequestParam("ids") String[] ids){
        try {
            for (String id: ids) {
                roleService.removeById(Integer.parseInt(id));
            }
            return new BmlResult<>(200,"删除成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"删除失败...");
        }
    }

    @ApiOperation("添加一条角色信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @PostMapping("/user/role")
    public BmlResult<Object> addRole(@RequestBody BmlRole bmlRole){
        try {
            roleService.save(bmlRole);
            return new BmlResult<>(200,"添加成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"添加失败...");
        }
    }

    @ApiOperation("更新一条角色信息")
    @PutMapping("/user/role")
    public BmlResult<Object> updateRole(@RequestBody BmlRole bmlRole) {
        System.out.println(bmlRole);
        try {
            roleService.updateById(bmlRole);
            return new BmlResult<>(200,"更新成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"更新失败...");
        }
    }
}

