package me.jamieburns.streams.day2;

import static me.jamieburns.streams.TestSupport.tests;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
                double[] sum = new double[1];
                Stream.<Double>iterate(0.0, d -> d < 100, d -> d + 1.0)
                    .forEach(d -> sum[0] += d);
                return sum[0];}, 4950.0),
            new Test<>(() -> {
                double sum = Stream.<Double>iterate(0.0, d -> d < 100, d -> d + 1.0)
                    .reduce(0.0, (d1, d2) -> d1 + d2);
                return sum;}, 4950.0),
            new Test<Double>(() -> {
                Optional<Double> sum = Stream.<Double>iterate(0.0, d -> d < 100, d -> d + 1.0)
                    .reduce((d1, d2) -> d1 + d2);
                return sum.get();}, 4950.0),
            new Test<>(() -> {
                String s = IntStream.rangeClosed(1, 20)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining());
                return s;}, "1234567891011121314151617181920"),
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
