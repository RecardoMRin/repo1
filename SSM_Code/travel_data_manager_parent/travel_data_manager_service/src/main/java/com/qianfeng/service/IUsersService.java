package com.qianfeng.service;


import com.qianfeng.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsersService extends UserDetailsService {

    List<UserInfo> findAll(Integer page, Integer pageSize);

    void save(UserInfo userInfo);

    UserInfo findById(String id);
}
