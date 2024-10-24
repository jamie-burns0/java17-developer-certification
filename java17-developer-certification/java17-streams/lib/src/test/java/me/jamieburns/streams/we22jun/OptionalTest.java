package me.jamieburns.streams.we22jun;

import static me.jamieburns.streams.TestSupport.tests;

import me.jamieburns.streams.Test;

public class OptionalTest {
    
public static void main(String[] args) {
        new OptionalTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test<Long>(() -> {
                return 1L;}, Long.valueOf("1")),
            new Test<Exception>(() -> {
                return new Exception();}, new Exception(), (Exception a, Exception b) -> a.getClass().equals(b.getClass())),
            new Test<String>(() -> {
                return "";}, ""),
            new Test<Double>(() -> {
                return null;}, null),
            new Test<>(() -> {
                return "a";}, "a"),
            new Test<>(() -> {return 0;}, 0),                
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0)
        );
    }
}
