package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description 查看详细信息
 */
@Data
public class HouseIdInfoVo {

    private Integer houseId;
    private String houseName;

    private Resident resident;
}
