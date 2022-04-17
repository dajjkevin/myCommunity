package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description 社区活动
 */
@Data
public class Activity {

    @TableId(type = IdType.AUTO)
    private Integer actId;

    /**活动主题*/
    private String actTheme;

    private String actImg;

    private String actStart;

    private String actEnd;

    /**可报名人数*/
    private String actNum;

    /**详情内容介绍*/
    private String actDtl;

    /**是否可报名 0可以 1禁止*/
    private String isOpen;

    /**参与人数*/
    private Integer actJoinNum;

    @TableField(exist = false)
    private UserHeart heart;


}
