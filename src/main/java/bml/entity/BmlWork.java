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
 * 职位信息表
 * </p>
 *
 * @author 月影
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BmlWork对象", description="职位信息表")
public class BmlWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职位名")
    private String workName;

    @ApiModelProperty(value = "薪资")
    private String workPay;

    @ApiModelProperty(value = "招聘的人数")
    private Integer workNumber;

    @ApiModelProperty(value = "联系人")
    private String workPerson;

    @ApiModelProperty(value = "联系方式")
    private String workPhone;

    @ApiModelProperty(value = "招聘条件")
    private String workCondition;

    @ApiModelProperty(value = "招聘地址")
    private String workAddress;

    @ApiModelProperty(value = "招聘是否发布 0发布、1不发布")
    private Integer workStatus;

    @ApiModelProperty(value = "职位截止时间")
    private Date workDate;

    @ApiModelProperty(value = "职位描述")
    private String workDescription;

    @ApiModelProperty(value = "职位分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "逻辑删除 默认为0，1为已删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "职位添加时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "职位更改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
