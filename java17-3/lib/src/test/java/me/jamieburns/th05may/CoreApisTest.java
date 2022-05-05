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
                // DO NOT reference the same string for the purposes of ==
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = "In the beginning was the Word\n".concat("and the Word was with God.");
                return a == b;}, false),
            new Test(() -> {
                // strings a and b are computed at RUNTIME - because of concat() - but, because of intern(),
                // DO reference the same string for the purposes of ==
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = "In the beginning was the Word\n".concat("and the Word was with God.").intern();
                return a == b;}, true),
            new Test(() -> {
                // lifted from the Java Language Specification, Example 3.10.5-1. String Literals
                String hello = "Hello", lo = "lo";
                var a = (hello == "Hello"); // true
                var b = (hello == ("Hel"+"lo")); //true
                var c = (hello == ("Hel"+lo)); // FALSE: lo is a variable not a string literal
                var d = (hello == ("Hel"+lo).intern()); // true
                return true;}, true),                
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
        String hello = "Hello", lo = "lo";
        boolean a = (hello == "Hello"); // true
        System.out.println(hello == ("Hel"+"lo"));
        System.out.println(hello == ("Hel"+lo));
        System.out.println(hello == ("Hel"+lo).intern());
        return 1;
    }
}
