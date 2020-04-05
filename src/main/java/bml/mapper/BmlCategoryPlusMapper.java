package bml.mapper;

import bml.entity.BmlCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 月影
 * Date 2020/3/26 17:47
 */
@Mapper
public interface BmlCategoryPlusMapper {

    /**
     * 查询所有
     * @return 集合
     */
    List<BmlCategoryDto> categoryList();

}
