package bml.service;

import bml.entity.BmlPermissionDto;

import java.util.List;

/**
 * @author 月影
 * Date 2020/3/27 15:56
 */
public interface BmlPermissionPlusService {

    /**
     * 查询树形结构
     * @return 集合
     */
    List<BmlPermissionDto> getPermsTree();

}
