package com.abc.gcsmsys.domain;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class AlipayBean {
    /*商户订单号，必填*/
    private String orderNo;
    /*订单名称，必填*/
    private String typeName;
    /*付款金额，必填*/
    private Double payMoney;
//    /*商品描述，可空*/
//    private String body;
//    /*超时时间参数*/
//    private String timeout_express = "10m";
//    private String product_code = "FAST_INSTANT_TRADE_PAY";
}
