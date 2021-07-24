package com.czj.module.tender.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czj.module.tender.entity.TenderNo;
import com.czj.module.tender.mapper.TenderNoMapper;
import com.czj.module.tender.service.ITenderNoService;
import org.springframework.stereotype.Service;

/**
 * @Author:caizhijian
 * @Date:2021-07-24
 */
@Service("tenderNoService")
public class TenderNoSereviceImpl extends ServiceImpl<TenderNoMapper, TenderNo> implements ITenderNoService {

}
