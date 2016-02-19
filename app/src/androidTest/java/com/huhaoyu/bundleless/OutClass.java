package com.huhaoyu.bundleless;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Test Class
 * Created by coderhuhy on 16/2/18.
 */
public class OutClass {
    private double d;
    public int i;
    protected String s;
    private Date date;
    protected List<InnerClass> list;
    protected Map<String, Double> map;
    private Set<InnerClass> set;

    public OutClass(double d, int i, String s) {
        this.d = d;
        this.i = i;
        this.s = s;
        this.date = new Date();
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.set = new HashSet<>();
    }

    public void add(InnerClass cls) {
        list.add(cls);
        set.add(cls);
        map.put(cls.toString(), 0.2);
    }

    public void add(String ss, int ii) {
        InnerClass cls = new InnerClass();
        cls.ss = ss;
        cls.ii = ii;
        add(cls);
    }

    public static class InnerClass {
        private String ss;
        protected int ii;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("d: ").append(d).append(";")
                .append("i: ").append(i).append(";")
                .append("s: ").append(s).append(";")
                .append("date: ").append(date.getTime()).append(";")
                .append("list: ");
        for (InnerClass cls : list) {
            builder.append(cls.ss + "-" + cls.ii).append(";");
        }
        builder.append(map.toString() + "-" + set.toString());
        return builder.toString();
    }
}

