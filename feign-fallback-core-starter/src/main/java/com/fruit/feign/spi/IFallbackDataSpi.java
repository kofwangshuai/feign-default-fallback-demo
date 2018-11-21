package com.fruit.feign.spi;

import java.io.Serializable;
import java.util.Map;

public interface IFallbackDataSpi extends Serializable {

     String name();

     Map<Class, Object> getReturnTypeDataMap() ;
}
