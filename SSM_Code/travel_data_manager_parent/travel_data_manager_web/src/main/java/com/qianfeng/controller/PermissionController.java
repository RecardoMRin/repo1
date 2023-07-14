package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Permission;
import com.qianfeng.service.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = true, defaultValue = "1", value = "page") Integer page, @RequestParam(required = true, defaultValue = "3", value = "pageSize") Integer pageSize) {

        ModelAndView mav = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page,pageSize);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("permission-list");
        return mav;

    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

}
