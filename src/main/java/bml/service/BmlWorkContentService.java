package bml.service;

import bml.entity.BmlWorkContent;
import bml.others.BmlWorkDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 职位文本表 服务类
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
public interface BmlWorkContentService extends IService<BmlWorkContent> {

    /**
     * 添加职位文章
     * @param bmlWorkDto 中间接收类
     * @param id 关联的主键
     */
    void addWorkContent(BmlWorkDto bmlWorkDto,Integer id);

    /**
     * 更新职位文章
     * @param bmlWorkDto 中间接收类
     * @param id 关联的主键
     */
    void updateWorkContent(BmlWorkDto bmlWorkDto,Integer id);

}
