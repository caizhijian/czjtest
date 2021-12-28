package com.czj.module.tender.controller;

import com.czj.module.tender.service.ITenderProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("tenderProjectController")
@RequestMapping("/tenderProject")
public class TenderProjectController {

    @Autowired
    private ITenderProjectService tenderProjectService;

    @RequestMapping("/hello")
    //@Async
    public String hello() throws Exception {
        System.out.println("hello");
        return "666";
    }

    @RequestMapping("/sysProject")
    @Async
    public void sysProject(@RequestParam String start,String end) throws Exception {
        tenderProjectService.sysTenderProject(start,end);
        System.out.println("===============================================");
        System.out.println("同步完成");
    }


}
