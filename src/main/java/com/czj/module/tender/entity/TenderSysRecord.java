package com.czj.module.tender.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author:caizhijian
 * @Date:2021-07-22
 */
@Data
@TableName("tender_sys_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenderSysRecord {


    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 项目数
     */
    private Integer projectNum;

}
