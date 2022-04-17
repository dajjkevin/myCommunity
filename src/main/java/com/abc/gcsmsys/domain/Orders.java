package com.abc.gcsmsys.domain;

import com.abc.gcsmsys.handler.StringArrayTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 订单
 */
@Data
@TableName(autoResultMap = true,value = "orders")
public class Orders {

    @TableId(type = IdType.AUTO)
    private Integer orderId;

    private String orderNo;

    private Integer serveId;

    private Integer userId;

    /**支付状态 0未支付 1支付成功 */
    private String payStatus;

    /**订单价格*/
    private String money;

    /**下单用户姓名*/
    private String orderUserName;

    /**商品名称*/
    private String orderName;

    /**订单创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**支付时间*/
    private String payTime;

    /**下单地址*/
    @TableField(typeHandler = StringArrayTypeHandler.class)
    private String[] orderAddr;

    /**订单状态 0正常 1确认 2删除*/
    private String orderStatus;

    /**下单电话电话*/
    private String orderPhone;

//    是否评价
    private String evalStatus;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Serve serve;

    @TableField(exist = false)
    private ServeType type;

    /**评论内容*/
    @TableField(exist = false)
    private Evaluate evaluate;

}
