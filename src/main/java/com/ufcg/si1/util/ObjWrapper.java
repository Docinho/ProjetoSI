package com.ufcg.si1.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ObjWrapper<T> implements Serializable {

    private T obj;

    public ObjWrapper(T obj) {
        this.obj = obj;
    }

    public ObjWrapper(){}

    public T getObj() {
        return obj;
    }

}

