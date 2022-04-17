package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description
 */
@Data
public class Serve {

    @TableId(type = IdType.AUTO)
    private Integer serveId;

    private String serveName;

    private Integer serveTypeId;

    private String phone;

    private String serveInt;

    @TableField("serve_money")
    private String serveMoney;

    @TableField(exist = false)
    private ServeType type;

    private String serveImg;
}
