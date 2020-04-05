package bml.controller;


import bml.entity.BmlPermission;
import bml.entity.BmlPermissionDto;
import bml.others.BmlResult;
import bml.service.BmlPermissionPlusService;
import bml.service.BmlPermissionService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @RequestMapping(value = "/user/permission",method = RequestMethod.GET)
    public List<BmlPermissionDto> list() {
        return plusService.getPermsTree();
    }


    @ApiOperation("根据ID删除")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @RequestMapping(value = "/user/permission",method = RequestMethod.DELETE)
    public BmlResult deleteById(String id) {
        try {
            permissionService.removeById(Long.parseLong(id));
            return BmlResult.ok("删除成功！");
        } catch (Exception e) {
            return BmlResult.error("删除失败...");
        }
    }

    @ApiOperation("更新一条权限信息")
    @RequestMapping(value = "/user/permission",method = RequestMethod.PUT)
    public BmlResult updateById(@RequestBody BmlPermission permission) {
        try {
            permissionService.updateById(permission);
            return BmlResult.ok("更新成功！");
        } catch (Exception e) {
            return BmlResult.error("更新失败...");
        }
    }

    @ApiOperation("添加一条权限信息")
    @RequestMapping(value = "/user/permission",method = RequestMethod.POST)
    public BmlResult add(@RequestBody BmlPermission permission) {
        try {
            permissionService.save(permission);
            return BmlResult.ok("添加成功！");
        } catch (Exception e) {
            return BmlResult.error("添加失败...");
        }
    }





}

