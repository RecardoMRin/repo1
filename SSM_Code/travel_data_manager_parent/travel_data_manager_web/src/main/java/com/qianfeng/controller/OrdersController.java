package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IOrderService;
import com.qianfeng.service.IProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private IOrderService orderService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(required = true,defaultValue = "1",value = "page") Integer page, @RequestParam(required = true,defaultValue = "3",value = "pageSize") Integer pageSize){

        List<Orders> orders = orderService.findAll(page, pageSize);
        ModelAndView mav = new ModelAndView();
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public ModelAndView findById(String id){
        ModelAndView mav = new ModelAndView();
        Orders orders = orderService.findById(id);
        mav.addObject("orders",orders);
        mav.setViewName("orders-show");
        return mav;
    }
}
