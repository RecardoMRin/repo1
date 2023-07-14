package com.qianfeng.service;

import com.qianfeng.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void addSysLog(SysLog sysLog);

    List<SysLog> findAll(Integer page, Integer pageSize);
}
