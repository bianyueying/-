package bml.service;

import bml.entity.BmlLog;
import bml.entity.BmlUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-25
 */
public interface BmlUserService extends IService<BmlUser> {

    /**
     * 根据条件查询所有
     * @param page 分页
     * @param limit 页数
     * @param name 用户名
     * @param gender 性别
     * @param phone 手机
     * @param email 邮箱
     * @param time 创建时间之前
     * @return 集合
     */
    List<BmlUser> listUsers(Integer page,Integer limit,String name,String gender,String phone,String email,String time);

    /**
     * 条件查询总数
     * @param name 用户名
     * @param gender 性别
     * @param phone 手机
     * @param email 邮箱
     * @param time 创建时间之前
     * @return 总数
     */
    Integer getUserCount(String name,String gender,String phone,String email,String time);

    /**
     * 根据用户名设置登录时间
     * @param username 账号
     */
    void setLoginTime(String username);


}
