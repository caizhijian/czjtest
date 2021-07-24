package com.czj.module.tender.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czj.module.tender.entity.TenderSysLog;
import com.czj.module.tender.mapper.TenderSysLogMapper;
import com.czj.module.tender.service.ITenderSysLogService;
import org.springframework.stereotype.Service;

/**
 * @Author:caizhijian
 * @Date:2021-07-24
 */
@Service("tenderSysLogService")
public class TenderSysLogSereviceImpl extends ServiceImpl<TenderSysLogMapper, TenderSysLog> implements ITenderSysLogService {

}
