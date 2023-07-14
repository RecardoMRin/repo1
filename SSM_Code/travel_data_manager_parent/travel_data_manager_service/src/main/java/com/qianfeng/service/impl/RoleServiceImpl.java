package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IRoleDao;
import com.qianfeng.domain.Role;
import com.qianfeng.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        String s = UUID.randomUUID().toString();
        role.setId(s);
        roleDao.save(role);
    }

    @Override
    public List<Role> findRoleByUserId(String id) {
        List<Role> roleList = roleDao.findRoleByUserId(id);

        return roleList;
    }

    @Override
    public Role findById(String id) {
        Role role = roleDao.findById(id);
        return role;
    }
}
