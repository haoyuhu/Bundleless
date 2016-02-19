package com.huhaoyu.bundleless;

import android.os.Bundle;
import android.util.Log;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Date;

/**
 * Bundleless Test
 * Created by coderhuhy on 16/2/19.
 */
public class BundlelessTest extends TestCase {

    final private String LogTag = BundlelessTest.class.getCanonicalName();
    private OutClass out;

    public void setUp() throws Exception {
        super.setUp();
        out = new OutClass(0.5, 1, "hello");
        OutClass.InnerClass inner = new OutClass.InnerClass();
        out.add(inner);
        out.add("world", 2);
    }

    public void testBundleless() throws Exception {
        String obKey = "object";
        String intKey = "int";
        String doubleKey = "double";
        String floatKey = "float";
        String dateKey = "date";
        String stringKey = "string";
        Date date = new Date();

        BundlelessBuilder builder = new BundlelessBuilderImpl();
        builder.put(obKey, out)
                .put(intKey, -1)
                .put(doubleKey, 1.1)
                .put(floatKey, 1.2)
                .put(dateKey, date)
                .put(stringKey, "test");
        Bundle bundle = builder.toBundle();

        BundleWrapper bundleless = Bundleless.from(bundle);
        Log.d(LogTag, bundleless.get(obKey).toString());
        Assert.assertEquals((int) bundleless.get(intKey), -1);
        Assert.assertEquals(bundleless.get(doubleKey), 1.1);
        Assert.assertEquals(bundleless.get(floatKey), 1.2);
        Assert.assertEquals(((Date) bundleless.get(dateKey)).getTime(), date.getTime());
        Assert.assertEquals(bundleless.get(stringKey), "test");
    }

}