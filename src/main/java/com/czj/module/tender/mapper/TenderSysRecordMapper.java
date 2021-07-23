package com.czj.module.tender.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czj.module.tender.entity.TenderSysRecord;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */

public interface TenderSysRecordMapper extends BaseMapper<TenderSysRecord> {

    @Select("queryLastRecord")
    Date queryLastRecord();

}
