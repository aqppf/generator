package com.item.gen.model;

import java.io.Serializable;

public class Admin implements Serializable {
    /**
     * 主键 id
     */
    private Long id;

    /**
     * 登录名 login_name
     */
    private String loginName;

    /**
     * 昵称 nick_name
     */
    private String nickName;

    /**
     * 密码 password
     */
    private String password;

    /**
     * 随机掩值 salt
     */
    private String salt;

    /**
     * 角色id role_id
     */
    private Integer roleId;

    /**
     * admin
     */
    private static final long serialVersionUID = 1L;

    /**
     * @author Administrator
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @author Administrator
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @author Administrator
     * @return login_name 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @author Administrator
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * @author Administrator
     * @return nick_name 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @author Administrator
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @author Administrator
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @author Administrator
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @author Administrator
     * @return salt 随机掩值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @author Administrator
     * @param salt 随机掩值
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * @author Administrator
     * @return role_id 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @author Administrator
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}