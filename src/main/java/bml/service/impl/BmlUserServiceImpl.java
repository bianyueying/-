package bml.service.impl;

import bml.entity.BmlUser;
import bml.mapper.BmlUserMapper;
import bml.service.BmlUserService;
import bml.util.Md5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-21
 */
@Service
public class BmlUserServiceImpl extends ServiceImpl<BmlUserMapper, BmlUser> implements BmlUserService {

    @Resource
    BmlUserMapper userMapper;

    @Override
    public List<BmlUser> listUsers(Integer page,Integer limit,String name,String gender,String phone,String email,String time) {
        PageHelper.startPage(page,limit);
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("username",name);
        }
        //此处需考虑未选择性别的情况 即gender=""
        if (gender != null && !"".equals(gender)) {
            wrapper.eq("gender",Integer.parseInt(gender));
        }
        if (phone != null) {
            wrapper.like("phone",phone);
        }
        if (email != null) {
            wrapper.like("email",email);
        }
        if (time != null && time.length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(time);
                wrapper.le("create_time",date);
            } catch (ParseException e) {
                System.out.println("时间转换异常");
            }
        }
        return userMapper.selectList(wrapper);
    }

    @Override
    public Integer getUserCount(String name,String gender,String phone,String email,String time) {
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("username",name);
        }
        if (gender != null && !"".equals(gender)) {
            wrapper.eq("gender",Integer.parseInt(gender));
        }
        if (phone != null) {
            wrapper.like("phone",phone);
        }
        if (email != null) {
            wrapper.like("email",email);
        }
        if (time != null && time.length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(time);
                wrapper.le("create_time",date);
            } catch (ParseException e) {
                System.out.println("时间转换异常");
            }
        }
        return userMapper.selectCount(wrapper);
    }

    @Override
    public void setLoginTime(String username) {
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        BmlUser user = userMapper.selectOne(wrapper);
        user.setLastTime(new Date());
        userMapper.updateById(user);
    }


}
