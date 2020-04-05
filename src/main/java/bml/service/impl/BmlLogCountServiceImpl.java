package bml.service.impl;

import bml.entity.BmlLogCount;
import bml.mapper.BmlLogCountMapper;
import bml.service.BmlLogCountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日志统计数量表 服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@Service
public class BmlLogCountServiceImpl extends ServiceImpl<BmlLogCountMapper, BmlLogCount> implements BmlLogCountService {


    @Resource
    BmlLogCountMapper logCountMapper;

    @Override
    public List<BmlLogCount> listAll() {
        QueryWrapper<BmlLogCount> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        byte maxLatestCount=10;
        List<BmlLogCount> list = logCountMapper.selectList(wrapper);
        if (list.size() >= maxLatestCount) {
            List<BmlLogCount> list1 = new ArrayList<>();
            // 大于10个则返回前10个数据
            for (int i = 0; i < maxLatestCount; i++) {
                list1.add(list.get(i));
            }
            return list1;
        }
        return list;
    }


}
