package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IOrderDao;
import com.qianfeng.domain.Orders;
import com.qianfeng.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Orders> orders=orderDao.findAll();
        return orders;
    }

    @Override
    public Orders findById(String id) {
        Orders orders=orderDao.findById(id);
        return orders;
    }
}
