package com.kof.fruit.a;

import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public  interface  ResponeData<T,K> {

    <T> T getCode();

    <K> K getMessage();

    Object getObject();
}
