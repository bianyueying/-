package bml.service.impl;

import bml.entity.BmlRole;
import bml.mapper.BmlRoleMapper;
import bml.service.BmlRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色相关信息表 服务实现类
 * </p>
 *
 * @author 月影
 * @since 2020-03-27
 */
@Service
public class BmlRoleServiceImpl extends ServiceImpl<BmlRoleMapper, BmlRole> implements BmlRoleService {

    @Resource
    BmlRoleMapper roleMapper;


    @Override
    public List<BmlRole> listRoles(int page, int limit, String name) {
        PageHelper.startPage(page,limit);
        QueryWrapper<BmlRole> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("role_name",name);
        }
        return roleMapper.selectList(wrapper);
    }

    @Override
    public int getRoleCount(String name) {
        QueryWrapper<BmlRole> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.like("role_name",name);
        }
        return roleMapper.selectCount(wrapper);
    }

}
