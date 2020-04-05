package bml.mapper;

import bml.entity.BmlPermissionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 月影
 * Date 2020/3/27 15:47
 */
@Mapper
public interface BmlPermissionPlusMapper {

    /**
     * 查询出树形结构
     * @return 集合
     */
    List<BmlPermissionDto> getPermsTree();


}
