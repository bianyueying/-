package bml.service.impl;

import bml.entity.BmlPermissionDto;
import bml.mapper.BmlPermissionPlusMapper;
import bml.service.BmlPermissionPlusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 月影
 * Date 2020/3/27 15:57
 */
@Service
public class BmlPermissionPlusServiceImpl implements BmlPermissionPlusService {

    @Resource
    BmlPermissionPlusMapper permissionPlusMapper;

    @Override
    public List<BmlPermissionDto> getPermsTree() {
        //原始的数据
        List<BmlPermissionDto> before = permissionPlusMapper.getPermsTree();
        //最后的结果
        List<BmlPermissionDto> after = new ArrayList<>();
        for (BmlPermissionDto bml: before) {
            if (bml.getParentId() == 0) {
                after.add(bml);
            }
        }
        for (BmlPermissionDto bml : after) {
            List<BmlPermissionDto> childrenList = getChild(bml.getId().intValue(), before);
            bml.setChildren(childrenList);
        }
        return after;
    }


    /**
     * 一直看不懂这个方法 尴尬
     */
    private List<BmlPermissionDto> getChild(Integer id, List<BmlPermissionDto> list) {
        List<BmlPermissionDto> childrenList = new ArrayList<>();
        for (BmlPermissionDto permissionDto : list) {
            if (permissionDto.getParentId().equals(id)) {
                childrenList.add(permissionDto);
            }
        }
        for (BmlPermissionDto permissionDto : childrenList) {
            permissionDto.setChildren(getChild(permissionDto.getId().intValue(),list));
        }
        if (childrenList.size() == 0) {
            return null;
        }
        return childrenList;
    }

}
