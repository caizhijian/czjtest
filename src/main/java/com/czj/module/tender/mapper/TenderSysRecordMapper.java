package com.czj.module.tender.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czj.module.tender.entity.TenderSysRecord;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Repository
public interface TenderSysRecordMapper extends BaseMapper<TenderSysRecord> {
    Date queryLastRecord(String a);
}
