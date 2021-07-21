package com.czj.module.tender.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("tender_project")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenderProject {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 项目编号
     */
    private String tenderNo;

    /**
     * 招标项目编号
     */
    private String projectApprovalNo;

    /**
     * 项目名称
     */
    private String tenderName;

    /**
     * 投资项目统一代码
     */
    private String investProjectCode;

    /**
     * 项目所属地区
     */
    private String in_area;

    /**
     * 项目地址(土地位置)
     */
    private String proAddr;

    /**
     * 招标人名称
     */
    private String tendererName;

    /**
     * 招标人代码
     */
    private String tendererCode;

    /**
     * 招标人法人
     */
    private String tendererLegalRepresent;

    /**
     * 招标单位地址
     */
    private String tendererAddress;

    /**
     * 招标人联系人
     */
    private String linkMan;

    /**
     * 招标人联系电话
     */
    private String linkPhone;

    /**
     * 招标代理机构名称
     */
    private String delegateName;

    /**
     * 招标代理人员
     */
    private String delegateLinkMan;

    /**
     * 招标代理人员身份证号
     */
    private String delegateLinkManIdcard;

    /**
     * 代理机构联系电话
     */
    private String delegateLinkMobilePhone;

    /**
     * 招标组织形式
     */
    private Integer organMode;

    /**
     * 招标方式
     */
    private Integer inviteMode;

    /**
     * 资格审查方式
     */
    private Integer qualCheckMode;

    /**
     * 是否政府投资
     */
    private Boolean government;

    /**
     * 是否重点工程
     */
    private Boolean isKey;

    /**
     * 是否小型项目
     */
    private Boolean isSmall;

    /**
     * 是否保密
     */
    private Boolean IsSecret;

    /**
     * 建设性质
     */
    private String constructProperty;

    /**
     * 资金性质
     */
    private String fundResource;

    /**
     * 投标保证金
     */
    private Float bidGuaranteePrice;

    /**
     * 项目批文名称
     */
    private String approvalName;

    /**
     * 投资总额
     */
    private Float investment;

    /**
     * 本期概算
     */
    private Float thisBudget;

    /**
     * 主管部门
     */
    private String manageDept;

    /**
     * 主管部门地址
     */
    private String manageDeptAddr;

    /**
     * 主管部门电话
     */
    private String manageDeptPhone;

    /**
     * 项目建立时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 允许联合体报名
     */
    private Boolean IsUnion;

    /**
     * 招标类型
     */
    private Integer bidClass;

    /**
     * 项目类型
     */
    private Integer proClass;

    /**
     * 项目类别
     */
    private Integer proType;

    /**
     * 项目分类
     */
    private Integer proSubType;

    /**
     * 控制价编制金额
     */
    private BigDecimal controlPrice;

    /**
     * 投标人资格条件
     */
    private String bidQualification;

    /**
     * 工期要求
     */
    private String time;

    /**
     * 质量要求
     */
    private String quality;

    /**
     * 监理项目等级
     */
    private String watchLevel;

    /**
     * 企业等级要求(招标代理比选)
     */
    private String deleEnterLevel;

    /**
     * 人员等级要求(招标代理比选)
     */
    private String delePersonLevel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 采用网上招投标
     */
    private Boolean bidOnline;


}
