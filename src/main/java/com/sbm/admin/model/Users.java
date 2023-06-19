package com.sbm.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author guoxiaoyong
 * @since 2023-06-17
 */
@Data
@TableName("users")
@ApiModel(value = "Users对象", description = "")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("email")
    private String email;

    @TableField("email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @TableField("password")
    private String password;

    @TableField("two_factor_secret")
    private String twoFactorSecret;

    @TableField("two_factor_recovery_codes")
    private String twoFactorRecoveryCodes;

    @TableField("two_factor_confirmed_at")
    private LocalDateTime twoFactorConfirmedAt;

    @TableField("remember_token")
    private String rememberToken;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;


}