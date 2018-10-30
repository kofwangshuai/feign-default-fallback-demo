package com.kof.fruit.a;

import java.io.Serializable;

public class FResponeData<T ,k> implements ResponeData<T ,k> , Serializable {

    private String code;
    private String message;



    public void setCode(String code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public <T> T getCode() {
        return (T) code;
    }

    @Override
    public <K> K getMessage() {
        return (K) message;
    }

    @Override
    public Object getObject() {
        return null;
    }
}
