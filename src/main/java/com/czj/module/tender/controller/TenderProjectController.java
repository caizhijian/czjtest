package com.czj.module.tender.controller;

import com.czj.module.tender.service.ITenderProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("tenderProjectController")
@RequestMapping("/tenderProject")
public class TenderProjectController {

    @Autowired
    private ITenderProjectService tenderProjectService;

    @RequestMapping("/sysProject")
    public String sysProject() throws Exception {
        tenderProjectService.sysTederProject();
        return "666";
    }

}
