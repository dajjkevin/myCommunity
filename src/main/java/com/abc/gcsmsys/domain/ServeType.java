package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 */
@Data
@TableName("serve_type")
public class ServeType {

    @TableId(type = IdType.AUTO)
    private Integer serveTypeId;

    private String typeName;

}
