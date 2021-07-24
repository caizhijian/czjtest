package com.czj.module.tender.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czj.module.DESCBCTest;
import com.czj.module.tender.entity.TenderProject;
import com.czj.module.tender.entity.TenderSysRecord;
import com.czj.module.tender.mapper.TenderProjectMapper;
import com.czj.module.tender.mapper.TenderSysRecordMapper;
import com.czj.module.tender.service.ITenderProjectService;
import com.czj.module.tender.service.ITenderSysRecordService;
import com.czj.module.tender.util.TenderXmlUtil;
import org.apache.axis.client.Call;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Service("tenderSysRecordService")
public class TenderSysRecordSereviceImpl extends ServiceImpl<TenderSysRecordMapper, TenderSysRecord> implements ITenderSysRecordService {

    @Autowired
    private TenderSysRecordMapper tenderSysRecordMapper;

    @Override
    public Date getLastRecord() throws ParseException {
        QueryWrapper<TenderSysRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sys_end_date");

        Page<TenderSysRecord> page = new Page(1,1);
        IPage<TenderSysRecord> iPage = getBaseMapper().selectPage(page, wrapper);
        List<TenderSysRecord> records = iPage.getRecords();
        if(CollectionUtils.isEmpty(records)){
            return new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01");
        }
        TenderSysRecord tenderSysRecord = records.get(0);

        return tenderSysRecord.getSysEndDate();
    }

}
