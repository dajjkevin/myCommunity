package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description
 */
@Data
public class ServeSearch {

    /**类型名字*/
    private String typeName;

    /**服务人员*/
    private String serveName;

    /**电话*/
    private String phone;

    /**服务主要内容*/
    private String serveInt;

}
