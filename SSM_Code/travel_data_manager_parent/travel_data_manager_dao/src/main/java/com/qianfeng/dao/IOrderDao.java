package com.qianfeng.dao;

import com.qianfeng.domain.Member;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDao {

    @Results(id = "baseResult1",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(fetchType = FetchType.EAGER,select = "com.qianfeng.dao.IProductDao.findById")),
    })
    @Select("select * from orders")
    List<Orders> findAll();

    @Results(id = "baseResult2",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(fetchType = FetchType.EAGER,select = "com.qianfeng.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(fetchType = FetchType.EAGER,select = "com.qianfeng.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(fetchType = FetchType.LAZY,select = "com.qianfeng.dao.ITravellerDao.findByOrdersId")),
    })
    @Select("select * from orders where id=#{id}")
    Orders findById(String id);
}
