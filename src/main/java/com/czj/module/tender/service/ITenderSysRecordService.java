package com.czj.module.tender.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czj.module.tender.entity.TenderSysRecord;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
public interface ITenderSysRecordService extends IService<TenderSysRecord> {

    Date getLastRecord() throws ParseException;

}
