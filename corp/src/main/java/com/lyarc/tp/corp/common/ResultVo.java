package com.lyarc.tp.corp.common;

import java.io.Serializable;

/**
 * 接口返回错误信息封装 XXX 提取错误信息和代码。错误信息和代码不仅是control层使用，此类应只是对错误信息和代码的包装，定义应独立出来
 * 
 * @version 1.0
 * @since JDK1.8
 * @author yangjunxiao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date May 15, 2017 10:37:04 AM
 */
public class ResultVo<T> implements Serializable, Cloneable {

    private static final long serialVersionUID = 7865545655244833548L;
    /*** 成功 */
    // public static final ResultVo<Object> SUCC = ResultVo.build(0,"OK");
    // public static final ResultVo<Object> API_NOT_EXSIT = ResultVo.build(404,"Api不存在");
    // public static final ResultVo<Object> FAILURE = ResultVo.build(500,"内部服务器错误");
    //
    // // '101到 200 为保留信息
    // /** 参数错误 */
    // public static final ResultVo<Object> PARAM_LESS_ERROR = ResultVo.build(600, "缺少参数");
    // /** 参数错误 */
    // public static final ResultVo<Object> PARAM_TYPE_ERROR = ResultVo.build(601, "参数类型转换错误");
    // public static final ResultVo<Object> LENGTH_ERROR = ResultVo.build(100010, "长度超出范围");


    protected int code;
    private T data;
    private String message;

    public ResultVo() {}

    private ResultVo(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.message = msg;
    }

    private ResultVo(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public ResultVo<T> wrapMessage(String message) {
        return new ResultVo<T>(this.code, this.data, message);
    }

    public ResultVo<T> wrapMessage(String message, int code) {
        return new ResultVo<T>(code, this.data, message);
    }

    public ResultVo<T> wrapData(T data) {
        return new ResultVo<T>(this.code, data, this.message);
    }

    public ResultVo<T> wrapDatas(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultVo<T> build(int code, String message) {
        return new ResultVo<T>(code, message);
    }

    public static <T> ResultVo<T> success() {
        return new ResultVo<T>(0, "OK");
    }

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<T>(0, data, "OK");
    }

    public static ResultVo<String> failure(String msg) {
        return new ResultVo<String>(1, msg);
    }

    public static ResultVo<String> failure(Integer code, String msg) {
        return new ResultVo<String>(code, msg);
    }

    public static ResultVo<String> failure() {
        return new ResultVo<String>(500, "内部服务器错误");
    }
}


