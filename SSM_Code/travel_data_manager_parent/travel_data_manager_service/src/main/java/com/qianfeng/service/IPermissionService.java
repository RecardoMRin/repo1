package com.qianfeng.service;

import com.qianfeng.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(Integer page, Integer pageSize);

    void save(Permission permission);

    List<Permission> findPermissionByRoleId(String id);
}
