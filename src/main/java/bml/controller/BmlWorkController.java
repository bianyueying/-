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

    @ApiOperation("返回所有的职位信息")
    @RequestMapping(value = "/works", method = RequestMethod.GET)
    public Map<String,Object> listAllWork(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, String name, String address, String time) {
        List<BmlWork> bmlWorks = workService.listAllWork(page, limit, name, address, time);
        Map<String, Object> resultMap = new HashMap<>(5000);
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",workService.getWorkCount(name,address,time));
        resultMap.put("data",bmlWorks);
        return resultMap;
    }


    @ApiOperation("添加一条职位信息")
    @RequestMapping(value = "/work",method = RequestMethod.POST)
    public BmlResult add(@RequestBody BmlWorkDto bmlWorkDto){
        System.out.println(bmlWorkDto);
        try {
            Integer id = workService.addWork(bmlWorkDto);
            contentService.addWorkContent(bmlWorkDto,id);
            return BmlResult.ok("添加成功！");
        }catch (Exception e) {
            return BmlResult.error("添加失败！");
        }
    }


    @ApiOperation("根据ID逻辑删除一条招聘信息 置1")
    @RequestMapping(value = "/work", method = RequestMethod.DELETE)
    public BmlResult deleteById(@RequestParam("id") String id){
        try {
            //逻辑删除 置1
            workService.removeById(Integer.parseInt(id));
            QueryWrapper<BmlWorkContent> wrapper = new QueryWrapper<>();
            wrapper.eq("work_id",Integer.parseInt(id));
            contentService.remove(wrapper);
            return BmlResult.ok("删除成功(>_<)!!!");
        } catch (Exception e) {
            return BmlResult.error("删除失败了......");
        }
    }


    @ApiOperation("根据ID查询出一条招聘信息 用于回显")
    @RequestMapping(value = "/work/{id}", method = RequestMethod.GET)
    public BmlWork getWork(@PathVariable("id") String id) {
        return workService.getById(id);
    }


    @ApiOperation("根据ID查询出一条招聘信息 需单独回显markdown文本")
    @RequestMapping(value = "/markdown/{id}", method = RequestMethod.GET)
    public String getWorkMd(@PathVariable("id")String id){
        QueryWrapper<BmlWorkContent> wrapper = new QueryWrapper<>();
        wrapper.eq("work_id",Integer.parseInt(id));
        return contentService.getOne(wrapper).getCompanyDescription();
    }


    @ApiOperation("更新一条职位信息")
    @RequestMapping(value = "/work",method = RequestMethod.PUT)
    public BmlResult update(@RequestBody BmlWorkDto workDto){
        System.out.println(workDto);
        try {
            Integer id = workService.updateWork(workDto);
            contentService.updateWorkContent(workDto,id);
            return BmlResult.ok("更新成功！");
        }catch (Exception e) {
            return BmlResult.error("更新失败！");
        }
    }













}

