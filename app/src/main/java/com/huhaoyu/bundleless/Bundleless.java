package com.huhaoyu.bundleless;

import android.os.Bundle;

/**
 * Bundleless
 * Created by coderhuhy on 16/2/19.
 */
public class Bundleless extends BundleWrapper {

    private BundlelessManager manager;

    public Bundleless(Bundle bundle) {
        super(bundle);
        manager = BundlelessManager.getInstance();
    }

    @Override
    public Object get(String key) {
        String s = bundle.getString(key);
        if (s != null) {
            return manager.deserialize(key, s);
        }
        return null;
    }

    public static BundleWrapper from(Bundle bundle) {
        return new Bundleless(bundle);
    }

}
