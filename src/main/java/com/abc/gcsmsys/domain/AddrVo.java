package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description 联机地址Vo
 */
@Data
public class AddrVo {

    /**省*/
    private String province;

    /**城市*/
    private String city;

    /**区*/
    private String area;

    /**详细地址*/
    private String addr;

    public String[] houAddr(){
        String[] houAddr  = {this.province,this.city,this.area,this.addr};
        return houAddr;
    }
}
