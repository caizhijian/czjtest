package com.czj.module.tender.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czj.module.tender.entity.TenderProject;

import java.util.List;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
public interface ITenderProjectService extends IService<TenderProject> {

    /**
     * 获取项目编号
     * @param startStr 开始日期
     * @param endStr 结束日期
     * @param type 类型
     * @return
     * @throws Exception
     */
    List<String> getTenderNoByType(String startStr, String endStr, String type) throws Exception;

    /**
     * 同步项目信息
     * @param start 开始时间
     * @param end 结束时间
     * @throws Exception
     */
    void sysTenderProject(String start ,String end) throws Exception;

}
