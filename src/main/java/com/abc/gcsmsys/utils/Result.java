package com.abc.gcsmsys.utils;

import lombok.Data;


/**
 * @Author surface
 * @Date 2021-12-6 0:07
 * @Description
 */
@Data
public class Result<T> {

    private String code;

    private String msg;

    private T data;

    private T data2;

    public Result(){

    }

    public Result(T data) {
        this.data = data;
    }

    //多个json列表数据
    public Result(T data,T data2) {
        this.data = data;
        this.data2 = data2;
    }

    /**成功返回*/
    public static Result success(){
        Result result = new Result();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data,T data2){
        Result<T> result = new Result<>(data,data2);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**失败返回信息*/
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
