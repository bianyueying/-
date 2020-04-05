package bml.controller;


import bml.entity.BmlCategory;
import bml.entity.BmlCategoryDto;
import bml.others.BmlResult;
import bml.service.BmlCategoryPlusService;
import bml.service.BmlCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @ApiOperation("返回职位的所有分类信息")
    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public List<BmlCategory> list() {
        QueryWrapper<BmlCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        return categoryService.list(wrapper);
    }

    @ApiOperation("返回树形的分类信息")
    @RequestMapping(value = "/category/list",method = RequestMethod.GET)
    public List<BmlCategoryDto> listAll(){
        return plusService.getCategoryTree();
    }

    @ApiOperation("添加分类信息")
    @RequestMapping(value = "/category/list",method = RequestMethod.POST)
    public BmlResult add(@RequestParam("id")String id){
        try {
            BmlCategory category = new BmlCategory();
            category.setTitle("未命名");
            category.setParentId(Integer.parseInt(id));
            categoryService.save(category);
            return BmlResult.ok("添加成功,页面刷新后请去修改！");
        } catch (Exception e) {
            return BmlResult.error("分类添加失败了...");
        }
    }


    @ApiOperation("更新分类信息")
    @RequestMapping(value = "/category/list",method = RequestMethod.PUT)
    public BmlResult update(@RequestParam("id")String id,@RequestParam("title")String title){
        try {
            BmlCategory category = categoryService.getById(Integer.parseInt(id));
            category.setTitle(title);
            categoryService.updateById(category);
            return BmlResult.ok("分类修改成功!");
        } catch (Exception e) {
            return BmlResult.error("分类修改失败了...");
        }
    }


    @ApiOperation("删除一个分类信息")
    @RequiresRoles(value = {"root","admin"},logical = Logical.OR)
    @RequestMapping(value = "/category/list",method = RequestMethod.DELETE)
    public BmlResult delete(@RequestParam("id")String id) {
        try {
            categoryService.removeById(Long.parseLong(id));
            return BmlResult.ok("删除成功");
        } catch (Exception e) {
            return BmlResult.ok("删除失败");
        }
    }

}

