package bml.others;

import lombok.Data;

import java.util.Date;

/**
 * @author Yuan
 * 接收表单中间类
 * 注：workNumber、workStatus如果是byte类型则接收不到数据
 */
@Data
public class BmlWorkDto {

    private Integer id;

    private String workName;

    private String workPay;

    private Integer workNumber;

    private String workPerson;

    private String workPhone;

    private String workCondition;

    private String workAddress;

    private Integer workStatus;

    private Date workDate;

    private String workDescription;

    private Long categoryId;

    private Long userId;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

    private String companyDescription;



}