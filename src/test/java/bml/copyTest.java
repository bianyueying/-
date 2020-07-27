package bml;

import bml.entity.BmlUser;
import bml.mapper.BmlUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 月影
 * Date 2020/3/23 0:54
 */
@SpringBootTest
public class copyTest {

    @Resource
    BmlUserMapper userMapper;

    @Test
    void copy(){
        QueryWrapper<BmlUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username","123444");
        BmlUser user = userMapper.selectOne(wrapper);
        System.out.println(user == null);

    }




}
