package com.czj.module.tender.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目编号实体
 */
@Data
@TableName("tender_no")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenderNo {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 项目编号
     */
    private String tenderNo;

    /**
     * 数据类型
     *
     * 1:项目基本信息；
     * -2:标录数据；
     * 2：招标公告；
     * 11：中标前公示；
     * 17：答疑公告；
     * 18：中标公告
     */
    private String dataType;



}
