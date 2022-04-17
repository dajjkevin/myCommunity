package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description
 */
@Data
public class UserHeart {

    @TableId(type = IdType.AUTO)
    private Integer heartId;

    private Integer actId;

    private Integer userId;

    private String myHeart;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Activity activity;
}
