package com.torch.supermusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author Torch
 * @since 2021-12-03
 */
@Data
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("权限等级")
    @TableField("root")
    private Integer root;

    @ApiModelProperty("联系方式")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("用户头像路径")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("性别（0男，1女）")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;


    //用户角色列表,不属于用户表字段，需要排除
    @ApiModelProperty(value = "用户角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    //由于authorities不是数据库里面的字段，所以要排除他，不然mybatis-plus找不到该字段会报错
    @ApiModelProperty(value = "权限")
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //账户没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //密码没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //账户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
