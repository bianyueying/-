package bml.service;

import bml.entity.BmlLogCount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 日志统计数量表 服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
public interface BmlLogCountService extends IService<BmlLogCount> {

    /**
     * 查询最近10天的数据
     * @return 集合
     */
    List<BmlLogCount> listAll();





}
