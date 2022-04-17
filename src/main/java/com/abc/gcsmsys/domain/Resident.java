package com.abc.gcsmsys.domain;

import com.abc.gcsmsys.handler.StringArrayTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @Description 社区居民
 */
@Data
@TableName(autoResultMap = true,value = "resident")
public class Resident {

    /***/
    @TableId(type = IdType.AUTO)
    private Integer personId;

    /**姓名*/
    private String name;

    /**户主id 忽略null判断*/
    @TableField (updateStrategy = FieldStrategy.IGNORED)
    private Integer houseId;

    /**身份证*/
    private String idCard;

    /**户籍*/
    private String census;

    /**出生日期*/
    private String birthday;

    /**民族*/
    private String ethnic;

    /**家庭地址*/
    @TableField(typeHandler = StringArrayTypeHandler.class)
    private String[] houseAddr; //typeHandler数组类型转换

    /**与户主关系*/
    private String relation;

    /**婚姻状态 0未婚 1已婚*/
    private String maritalStatus;

    /**文化水平*/
    private String culture;

    /**性别*/
    private String gender;

    /**所属户主信息 */
    @TableField(exist = false)
    private HouseHold houseHold;

    private Integer age;
}
