package bml.service.impl;

import bml.entity.BmlLog;
import bml.mapper.BmlLogMapper;
import bml.service.BmlLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 访客所有信息表 服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-22
 */
@Service
public class BmlLogServiceImpl extends ServiceImpl<BmlLogMapper, BmlLog> implements BmlLogService {

    @Resource
    BmlLogMapper logMapper;

    @Override
    public List<BmlLog> listAllLog(Integer page, Integer limit, String ip, String name) {
        PageHelper.startPage(page,limit);
        QueryWrapper<BmlLog> wrapper = new QueryWrapper<>();
        if (ip != null) {
            wrapper.like("ip",ip);
        }
        if (name != null) {
            wrapper.like("username",name);
        }
        return logMapper.selectList(wrapper);
    }

    @Override
    public Integer getLogCount(String ip,String name) {
        QueryWrapper<BmlLog> wrapper = new QueryWrapper<>();
        if (ip != null) {
            wrapper.like("ip",ip);
        }
        if (name != null) {
            wrapper.like("username",name);
        }
        return logMapper.selectCount(wrapper);
    }

}
