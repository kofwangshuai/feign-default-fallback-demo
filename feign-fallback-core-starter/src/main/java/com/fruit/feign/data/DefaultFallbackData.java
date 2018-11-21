package com.fruit.feign.data;

/** 1
 *   发生服务熔断的时候 ，响应数据对象
 *
 * **/
public class DefaultFallbackData<K ,V> implements FallbackData<K, V> {

    private K code;
    private V message;

    public void setCode(K code) {
        this.code = code;
    }

    public void setMessage(V message) {
        this.message = message;
    }

    @Override
    public  K getCode() {
        return code;
    }

    @Override
    public  V getMessage() {
        return message;
    }

    @Override
    public <T> T getData() {
        return null;
    }
}
