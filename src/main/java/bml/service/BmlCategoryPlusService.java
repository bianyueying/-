package bml.service;

import bml.entity.BmlCategoryDto;

import java.util.List;

/**
 * @author 月影
 * Date 2020/3/26 17:56
 */
public interface BmlCategoryPlusService {

    /**
     * 查询所有
     * @return 集合
     */
    List<BmlCategoryDto> getCategoryTree();

}
