package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description 投诉/建议
 */
@Data
public class Appeal {

    @TableId(type = IdType.AUTO)
    private Integer appealId;

    private String title;

    private String detail;

    private String userId;

    /**回复内容*/
    private String dealWith;

    /**回复状态*/
    private String replyStatus;

    private String uemail;

    private String uphone;

    @TableField(fill = FieldFill.INSERT)
    private Date appTime;

    @TableField(exist = false)
    private User user;

}
