package com.abc.gcsmsys.domain;

import com.abc.gcsmsys.handler.StringArrayTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description  家庭户主
 */
@Data
@TableName(autoResultMap = true,value = "household")
public class HouseHold {

    @TableId(type = IdType.AUTO)
    private Integer houseId;

    /**户主姓名*/
    private String houseName;

    /**户籍*/
    private String census;

    /**家庭人口*/
    private Integer houseCnt;

    private String birthday;

    /**身份证*/
    private String idCard;

    /**民族*/
    private String ethnic;

    /**性别*/
    private String gender;

    /**家庭地址*/
    @TableField(typeHandler = StringArrayTypeHandler.class)
    private String[] houseAddr; //typeHandler数组类型转换

    /**登记时间*/
    @TableField(fill = FieldFill.INSERT)
    private Date registDate;

    /**婚姻状态 0未婚 1已婚*/
    private String maritalStatus;

    /**搬迁状态 0正常 1搬迁*/
    private String housingStatus;

}
