package com.czj.module.tender.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czj.module.tender.entity.TenderProject;

import java.util.List;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
public interface ITenderProjectService extends IService<TenderProject> {

    List getTederNoByType(String type) throws Exception;

    void sysTederProject() throws Exception;

}
