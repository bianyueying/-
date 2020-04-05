package bml.service.impl;

import bml.mapper.BmlCategoryPlusMapper;
import bml.entity.BmlCategoryDto;
import bml.service.BmlCategoryPlusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 月影
 * Date 2020/3/26 17:57
 */
@Service
public class BmlCategoryPlusServiceImpl implements BmlCategoryPlusService {

    @Resource
    BmlCategoryPlusMapper categoryPlusMapper;

    @Override
    public List<BmlCategoryDto> getCategoryTree() {
        // 原始的数据
        List<BmlCategoryDto> list = categoryPlusMapper.categoryList();

        // 最后的结果
        List<BmlCategoryDto> categoryList = new ArrayList<>();

        for (BmlCategoryDto categoryDto : list) {
            // 一级菜单没有parentId
            if (categoryDto.getParentId() == 0) {
                categoryList.add(categoryDto);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (BmlCategoryDto b : categoryList) {
            //获取所有子菜单 递归
            List<BmlCategoryDto> childrenList = getChild(b.getId().intValue(),list);
            b.setChildren(childrenList);
        }
        return categoryList;
    }

    private List<BmlCategoryDto> getChild(Integer id, List<BmlCategoryDto> list) {
        List<BmlCategoryDto> childrenList = new ArrayList<>();
        for (BmlCategoryDto categoryDto : list) {
            //判断当前的菜单标识是否等于主菜单的id
            if (categoryDto.getParentId().equals(id)) {
                childrenList.add(categoryDto);
            }
        }
        //递归了，遍历所有的子菜单
        for (BmlCategoryDto categoryDto:childrenList){
            categoryDto.setChildren(getChild(categoryDto.getId().intValue(),list));
        }
        if (childrenList.size() == 0) {
            return null;
        }
        return childrenList;
    }

}
