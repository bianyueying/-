package bml.config;

import bml.entity.BmlLog;
import bml.entity.BmlLogCount;
import bml.service.BmlLogCountService;
import bml.service.BmlLogService;
import bml.util.DateFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 月影
 * Date 2020/3/3 20:07
 * 秒 分 时
 * 使用定时器别忘了在启动类上加注解 @EnableScheduling
 */
@Component
public class ScheduleTask {

    @Resource
    BmlLogCountService logCountService;

    @Resource
    BmlLogService logService;

    @ApiOperation("每天零时零分一秒时添加一条记录到数据库")
    @Scheduled(cron = "01 00 00 * * ?")
    public void addCount(){
        QueryWrapper<BmlLogCount> wrapper = new QueryWrapper<>();
        wrapper.eq("some_day",DateFormatUtil.getStringTime(new Date()));
        BmlLogCount count = logCountService.getOne(wrapper);
        if (count == null) {
            BmlLogCount logCount = new BmlLogCount();
            logCount.setSomeDay(DateFormatUtil.getStringTime(new Date()));
            //初始访问记录设置为0 这里无所谓
            logCount.setRecordCount(0);
            logCountService.save(logCount);
        }
    }

    @ApiOperation("每隔5秒更新数据库 便于前端实时展示")
    @Scheduled(cron = "*/5 * * * * ?")
    public void updateCount(){
        addCount();
        QueryWrapper<BmlLogCount> wrapper = new QueryWrapper<>();
        wrapper.eq("some_day",DateFormatUtil.getStringTime(new Date()));
        BmlLogCount count = logCountService.getOne(wrapper);
        QueryWrapper<BmlLog> logQueryWrapper = new QueryWrapper<>();
        logQueryWrapper.ge("create_time",DateFormatUtil.getStartTime());
        //获取今天的记录数
        count.setRecordCount(logService.count(logQueryWrapper));
        logCountService.updateById(count);
    }

}
