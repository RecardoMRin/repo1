package com.qianfeng.service;

import com.qianfeng.domain.Orders;

import java.util.List;

public interface IOrderService {

    List<Orders> findAll(Integer page, Integer pageSize);

    Orders findById(String id);
}
