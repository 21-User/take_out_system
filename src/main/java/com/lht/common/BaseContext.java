package com.lht.common;

/**
 * 自定义获取线程id公共类
 * @author: 21
 * @date: 2022/9/5
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new InheritableThreadLocal<>();

    /**
     * 设置当前线程id
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前线程id
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
