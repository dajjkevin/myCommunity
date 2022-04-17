package com.abc.gcsmsys.domain;

import lombok.Data;

/**
 * @Description
 */
@Data
public class ApiReturnUtil {

    //错误
    private String error;

    private String context;

    public static String error(String s) {
        return s;
    }
}
