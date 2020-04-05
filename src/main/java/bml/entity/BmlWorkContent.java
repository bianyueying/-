package bml.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职位文本表
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BmlWorkContent对象", description="职位文本表")
public class BmlWorkContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联职位ID")
    private Long workId;

    @ApiModelProperty(value = "逻辑删除 默认为0,1为删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "公司介绍")
    private String companyDescription;

    @ApiModelProperty(value = "职位添加时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "职位更改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
