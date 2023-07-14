package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IPermissionDao;
import com.qianfeng.domain.Permission;
import com.qianfeng.service.IPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        String s = UUID.randomUUID().toString();
        permission.setId(s);
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        List<Permission> permissionList = permissionDao.findPermissionByRoleId(id);

        return permissionList;
    }
}
