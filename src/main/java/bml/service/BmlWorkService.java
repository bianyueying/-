package bml.service;

import bml.entity.BmlWork;
import bml.others.BmlWorkDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 职位信息表 服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
public interface BmlWorkService extends IService<BmlWork> {

    /**
     * 添加一条职位信息
     * @param bmlWorkDto 中间接收类
     * @return 最新的ID
     */
    Integer addWork(BmlWorkDto bmlWorkDto);

    /**
     * 更新一条职位信息
     * @param bmlWorkDto 中间接收类
     * @return 更新的ID
     */
    Integer updateWork(BmlWorkDto bmlWorkDto);

    /**
     * 条件查询
     * @param name 职位名
     * @param address 工作地址
     * @param time 截止日期之前
     * @return 数量
     */
    long getWorkCount(String name,String address,String time);

    /**
     * 获取所有职位信息 用于前端展示
     * @param page 页数
     * @param limit 每页有多少记录
     * @param address 工作地址
     * @param time 截止日期之前
     * @param name 职位名
     * @return list集合
     */
    List<BmlWork> listAllWork(int page, int limit, String name, String address, String time);

}
