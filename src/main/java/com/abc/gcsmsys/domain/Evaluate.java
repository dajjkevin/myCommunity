package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 评价
 */
@Data
@TableName(autoResultMap = true,value = "evaluate")
public class Evaluate {

    @TableId(type = IdType.AUTO)
    private Integer eId;

    private String content;

    private Integer userId;

    /**订单id*/
    private String orderId;

    private Integer serveId;

    /**0已评论 1删除*/
    private String eStatus;

    /**星级*/
    private String fraction;

    @TableField(fill = FieldFill.INSERT)
    private Date eTime;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Serve serve;

    @TableField(exist = false)
    private Orders orders;

    @TableField(exist = false)
    private ServeType type;

}
