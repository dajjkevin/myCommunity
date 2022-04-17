package com.abc.gcsmsys.domain;

import lombok.Data;


@Data
public class CancelOrderDto {
    private String orderNo;
    private Double amount;
}
