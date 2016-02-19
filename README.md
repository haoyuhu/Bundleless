[![](https://jitpack.io/v/HaoyuHu/Bundleless.svg)](https://jitpack.io/#HaoyuHu/Bundleless)
# Bundleless
Solution of data transmission between activities without Serializable and Parcelable.

## Setup
### Gradle
Add the JitPack repository to your build file in gradle:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:

```gradle
dependencies {
    compile 'com.github.HaoyuHu:Bundleless:v1.0'
}
```

### Maven
Add the JitPack repository to your build file in maven:

```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

Add the dependency:

```
<dependency>
    <groupId>com.github.HaoyuHu</groupId>
    <artifactId>Bundleless</artifactId>
    <version>v1.0</version>
</dependency>
```

## Usages
For example, your class `Foo` has a inner class `Bar`:

```java
public class Foo {
    private double d;
    public int i;
    protected String s;
    private Date date;
    protected List<Bar> list;
    protected Map<String, Double> map;
    private Set<Bar> set;

    public Foo(double d, int i, String s) {
        this.d = d;
        this.i = i;
        this.s = s;
        this.date = new Date();
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.set = new HashSet<>();
    }

    public void add(Bar bar) {
        list.add(bar);
        set.add(bar);
        map.put(bar.toString(), 0);
    }

    public void add(String ss, int ii) {
        Bar bar = new Bar();
        bar.ss = ss;
        bar.ii = ii;
        add(bar);
    }

    public static class Bar {
        private String ss;
        protected int ii;
    }
}
```

In `ActivityA` for Android App:

```java
String obKey = "object";
String intKey = "int";
String doubleKey = "double";
String floatKey = "float";
String dateKey = "date";
String stringKey = "string";

BundlelessBuilder builder = new BundlelessBuilderImpl();
builder.put(obKey, out)
        .put(intKey, -1)
        .put(doubleKey, 1.1)
        .put(floatKey, 1.2)
        .put(dateKey, new Date())
        .put(stringKey, "test");
Bundle bundle = builder.toBundle();

Intent intent = new Intent();
intent.setClass(ActivityA.this, ActivityB.class);
intent.putExtras(bundle);
startActivity(intent);
```

In `ActivityB#onCreate` method for Android App:

```java
Bundle bundle = getIntent().getExtras();
BundleWrapper bundleless = Bundleless.from(bundle);

Foo foo = (Foo) bundleless.get(obKey);
int i = (int) bundleless.get(intKey);
double d = (double) bundleless.get(doubleKey);
float f = (float) bundleless.get(floatKey);
Date date = (Date) bundleless.get(dateKey);
String str = (String) bundleless.get(stringKey);
```

## Attention

* Data transmission is based on `Gson`, more information **[google-gson](https://github.com/google/gson)**.
* Bundleless can serialize/deserialize objects of **static nested classes**. However, Bundleless can **not** automatically serialize/deserialize the pure inner classes. You can address this problem by either **making the inner class static** or by **providing a custom InstanceCreator for it**.
* Bundleless can not automatically serialize/deserialize Array and Collections.

## License
The MIT License (MIT)

Copyright (c) 2016 huhaoyu

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
