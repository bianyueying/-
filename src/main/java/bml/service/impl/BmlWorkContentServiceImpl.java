package bml.service.impl;

import bml.entity.BmlWorkContent;
import bml.mapper.BmlWorkContentMapper;
import bml.others.BmlWorkDto;
import bml.service.BmlWorkContentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 职位文本表 服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@Service
public class BmlWorkContentServiceImpl extends ServiceImpl<BmlWorkContentMapper, BmlWorkContent> implements BmlWorkContentService {

    @Resource
    BmlWorkContentMapper contentMapper;

    @Override
    public void addWorkContent(BmlWorkDto bmlWorkDto,Integer id) {
        BmlWorkContent content = new BmlWorkContent();
        //设置文本内容
        BeanUtils.copyProperties(bmlWorkDto,content);
        // 转换类型
        content.setWorkId(id.longValue());
        contentMapper.insert(content);
    }

    @Override
    public void updateWorkContent(BmlWorkDto bmlWorkDto, Integer id) {
        QueryWrapper<BmlWorkContent> wrapper = new QueryWrapper<>();
        wrapper.eq("work_id",id);
        BmlWorkContent content = contentMapper.selectOne(wrapper);
        content.setCompanyDescription(bmlWorkDto.getCompanyDescription());
        contentMapper.updateById(content);
    }


}
