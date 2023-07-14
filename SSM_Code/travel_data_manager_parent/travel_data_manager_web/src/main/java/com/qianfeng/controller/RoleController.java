package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Permission;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IPermissionService;
import com.qianfeng.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = true, defaultValue = "1", value = "page") Integer page, @RequestParam(required = true, defaultValue = "3", value = "pageSize") Integer pageSize) {

        ModelAndView mav = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,pageSize);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("role-list");
        return mav;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping(value = "/findRoleByIdAndAllPermission",method = RequestMethod.GET)
    public ModelAndView findRoleByIdAndAllPermission(String id){
        Role role = roleService.findById(id);
        List<Permission> permissionList = permissionService.findPermissionByRoleId(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("role",role);
        mav.addObject("permissionList",permissionList);
        mav.setViewName("role-permission-add");
        return mav;
    }

}