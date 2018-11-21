package com.kof.fruit.a;

import com.fruit.feign.data.FallbackData;

public class DefaultFallbackData2 <K ,V> implements FallbackData<K, V> {

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