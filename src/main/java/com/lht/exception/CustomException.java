package com.lht.exception;

/**
 * 自定义运行时异常
 * @Author lihetao
 * @Date 2022/9/7 15:03
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg) {
        super(msg);
    }
}
