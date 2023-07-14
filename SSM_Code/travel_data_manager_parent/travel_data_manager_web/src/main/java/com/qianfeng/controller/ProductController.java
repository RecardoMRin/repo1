package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import com.qianfeng.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.List;

/*
/find GET 查找所有
/find/1 PUT 根据ID查询
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private IProductService productService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(required = true,defaultValue = "1",value = "page") Integer page, @RequestParam(required = true,defaultValue = "5",value = "pageSize") Integer pageSize){

        List<Product> productList=productService.findAll(page,pageSize);
        ModelAndView mav = new ModelAndView();
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("product-list");
        return mav;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String addProduct(Product product){
        productService.addProduct(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public ModelAndView findById(String id){
        Product product = productService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("product",product);
        mav.setViewName("product-update");
        return mav;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Product product){
        productService.updateProduct(product);
        return "redirect:/product/findAll";
    }


    @RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
    public String deleteByIds(String[] ids){
        productService.deleteByIds(ids);
        return "redirect:/product/findAll";
    }
}
