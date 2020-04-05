package bml.controller;


import bml.entity.BmlLog;
import bml.entity.BmlLogCount;
import bml.others.BmlResult;
import bml.service.BmlLogCountService;
import bml.service.BmlLogService;
import bml.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访客所有信息表 前端控制器
 * </p>
 *
 * @author 月影
 * @since 2020-03-22
 */
@RestController
@RequestMapping("/admin")
public class BmlLogController {

    @Resource
    BmlLogService logService;

    @ApiOperation("返回所有登录日志信息")
    @RequestMapping(value = "/sys/logs",method = RequestMethod.GET)
    public Map<String, Object> listAllLog(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,String username, String ip) {
        List<BmlLog> bmlLogs = logService.listAllLog(page,limit,ip,username);
        Map<String, Object> resultMap = new HashMap<>(1000);
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",logService.getLogCount(ip,username));
        resultMap.put("data",bmlLogs);
        return resultMap;
    }

    @ApiOperation("根据主键ID删除一条日志信息")
    @RequestMapping(value = "/sys/log",method = RequestMethod.DELETE)
    public BmlResult delete(@RequestParam("id") String id){
        try {
            logService.removeById(Long.parseLong(id));
            return BmlResult.ok("删除成功");
        } catch (Exception e) {
            return BmlResult.error("删除失败");
        }
    }

    @Resource
    BmlLogCountService logCountService;

    @ApiOperation("返回最近十天的日志统计")
    @RequestMapping(value = "/sys/log",method = RequestMethod.GET)
    public List<BmlLogCount> getBmlLogCounts() {
        List<BmlLogCount> list = logCountService.listAll();
        //反转集合中的元素
        Collections.reverse(list);
        for (BmlLogCount bmlLogCount : list) {
            //把集合中的日期格式转换 以适应前端展示
            bmlLogCount.setSomeDay(StringUtil.getFitDate(bmlLogCount.getSomeDay()));
        }
        return list;
    }



}

