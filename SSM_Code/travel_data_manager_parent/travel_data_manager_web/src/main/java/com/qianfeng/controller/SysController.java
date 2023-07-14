package com.qianfeng.controller;

import com.github.pagehelper.PageInfo;
import com.qianfeng.domain.Product;
import com.qianfeng.domain.SysLog;
import com.qianfeng.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
@Aspect
@RequestMapping("/sysLog")
public class SysController {

    @Resource
    private ISysLogService sysLogService;

    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(* com.qianfeng.controller.*.*(..))")
    public void pt1(){}

    @Around("pt1()")
    public Object around(ProceedingJoinPoint pdj) throws Throwable {

        SysLog sysLog = new SysLog();
        sysLog.setId(UUID.randomUUID().toString());
        Date visitTime = new Date();
        sysLog.setVisitTime(visitTime);

        SecurityContext context = SecurityContextHolder.getContext();
        User principal = (User) context.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        sysLog.setUsername(username);

        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);
        Object target = pdj.getTarget();
        Class clazz = target.getClass();
        String classUrl="";
        if (clazz.isAnnotationPresent(RequestMapping.class)){
            RequestMapping classController = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            classUrl = classController.value()[0];//url
        }
        String methodUrl ="";
        String methodName = pdj.getSignature().getName();

        Object[] args = pdj.getArgs();
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i]=args[i].getClass();
        }

        Method method = clazz.getMethod(methodName,argsClass);
        if (method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping methodController = (RequestMapping) method.getAnnotation(RequestMapping.class);
            methodUrl = methodController.value()[0];//url
        }
        String url=classUrl+methodUrl;

        sysLog.setUrl(url);
        String methodname = clazz.getName()+"."+method.getName();
        sysLog.setMethod(methodname);


        Object result = pdj.proceed(args);

        long executionTime = new Date().getTime()-visitTime.getTime();
        sysLog.setExecutionTime(executionTime);
        sysLogService.addSysLog(sysLog);


        return result;
    }


    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(required = true,defaultValue = "1",value = "page") Integer page, @RequestParam(required = true,defaultValue = "5",value = "pageSize") Integer pageSize){

        List<SysLog> sysLogList=sysLogService.findAll(page,pageSize);
        ModelAndView mav = new ModelAndView();
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("syslog-list");
        return mav;
    }
}
