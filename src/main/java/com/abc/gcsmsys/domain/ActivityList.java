package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 */
@Data
@TableName("activity_list")
public class ActivityList {

    @TableId(type = IdType.AUTO)
    private Integer actListId;

    private Integer actId;

    private Integer userId;


}
