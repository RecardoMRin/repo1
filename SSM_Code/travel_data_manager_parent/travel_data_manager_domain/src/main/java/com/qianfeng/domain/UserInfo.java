package com.qianfeng.domain;

import java.util.List;

public class UserInfo {

    private String id;
    private String email;
    private String username;
    private String password;
    private String phoneNum;
    private int status;
    private String statusStr;
    private List<Role> roles;

    public UserInfo() {
    }

    public UserInfo(String id, String email, String username, String password, String phoneNum, int status) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.status = status;
    }

    public UserInfo(String id, String email, String username, String password, String phoneNum, int status, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.status = status;
        this.roles = roles;
    }

    public UserInfo(String id, String email, String username, String password, String phoneNum, int status, String statusStr, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.status = status;
        this.statusStr = statusStr;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getstatusStr() {

        if (status == 0) {
            statusStr = "关闭";
        }
        if (status == 1) {
            statusStr = "开启";
        }
        return statusStr;

    }

    public void setstatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", roles=" + roles +
                '}';
    }
}
