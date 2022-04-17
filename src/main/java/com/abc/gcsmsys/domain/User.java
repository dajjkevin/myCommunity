package com.abc.gcsmsys.domain;

import com.abc.gcsmsys.handler.StringArrayTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 */
@Data
@TableName(autoResultMap = true,value = "user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPwd;

    private String email;

    @TableField(typeHandler = StringArrayTypeHandler.class)
    private String[] userAddr;

    private String userAvatar;

    /**0正常 1冻结*/
    private String userStatus;

    private String phone;

    private String gender;

    private String birthday;

    /**个人简介*/
    private String userInt;


}
