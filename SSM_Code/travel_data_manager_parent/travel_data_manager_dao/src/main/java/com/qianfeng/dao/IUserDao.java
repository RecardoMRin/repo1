package com.qianfeng.dao;


import com.qianfeng.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface IUserDao {

    @Results(id = "baseResult3",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "email",property = "email"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",many = @Many(fetchType = FetchType.EAGER,select = "com.qianfeng.dao.IRoleDao.findByUserId")),
    })
    @Select("select * from users where username=#{userName}")
    UserInfo findByUsername(String userName);


    @Select("select * from users where status = 1")
    List<UserInfo> findAll();

    @ResultMap("baseResult3")
    @Delete("delete from users where id=#{id}")
    void deleteById(String id);

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @ResultMap("baseResult3")
    @Select("select * from users where id=#{id}")
    UserInfo findById(String id);


}
