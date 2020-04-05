package bml.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 月影
 * Date 2020/3/26 17:16
 * 处理分类的特别类
 */
@Data
public class BmlCategoryDto {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名")
    private String title;

    @ApiModelProperty(value = "父节点ID")
    private Integer parentId;

    @ApiModelProperty(value = "节点排列顺序")
    private Integer orderBy;

    @ApiModelProperty(value = "逻辑删除 默认为0，1为已删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private List<BmlCategoryDto> children;

}
