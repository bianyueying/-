package bml.service;

import bml.entity.BmlRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色相关信息表 服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-27
 */
public interface BmlRoleService extends IService<BmlRole> {

    /**
     * 查询所有角色
     * @param page 页数
     * @param limit limit
     * @param name 用户输入的角色名 用于条件查询
     * @return 集合
     */
    List<BmlRole> listRoles(int page, int limit, String name);


    /**
     * 获取总数
     * @param name 用户输入的角色名 用于条件查询
     * @return int
     */
    int getRoleCount(String name);

}
