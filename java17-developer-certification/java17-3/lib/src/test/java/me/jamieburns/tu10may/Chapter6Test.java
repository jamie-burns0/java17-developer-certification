package me.jamieburns.tu10may;

import me.jamieburns.Test;
import static me.jamieburns.TestSupport.tests;

public class Chapter6Test {

    public static void main(String[] args) {
        new Chapter6Test().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                class A {
                    public int i; // 0
                    public byte by; // 0
                    public short sh; // 0
                    public long l; // 0
                    public char c; // \u0000 
                    public float f; // 0.0
                    public double d; // 0.0
                    public boolean bo; // false
                    public Object o; // null - for all reference types
                }
                A a = new A();
                System.out.println("" + a.i + a.by + a.sh + a.l + a.c + a.f + a.d + a.bo + a.o);
                return a.o == null;}, true),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0)
        );
    }

    private void a() {
        class Snake {
            public static String hiss = "hiss";
        }
        Snake s = new Snake();
        var a = s.hiss;
        s = null;
        var b = s.hiss;
    }
}
