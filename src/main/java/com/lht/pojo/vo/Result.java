package com.lht.pojo.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局返回结果类
 * @Author 21
 * @Date 2022/9/2 11:35
 */
@Data
public class Result<T> {

    /**
     * 错误信息
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap();

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
