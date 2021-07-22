package com.czj.module.task;

import com.czj.module.tender.service.ITenderProjectService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Component
public class SysTenderProjectTask {

    private ITenderProjectService tenderProjectService;

    @Scheduled(cron = "0 0 23 * * ?")
    public void sysTenderProject() throws Exception {
        System.out.println("定时器启动");
        tenderProjectService.sysTenderProject("2018-01-01","2018-05-31");
    }

}
