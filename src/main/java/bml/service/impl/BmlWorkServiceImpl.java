package bml.service.impl;

import bml.entity.BmlUser;
import bml.entity.BmlWork;
import bml.mapper.BmlWorkMapper;
import bml.others.BmlWorkDto;
import bml.service.BmlWorkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 职位信息表 服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@Service
public class BmlWorkServiceImpl extends ServiceImpl<BmlWorkMapper, BmlWork> implements BmlWorkService {

    @Resource
    BmlWorkMapper workMapper;

    @Override
    public Integer addWork(BmlWorkDto bmlWorkDto) {
        BmlWork work = new BmlWork();
        BeanUtils.copyProperties(bmlWorkDto,work);
        //添加对象
        workMapper.insert(work);
        //返回最新的自增ID
        return work.getId();
    }

    @Override
    public Integer updateWork(BmlWorkDto bmlWorkDto) {
        BmlWork work = new BmlWork();
        BeanUtils.copyProperties(bmlWorkDto,work);
        workMapper.updateById(work);
        return work.getId();
    }

    @Override
    public long getWorkCount(String name, String address, String time) {
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();
        QueryWrapper<BmlWork> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        if (name != null) {
            wrapper.like("work_name",name);
        }
        if (address != null) {
            wrapper.like("work_address",address);
        }
        if (time != null && time.length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(time);
                wrapper.le("work_date",date);
            } catch (ParseException e) {
                System.out.println("时间转换异常");
            }
        }
        return workMapper.selectCount(wrapper);
    }

    @Override
    public List<BmlWork> listAllWork(int page, int limit, String name, String address, String time) {
        // 获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        BmlUser user= (BmlUser) subject.getPrincipal();

        PageHelper.startPage(page,limit);
        QueryWrapper<BmlWork> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        if (name != null) {
            wrapper.like("work_name",name);
        }
        if (address != null) {
            wrapper.like("work_address",address);
        }
        if (time != null && time.length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(time);
                wrapper.le("work_date",date);
            } catch (ParseException e) {
                System.out.println("时间转换异常");
            }
        }
        return workMapper.selectList(wrapper);
    }


}
