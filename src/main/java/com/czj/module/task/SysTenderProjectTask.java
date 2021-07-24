package com.czj.module.task;

import com.czj.module.tender.entity.TenderSysRecord;
import com.czj.module.tender.service.ITenderProjectService;
import com.czj.module.tender.service.ITenderSysRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Component
public class SysTenderProjectTask {

    @Autowired
    private ITenderProjectService tenderProjectService;

    @Autowired
    private ITenderSysRecordService tenderSysRecordService;

//    @Scheduled(cron = "0 */2 * * * ?")
    //一分钟同步一次
    @Scheduled(fixedDelay = 1*60*1000)
        public void sysTenderProject() throws Exception {
        System.out.println("定时器启动");
        LocalDate of = LocalDate.of(2021, 06, 30);
        Date lastRecord = tenderSysRecordService.getLastRecord();
        if(null==lastRecord){
            lastRecord = new SimpleDateFormat("yyyy-MM-dd").parse("2017-12-31");
        }
        Instant instant = lastRecord.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate lastDate = localDateTime.toLocalDate();
        if(lastDate.isAfter(LocalDate.of(2021, 6, 30))){
            return;
        }

        LocalDate start = lastDate.plusDays(1);
        LocalDate end = lastDate.plusDays(30);
        if(end.isAfter(of)){
            end = of;
        }
        tenderProjectService.sysTenderProject(start.toString(),end.toString());

        TenderSysRecord record = new TenderSysRecord();
        Instant startInstant = start.atStartOfDay().atZone(zone).toInstant();
        Instant endInstant = end.atStartOfDay().atZone(zone).toInstant();

        record.setSysStartDate(Date.from(startInstant));
        record.setSysEndDate(Date.from(endInstant));
        tenderSysRecordService.save(record);
        System.out.println("完成同步");
    }

}
