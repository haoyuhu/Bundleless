package com.huhaoyu.bundleless;

import android.os.Bundle;

import java.util.Date;

/**
 * Bundleless Builder
 * Created by coderhuhy on 16/2/19.
 */
public interface BundlelessBuilder {

    /**
     * @param key    Key used for saving object into bundleless
     * @param object Object
     * @return BundlelessBuilder
     */
    BundlelessBuilder put(String key, Object object);

    /**
     * @param key Key used for saving String into bundleless
     * @param string String
     * @return BundlelessBuilder
     */
    BundlelessBuilder put(String key, String string);

    /**
     * @param key Key used for saving int type value into bundleless
     * @param i int value
     * @return BundlelessBuilder
     */
    BundlelessBuilder put(String key, int i);

    /**
     * @param key Key used for saving date into bundleless
     * @param date Date type value
     * @return BundlelessBuilder
     */
    BundlelessBuilder put(String key, Date date);

    /**
     * @param key Key used for saving double value into bundleless
     * @param d double value
     * @return Bundleless
     */
    BundlelessBuilder put(String key, double d);

    /**
     * @param key Key used for saving float value into bundleless
     * @param f float value
     * @return Bundleless
     */
    BundlelessBuilder put(String key, float f);

    /**
     * @return Bundle for transmitting between Activity
     */
    Bundle toBundle();

}
