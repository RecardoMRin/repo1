package com.qianfeng.service;

import com.qianfeng.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(Integer page, Integer pageSize);

    void save(Role role);

    List<Role> findRoleByUserId(String id);

    Role findById(String id);
}
