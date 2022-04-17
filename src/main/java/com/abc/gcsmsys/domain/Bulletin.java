package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 */
@Data
public class Bulletin {

    @TableId(type = IdType.AUTO)
    private Integer bulletinId;

    private String bulletinTheme;

    private String content;

    /**登记时间*/
    private String releaseTime;

    private String view;
}
