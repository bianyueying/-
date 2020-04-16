package bml.controller;


import bml.entity.BmlLog;
import bml.entity.BmlLogCount;
import bml.others.BmlResult;
import bml.service.BmlLogCountService;
import bml.service.BmlLogService;
import bml.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    BmlLogCountService logCountService;

    @ApiOperation("返回所有登录日志信息")
    @GetMapping("/sys/logs")
    public BmlResult<Object> listLogs(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,String username, String ip) {
        List<BmlLog> bmlLogs = logService.listAllLog(page,limit,ip,username);
        Map<String, Object> resultMap = new HashMap<>(10000);
        resultMap.put("count",logService.getLogCount(ip,username));
        resultMap.put("data",bmlLogs);
        return new BmlResult<>(resultMap,0);
    }

    @ApiOperation("根据主键ID删除一条日志信息")
    @DeleteMapping("/sys/log")
    public BmlResult<Object> delete(@RequestParam("id") String id){
        try {
            logService.removeById(Long.parseLong(id));
            return new BmlResult<>(200,"删除成功");
        } catch (Exception e) {
            return new BmlResult<>(400,"删除失败");
        }
    }

    @ApiOperation("返回最近十天的日志统计")
    @GetMapping("/sys/log")
    public BmlResult<Object> getBmlLogCounts() {
        List<BmlLogCount> list = logCountService.listAll();
        //反转集合中的元素
        Collections.reverse(list);
        for (BmlLogCount bmlLogCount : list) {
            //把集合中的日期格式转换 以适应前端展示
            bmlLogCount.setSomeDay(StringUtil.getFitDate(bmlLogCount.getSomeDay()));
        }
        Map<String, Object> resultMap = new HashMap<>(10000);
        resultMap.put("data",list);
        return new BmlResult<>(resultMap,0);
    }
}

