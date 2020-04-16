package bml.controller;


import bml.entity.BmlCategory;
import bml.others.BmlResult;
import bml.service.BmlCategoryPlusService;
import bml.service.BmlCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 分类信息表 前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-22
 */
@RestController
@RequestMapping("/admin")
public class BmlCategoryController {

    @Resource
    BmlCategoryService categoryService;

    @Resource
    BmlCategoryPlusService plusService;

    @ApiOperation("返回职位的总分类信息,不包含子分类")
    @GetMapping("/categories")
    public BmlResult<Object> listCategories() {
        Map<String, Object> resultMap = new HashMap<>(100);
        resultMap.put("data",categoryService.list(new QueryWrapper<BmlCategory>().eq("parent_id",0)));
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("返回树形的分类信息")
    @GetMapping("/category/list")
    public BmlResult<Object> listAll() {
        Map<String, Object> resultMap = new HashMap<>(100);
        resultMap.put("data",plusService.getCategoryTree());
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("添加分类信息")
    @PostMapping("/category/list")
    public BmlResult<Object> add(@RequestParam("id")String id){
        try {
            BmlCategory category = new BmlCategory();
            category.setTitle("未命名");
            category.setParentId(Integer.parseInt(id));
            categoryService.save(category);
            return new BmlResult<>(200,"添加成功,页面刷新后请去修改!");
        } catch (Exception e) {
            return new BmlResult<>(400,"分类添加失败...");
        }
    }

    @ApiOperation("添加父级分类信息")
    @PostMapping("/category")
    public BmlResult<Object> addCategory(@RequestBody BmlCategory category) {
        try {
            categoryService.save(category);
            return new BmlResult<>(200,"添加成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"添加失败...");
        }
    }


    @ApiOperation("更新分类信息")
    @PutMapping("/category/list")
    public BmlResult<Object> update(@RequestParam("id")String id,@RequestParam("title")String title){
        try {
            BmlCategory category = categoryService.getById(Integer.parseInt(id));
            category.setTitle(title);
            categoryService.updateById(category);
            return new BmlResult<>(200,"分类更新成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"分类修改失败...");
        }
    }

    @ApiOperation("删除一个分类信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @DeleteMapping("/category/list")
    public BmlResult<Object> delete(@RequestParam("id")String id) {
        try {
            categoryService.removeById(Long.parseLong(id));
            return new BmlResult<>(200,"删除成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"删除失败...");
        }
    }
}

