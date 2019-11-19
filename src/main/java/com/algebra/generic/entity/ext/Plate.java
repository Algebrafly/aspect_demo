package com.algebra.generic.entity.ext;

/**
 * @author al
 * @date 2019/11/19 9:55
 * @description
 */
public class Plate<T> {
    private T item;

    public Plate(){}

    public Plate(T t){
        item = t;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
