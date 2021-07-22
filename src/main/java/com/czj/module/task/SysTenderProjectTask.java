package com.czj.module.task;

import com.czj.module.tender.entity.TenderSysRecord;
import com.czj.module.tender.service.ITenderProjectService;
import com.czj.module.tender.service.ITenderSysRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

    @Scheduled(cron = "0 */2 * * * ?")
    public void sysTenderProject() throws Exception {
        System.out.println("定时器启动");
        Date lastRecord = tenderSysRecordService.getLastRecord();
        Instant instant = lastRecord.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate lastDate = localDateTime.toLocalDate();
        LocalDate start = lastDate.plusDays(1);
        LocalDate end = lastDate.plusDays(30);
        tenderProjectService.sysTenderProject(start.toString(),end.toString());

        TenderSysRecord record = new TenderSysRecord();
        Instant startInstant = start.atStartOfDay().atZone(zone).toInstant();
        Instant endInstant = start.atStartOfDay().atZone(zone).toInstant();

        record.setStartDate(Date.from(startInstant));
        record.setEndDate(Date.from(endInstant));
        tenderSysRecordService.save(record);
        System.out.println("完成同步");
    }

}
