package com.czj.module.tender.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 项目同步日志表
 */
@Data
@TableName("tender_sys_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenderSysLog {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 同步id（tender_sys_record的id）
     */
    private String sysId;

    /**
     * 项目编号
     */
    private String tenderNo;

    /**
     * 同步结果
     */
    private Boolean sysResult;

    /**
     * 同步日志(异常时，报错异常日志)
     */
    private String sysResultLog;

    /**
     * 响应的数据
     */
    private String sysResultData;

    /**
     * 创建时间
     */
    private Date createTime;

}
