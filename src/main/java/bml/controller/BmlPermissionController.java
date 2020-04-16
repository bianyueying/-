package bml.controller;


import bml.entity.BmlPermission;
import bml.others.BmlResult;
import bml.service.BmlPermissionPlusService;
import bml.service.BmlPermissionService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限分类信息表 前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/admin")
public class BmlPermissionController {

    @Resource
    BmlPermissionService permissionService;

    @Resource
    BmlPermissionPlusService plusService;

    @ApiOperation("查询权限树形结构")
    @GetMapping("/user/permission")
    public BmlResult<Object> listPermissions() {
        Map<String, Object> resultMap = new HashMap<>(100);
        resultMap.put("data",plusService.getPermsTree());
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("根据ID删除")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @DeleteMapping("/user/permission")
    public BmlResult<Object> deleteById(String id) {
        try {
            permissionService.removeById(Long.parseLong(id));
            return new BmlResult<>(200,"删除成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"删除失败...");
        }
    }

    @ApiOperation("更新一条权限信息")
    @PutMapping("/user/permission")
    public BmlResult<Object> updateById(@RequestBody BmlPermission permission) {
        try {
            permissionService.updateById(permission);
            return new BmlResult<>(200,"更新成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"更新失败...");
        }
    }

    @ApiOperation("添加一条权限信息")
    @PostMapping("/user/permission")
    public BmlResult<Object> add(@RequestBody BmlPermission permission) {
        try {
            permissionService.save(permission);
            return new BmlResult<>(200,"添加成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"添加失败...");
        }
    }
}

