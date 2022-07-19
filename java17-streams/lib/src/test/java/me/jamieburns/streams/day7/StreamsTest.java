package me.jamieburns.streams.day7;

import static me.jamieburns.streams.TestSupport.tests;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
                // https://howtodoinjava.com/java8/stream-random-numbers-range/
                // https://howtodoinjava.com/java8/stream-flatmap-example/
                Random r = new Random();
                List<Integer> l1 = r.ints(3)
                    .boxed()
                    .collect(Collectors.toList());
                List<Integer> l2 = r.ints(5)
                    .boxed()
                    .collect(Collectors.toList());
                
                List<Integer> flattened = List.of(l1, l2).stream()
                    .flatMap(List::stream) // create one stream of Integer from the two lists
                    .map(i -> i % 20)
                    .collect(Collectors.toList()); // put all the Integer values into one List

                System.out.println(flattened);
                return 0;}, 0),
            new Test<>(() -> {
                CollectDebug<String> result = Stream.<Double>generate(Math::random)
                //.parallel()
                .limit(4)
                //.parallel()
                .map(t -> String.valueOf(t) + "A")
                .collect(// Supplier<R>, BiConsumer<R,T>, BiConsumer<R,R> : R
                    CollectDebug::new,
                    //() -> new CollectDebug<Double>(),
                    CollectDebug::collect,
                    //(r, t) -> r.collect(t),
                    CollectDebug::combine
                    //(r1, r2) -> r1.combine(r2)                        
                );
                result.print();
                return 0;}, 0),
            new Test<>(() -> {
                interface A {
                    int apply(String s1, String s2);
                }

                interface B {
                    int apply(String s);
                }

                String abc = "abc";
                String def = "def";

                A a = (s1, s2) -> s1.compareTo(s2);
                A a2 = String::compareTo;
                B b = abc::compareTo;
                
                int r1 = a.apply(abc, def);
                int r2 = a2.apply(abc, def);
                int r3 = b.apply(def);

                System.out.println(r1);
                System.out.println(r2);
                System.out.println(r3);

                return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
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

class CollectDebug<T> extends Debug {
    void collect(T v) {
        System.out.println("CollectDebug::collect: " + this + ", " + v);
    }
    void combine(CollectDebug<? extends T> that) {
        System.out.println("CollectDebug::combine: " + this + ", " + that);
    }
}

class ReduceDebug<T> extends Debug {
    ReduceDebug<T> reduce(T v) {
        System.out.println("ReduceDebug::reduce: " + this + ", " + v);
        return this;
    }
    ReduceDebug<T> combine(ReduceDebug<? extends T> that) {
        System.out.println("ReduceDebug::combine: " + this + ", " + that);
        return this;
    }
}
