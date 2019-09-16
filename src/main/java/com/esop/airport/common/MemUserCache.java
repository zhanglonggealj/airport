package com.esop.airport.common;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by liliqiang on 2018/1/31.
 */
public class MemUserCache {

    static final ThreadLocal<Map<String, Object>> resources = new ThreadLocal();

    public final static int ORG_WX_USER = 1;
    public final static int ORG_ADMIN = 2;


    public static Object get(Integer key) {

        String mapKey = key + Thread.currentThread().getName();

        Map<String, Object> map = resources.get();
        return map != null ? map.get(mapKey) : null;
    }

    public static void set(Integer key, Object obj) {

        String mapKey = key + Thread.currentThread().getName();

        Map<String, Object> map = resources.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(mapKey, obj);
        resources.set(map);
    }

    public static void remove() {
        resources.remove();
    }


}
