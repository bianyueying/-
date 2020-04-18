package bml.controller;


import bml.entity.BmlWork;
import bml.entity.BmlWorkContent;
import bml.others.BmlResult;
import bml.others.BmlWorkDto;
import bml.service.BmlWorkContentService;
import bml.service.BmlWorkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位信息表 前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/admin")
public class BmlWorkController {

    @Resource
    BmlWorkService workService;

    @Resource
    BmlWorkContentService contentService;

    @ApiOperation("返回当前用户下的所有职位信息")
    @GetMapping("/works")
    public BmlResult<Object> listWorks(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String name, String address, String time) {
        List<BmlWork> bmlWorks = workService.listAllWork(page, limit, name, address, time);
        Map<String, Object> resultMap = new HashMap<>(100);
        resultMap.put("count",workService.getWorkCount(name,address,time));
        resultMap.put("data",bmlWorks);
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("添加一条职位信息")
    @PostMapping("/work")
    public BmlResult<Object> add(@RequestBody BmlWorkDto bmlWorkDto){
        try {
            Integer id = workService.addWork(bmlWorkDto);
            contentService.addWorkContent(bmlWorkDto,id);
            return new BmlResult<>(200,"添加成功！");
        } catch (Exception e) {
            return new BmlResult<>(400,"添加失败...");
        }
    }

    @ApiOperation("根据ID逻辑删除一条招聘信息 置1")
    @DeleteMapping("/work")
    public BmlResult<Object> deleteById(@RequestParam("id") String id){
        try {
            //逻辑删除 置1
            workService.removeById(Integer.parseInt(id));
            contentService.remove(new QueryWrapper<BmlWorkContent>().eq("work_id",Integer.parseInt(id)));
            return new BmlResult<>(200,"删除成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"删除失败...");
        }
    }

    @ApiOperation("根据ID查询出一条招聘信息 用于回显")
    @GetMapping("/work/{id}")
    public BmlWork getWork(@PathVariable("id") String id) {
        return workService.getById(id);
    }

    @ApiOperation("根据ID查询出一条招聘信息 需单独回显markdown文本")
    @GetMapping("/markdown/{id}")
    public String getWorkMd(@PathVariable("id")String id){
        QueryWrapper<BmlWorkContent> wrapper = new QueryWrapper<>();
        wrapper.eq("work_id",Integer.parseInt(id));
        return contentService.getOne(wrapper).getCompanyDescription();
    }

    @ApiOperation("更新一条职位信息")
    @PutMapping("/work")
    public BmlResult<Object> update(@RequestBody BmlWorkDto workDto){
        try {
            Integer id = workService.updateWork(workDto);
            contentService.updateWorkContent(workDto,id);
            return new BmlResult<>(200,"更新成功!");
        } catch (Exception e) {
            return new BmlResult<>(400,"更新失败...");
        }
    }

}

