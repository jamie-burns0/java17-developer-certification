package me.jamieburns.th05may;

import me.jamieburns.Test;
import static me.jamieburns.TestSupport.tests;

public class CoreApisTest {

    public static void main(String[] args) {
        new CoreApisTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                // strings a and b are computed at COMPILE TIME and are the same 
                // --> interned (added to String's string pool) 
                // --> a and b have the same reference value
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = """
                    In the beginning was the Word
                    and the Word was with God.""";
                return a == b;}, true),
            new Test(() -> {
                // strings a and b are computed at RUNTIME - because of concat() - and while the same for equals(),
                // are not referencing the same string for the purposes of ==
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = "In the beginning was the Word\n".concat("and the Word was with God.");
                return a == b;}, false),
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

    private int a() {
        int a = 0;
        int d = 5;
        int e = 0;
        for (int b = 0, c = 99; (e = (c - b) % a) == 5; b++, c--) {
            // do nothing
        }
        System.out.println("e="+ e);
        return e;
    }
}
