package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description 后台订单查询条件
 */
@Data
public class OrdersSearch {

    private String userName;

    private String userPhone;

    private String startDate;

    private String endDate;

    private String orderName;

//    支付状态
    private String payStatus;

}
