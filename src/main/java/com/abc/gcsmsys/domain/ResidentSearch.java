package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description
 */
@Data
public class ResidentSearch {

    private String sName;

    /**范围 0等于 1小于等于 2大于等于*/
    private String sJudge;

    private String sAge;


    //修改的查询条件
    private Integer personId;
    private Integer houseId;
}
