package com.huhaoyu.bundleless;

import android.os.Bundle;

/**
 * Bundle Wrapper
 * Created by coderhuhy on 16/2/19.
 */
public abstract class BundleWrapper {

    protected Bundle bundle;

    public BundleWrapper(Bundle bundle) {
        this.bundle = bundle;
    }

    /**
     * @param key String Key
     * @return Object of Value
     */
    abstract public Object get(String key);

    /**
     * @return Original Bundle
     */
    public Bundle bundle() {
        return this.bundle;
    }
}
