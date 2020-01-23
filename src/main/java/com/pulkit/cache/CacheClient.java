package com.pulkit.cache;

import com.pulkit.cache.shared.Cache;
import com.pulkit.cache.implementation.lru.LRUCache;
import com.pulkit.cache.implementation.non_repeating_element.NonRepeatingElementCache;

public class CacheClient {
    private Cache cache;

    public void createNonRepeatingElementCache() {
        this.cache = new NonRepeatingElementCache();
    }

    public void createLRUCache() {
        this.cache = new LRUCache();
    }

    public void addElement(Integer element) {
        cache.add(element);
    }

    public Integer getElement() {
        return cache.getElement();
    }

    public void clearCache() {
        cache.purgeCache();
    }
}