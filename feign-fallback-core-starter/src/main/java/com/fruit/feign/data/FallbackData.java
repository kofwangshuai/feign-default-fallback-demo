package com.fruit.feign.data;

import java.io.Serializable;

public interface FallbackData<K ,V> extends Serializable {

     K getCode();

     V getMessage();

    <T> T getData();
}
