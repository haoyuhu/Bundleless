package com.huhaoyu.bundleless;

import android.os.Bundle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement of Bundleless Builder
 * Created by coderhuhy on 16/2/19.
 */
public class BundlelessBuilderImpl implements BundlelessBuilder {

    private BundlelessManager manager;
    private Map<String, String> store;

    public BundlelessBuilderImpl() {
        manager = BundlelessManager.getInstance();
        manager.init();
        store = new HashMap<>();
    }

    @Override
    public BundlelessBuilder put(String key, Object object) {
        String json = manager.serialize(key, object);
        store.put(key, json);
        return this;
    }

    @Override
    public BundlelessBuilder put(String key, String string) {
        String json = manager.serialize(key, string);
        store.put(key, json);
        return this;
    }

    @Override
    public BundlelessBuilder put(String key, int i) {
        String json = manager.serialize(key, i);
        store.put(key, json);
        return this;
    }

    @Override
    public BundlelessBuilder put(String key, Date date) {
        String json = manager.serialize(key, date);
        store.put(key, json);
        return this;
    }

    @Override
    public BundlelessBuilder put(String key, double d) {
        String json = manager.serialize(key, d);
        store.put(key, json);
        return this;
    }

    @Override
    public BundlelessBuilder put(String key, float f) {
        String json = manager.serialize(key, f);
        store.put(key, json);
        return this;
    }

    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : store.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        store.clear();
        return bundle;
    }
}
