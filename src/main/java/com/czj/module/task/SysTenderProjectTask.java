package com.czj.module.task;

import com.czj.module.tender.service.TenderProjectService;
import com.czj.module.tender.util.TenderUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Component
public class SysTenderProjectTask {

    private TenderProjectService tenderProjectService;

    @Scheduled(cron = "0 0 23 * * ?")
    public void sysTenderProject() throws Exception {
        List projectNos = tenderProjectService.getTederNoByType("-1");
        System.out.println("定时器启动");
        tenderProjectService.sysTederProject();
    }

}
