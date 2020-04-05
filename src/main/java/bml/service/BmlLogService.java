package bml.service;

import bml.entity.BmlLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 访客所有信息表 服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-22
 */
public interface BmlLogService extends IService<BmlLog> {

    /**
     * 根据条件查询所有
     * @param page 分页
     * @param limit 页数
     * @param ip 输入ip
     * @param name 输入用户名
     * @return 集合
     */
    List<BmlLog> listAllLog(Integer page,Integer limit,String ip,String name);

    /**
     * 根据ip查询出总数
     * @param ip ip
     * @param name username
     * @return 总数
     */
    Integer getLogCount(String ip,String name);



}
