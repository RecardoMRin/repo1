package com.qianfeng.controller;


import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Product;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IRoleService;
import com.qianfeng.service.IUsersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUsersService usersService;

    @Resource
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = true, defaultValue = "1", value = "page") Integer page, @RequestParam(required = true, defaultValue = "3", value = "pageSize") Integer pageSize) {
        List<UserInfo> userInfos = usersService.findAll(page, pageSize);
        ModelAndView mav = new ModelAndView();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("user-list");
        return mav;
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    @RolesAllowed("USER")
//    @RolesAllowed("ADMIN")
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")//spel表达式
//    @PreAuthorize("authentication.principal.username=='zhishikeng'")
    public ModelAndView findById(String id){
        UserInfo userInfo = usersService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",userInfo);
        mav.setViewName("user-show");
        return mav;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(UserInfo userInfo){
        usersService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping(value = "/findUserByIdAndAllRole",method = RequestMethod.GET)
    public ModelAndView findUserByIdAndAllRole(String id){
        UserInfo userInfo = usersService.findById(id);
        List<Role> roleList = roleService.findRoleByUserId(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",userInfo);
        mav.addObject("roleList",roleList);
        mav.setViewName("user-role-add");
        return mav;
    }


}
