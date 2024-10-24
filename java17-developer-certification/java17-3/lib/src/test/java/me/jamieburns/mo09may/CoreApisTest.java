package me.jamieburns.mo09may;

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
                class Snake {
                    static String hiss = "hiss";
                }
                Snake s = new Snake();
                var a = s.hiss;
                s = null;
                var b = s.hiss; // hiss is static so we can call it on s, even when s is null. Java calls it on the class (Type)
                                // see exam guide p244
                return a == b;}, true),
            new Test(() -> {
                class Snake {
                    static String slither = "slither";
                }
                Snake s = null;
                var a = s.slither;
                return true;}, true),
            new Test(() -> {
                Integer a = 3;
                Long b = (long)3;
                Byte c = 3; // int->Byte works here because 3 fits into byte.
                //Integer d = 3L; // why doesn't long->Integer work? A long 3 fits into int
                return 0;}, 0),
            new Test(() -> {
                class Overloading {
                    // order java uses to choose the right overloaded method - see exam guide p262
                    //
                    // (1) exact match by type
                    // (2) larger primitive type
                    // (3) autoboxed type
                    // (4) varargs

                    // (1)
                    public String callMe(int a) {
                        return "callMe(int)";
                    }

                    // (2)
                    public String callMe(long a, long b) {
                        return "callMe(long, long)";
                    }

                    // (3)
                    public String callMe(Integer a, Integer b, Integer c) { 
                        return "callMe(Integer, Integer, Integer)";
                    }

                    // (4)
                    public String callMe(int... a) { // won't be called if we have (int, int) or (int, int, int), but will be called if we have (int, int, int, int)
                        return "callMe(int...)";
                    }
                }
                Overloading o = new Overloading();
                StringBuilder sb = new StringBuilder()
                    .append(o.callMe(1)) // callMe(int)                             (1)
                    .append(o.callMe(1, 2)) // callMe(long, long)                   (2)
                    .append(o.callMe(1, 2, 3)) // callMe(Integer, Integer, Integer) (3)
                    .append(o.callMe(1, 2, 3, 4)); // callMe(int...)                (4)
                
                o.callMe(0xff); // callMe(int)
                o.callMe((byte)120); // callMe(int) - widening

                return sb.toString();}, "callMe(int)callMe(long, long)callMe(Integer, Integer, Integer)callMe(int...)"),
            new Test(() -> {
                class Jamie {} // compiler adds "extends Object" if a class doesn't extend anything
                var a = new Jamie();
                var b = new Jamie();
                var c = a.equals(b); // Object.equals(...)
                return c;}, false),
            new Test(() -> {
                class Initializer {
                    public static int myStaticInt;
                    public static final long myStaticFinalInt;

                    public int myInt;

                    static {
                        myStaticInt = 1;
                        myStaticFinalInt = 2L;
                    }

                    {
                        myInt = 3;
                    }

                    public Initializer() {
                        var temp = myInt * myStaticFinalInt + myStaticInt;
                        myInt = (int)temp;
                    }
                }

                var i = new Initializer();
                return i.myInt;}, 7),
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
