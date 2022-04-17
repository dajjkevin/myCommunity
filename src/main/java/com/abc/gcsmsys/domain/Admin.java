package com.abc.gcsmsys.domain;

import com.abc.gcsmsys.handler.StringArrayTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description 管理员
 */
@Data
@TableName(autoResultMap = true,value = "admin")
public class Admin {

    @TableId(type = IdType.AUTO)
    private Integer adminId;

    private String adminName;

    private String adminPwd;

    /**性别 0-男 1-女*/
    private String gender;

    /**管理员状态 0超级 1普通*/
    private String adminType;

    private String phone;

    @TableField(typeHandler = StringArrayTypeHandler.class)
    private String[] addr;

    /**头像*/
    private String adminAvatar;

    private String adminEmail;

    /**户籍*/
    private String census;

    /**出生日期*/
    private String birthday;

    /**民族*/
    private String ethnic;

    /**政治身份*/
    private String politicalIdentity;

    /**0正常 1禁用*/
    private String adminStatus;
}
