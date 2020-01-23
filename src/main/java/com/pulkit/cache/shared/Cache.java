package com.pulkit.cache.shared;

public interface Cache {
    void add(int streamElement);

    void purgeCache();

    Integer getElement();
}