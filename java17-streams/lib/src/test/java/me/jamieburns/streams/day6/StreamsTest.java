package me.jamieburns.streams.day6;

import static me.jamieburns.streams.TestSupport.tests;

import java.util.stream.Stream;

import me.jamieburns.streams.Test;

public class StreamsTest {
    
    public static void main(String[] args) {
            new StreamsTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test<>(() -> {
                class CollectDebug<T> extends Debug {
                    void collect(T v) {
                        System.out.println("CollectDebug::collect: " + this + ", " + v);
                    }
                    void combine(CollectDebug<? extends T> that) {
                        System.out.println("CollectDebug::combine: " + this + ", " + that);
                    }
                }
                CollectDebug<Double> result = Stream.<Double>generate(Math::random)
                    .limit(4)
                    .collect(// Supplier<R>, BiConsumer<R,T>, BiConsumer<R,R> : R
                        CollectDebug::new,
                        (r, t) -> r.collect(t),
                        (r1, r2) -> r1.combine(r2)
                    );
                result.print();
                return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0)
        );
    }
}

class Debug {
    final static Object lock = new Object();
    static int count;
    final int id;

    public Debug() {
        synchronized (lock) {
            id = ++count;
        }
    }
    public void print() {System.out.println(toString());}
    public String toString() {return "{" + id + "}";}
}
