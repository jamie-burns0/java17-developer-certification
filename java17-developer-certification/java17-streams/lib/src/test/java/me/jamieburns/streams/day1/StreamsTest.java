package me.jamieburns.streams.day1;

import static me.jamieburns.streams.TestSupport.tests;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import me.jamieburns.streams.Test;

public class StreamsTest {
    
public static void main(String[] args) {
        new StreamsTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test<>(() -> {
                new Random().ints()
                    .limit(10)
                    .filter(i -> i % 2 == 0) // even numbers
                    .map(i -> Math.abs(i))
                    .forEach(System.out::print);
                System.out.println("");

                int sum = IntStream.of(1, 2, 3, 4, 5, 6, 7)
                    .filter(i -> i % 2 == 0)
                    .sum();

                return sum;}, 12),
            new Test<>(() -> {
                List<String> l = List.of("In", "the", "beginning", "was", "the", "Word");
                String s = l.stream()
                    .map(String::toLowerCase)
                    //.peek(System.out::println)
                    .filter(Pattern.compile("[ai]").asPredicate()) // keep words with an a or i in them
                    //.peek(System.out::println)
                    .collect(Collectors.joining(", "));
                  return s;}, "in, beginning, was"),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
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
