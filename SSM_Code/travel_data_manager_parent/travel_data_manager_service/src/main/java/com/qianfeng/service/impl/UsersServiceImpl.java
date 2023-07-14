package com.qianfeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.dao.IUserDao;
import com.qianfeng.domain.Orders;
import com.qianfeng.domain.Role;
import com.qianfeng.domain.UserInfo;
import com.qianfeng.service.IUsersService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service("userServiceImpl")
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class UsersServiceImpl implements IUsersService {

    @Resource
    IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo userInfo=userDao.findByUsername(userName);
        User user;
        if (userInfo==null){
            user=null;
        }else {
            //user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getGrantedAuthority());
            user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 1 ? true : false,true,true,true,getGrantedAuthority(userInfo));
        }
        return user;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthority(UserInfo userInfo) {
        ArrayList<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            String roleName = role.getRoleName();
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }
        return grantedAuthority;
    }

    @Override
    public List<UserInfo> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<UserInfo> userInfos=userDao.findAll();
        return userInfos;
    }

    @Override
    public void save(UserInfo userInfo) {
        String s = UUID.randomUUID().toString();
        userInfo.setId(s);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }
}
