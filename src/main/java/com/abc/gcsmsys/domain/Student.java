package com.abc.gcsmsys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 */
@Data
@TableName("tbl_student")
public class Student {

    @TableId(type = IdType.AUTO)
    private Integer stuId;

    private String stuName;

    private Double stuHeight;

}
