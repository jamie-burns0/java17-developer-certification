package me.jamieburns.th16jun;

import static me.jamieburns.TestSupport.tests;

import java.util.ArrayList;
import java.util.List;

import me.jamieburns.Test;

public class GenericsTest {
    
public static void main(String[] args) {
        new GenericsTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
                new Test(() -> {
                    B b = new B(){};
                    var b2 = new Box<>(b);
                    Box<B> b3 = b2;

                    var b1 = new Box<>("jamie", "jamie"); // construct a Box of any type. b is a Box of Object
                    //Box<?> b4 = b;
                    //Box<?> b5 = new Box<C>("a"); // construct a Box of any type. b2 is a Box of C
                    //Object o = b.t; // field t is erased to Object
                    //b.m2(new Box<>("jamie")); // pass in a Box of any type
                    //Box.m(new C(){}); // pass in any type that extends B or is B

                    return 0;}, 0),
                new Test(() -> {
                    List<? super B> l = new ArrayList<B>();
                    l.add(new B(){});
                    l.add(new C(){});
                    for (var i : l) {
                        i.toString();
                    }
                    List<? extends B> l2 = List.<B>of(new C(){}, new B(){});
                    for(var i : l2) {
                        i.b();
                    }

                    List<?> l3 = List.<B>of(new C(){}, new B(){});
                    for (var i : l3) {
                        i.toString();
                    }
                    return 0;}, 0),
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
}
