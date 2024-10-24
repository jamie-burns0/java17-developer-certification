package me.jamieburns.tu03may;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class FlowControlTest {

    private record Test(Tester tester, Object expectedResult) {}

    private interface Tester {
        public Object test();
    }

    private void tests(Test... testArray) {
        int testCount = 0;
        for (Test test : testArray) {
            Object result = test.tester().test();
            System.out.println(++testCount + ": " + result + ", " + result.getClass().getSimpleName());
            assertTrue(result.equals(test.expectedResult()), test.toString());
        }
    }

    @org.testng.annotations.Test
    public void testAssignments() {

        /*
         * operators on each row have the same level of precedence
         * where operators have the same level of precedence, everything is evaluated L-R unless expicitly called out as R-L
         * 
         * x++ x--
         * ++x --x
         * [R-L] -x (negate) !x (not) ~x (bitwise complement) ()x (primitive cast)
         * ()x (reference cast)
         * * / %
         * + -
         * << >> >>>
         * < > <= >= instanceof
         * == !=
         * &
         * ^
         * |
         * [R-L] x ? y : z
         * [R-L] = += -= *= /= %= &= ^= |= <<= >>= >>>=
         * [R-L] ->
         * 
         * */
        tests(
            new Test(() -> { 
                Number a = Integer.valueOf(3);
                Number b1 = null;
                if (a instanceof Integer b) { // pattern matching
                    b1 = b.compareTo(3);
                }
                // without pattern matching, this code would need to be
                if (a instanceof Integer) {
                    Integer b = (Integer)a;
                    b1 = b.compareTo(3);
                }
                
                Number c = Long.valueOf(3L);
                Number b2 = null;
                if (c instanceof Long b) { // pattern matching
                    b2 = b.compareTo(3L);
                }
                
            return b1 != null && b2 != null && b1 == b2;}, true), 
            new Test(() -> { 
                Number a = null;
                Number b1 = null;
                System.out.println(a + ", " + b1); // null, null
                if (a instanceof Integer b) { // false
                    b1 = b.compareTo(3);
                    System.out.println(b + ", " + b1); 
                }
                return "x";}, "x"), 
            new Test(() -> { // switch statement
                String a = "summer";
                String time = "2pm";
                final var b = "winter";
                final String c = "spring";
                switch (a) {
                    case "sum" + "mer":
                        if ("2pm".equals(time)) {
                            return "very hot";
                        } else {
                            return "hot";
                        }
                        // without our else, we need a break statement, otherwise we fall through to the next case statement
                    case c, "autumn":
                        return "warm";
                    case b:
                        return "cold";
                    case b + c:
                        return "unknown";
                    default:
                        return null;
                } // no semicolon at the end of a switch STATEMENT block
                }, "very hot"),
            new Test(() -> { // switch expression
                String a = "winter";
                String time = "2am";
                final var b = "winter";
                final String c = "spring";
                var temperature = switch (a) {
                    case "sum" + "mer" -> "hot";
                    case c, "autumn" -> "warm";
                    case b -> {
                        if ("2am".equals(time)) {
                            yield "very cold"; // case block must have a yield statement when our switch EXPRESSION is returning a value
                        } else {
                            yield "cold";
                        }
                    } // no semicolon in a case block
                    case b + c -> "unknown";
                    default -> null;
                }; // semicolon at the end of a switch EXPRESSION block, when we assign a value: var temperature = switch...
                return temperature;}, "very cold"),
            new Test(() -> {
                enum Season {
                    SUMMER, AUTUMN, WINTER, SPRING
                }
                Season a = Season.WINTER;
                String time = "2am";
                var temperature = switch (a) {
                    case SUMMER -> "hot";
                    case SPRING, AUTUMN -> "warm";
                    case WINTER -> {
                        if ("2am".equals(time)) {
                            yield "very cold"; // case block must have a yield statement when our switch EXPRESSION is returning a value
                        } else {
                            yield "cold";
                        }
                    } // no semicolon in a case block
                    default -> "UNKNOWN";
                }; // semicolon at the end of a switch EXPRESSION block, when we assign a value: var temperature = switch...
                return temperature;}, "very cold"),    
            new Test(() -> {
                int a = 3;
                while (a-- != 0) { // post-decrement operator
                    int doNothing = 0;
                }
                // while (a++ != 3) {
                //     if (a % 3 == 2) {
                //         break;
                //     }
                // }
                return a;}, -1),
            new Test(() -> {
                int a = 3;
                do {
                    int doNothing = 0;
                } while (--a != 0); // pre-decrement operator
                return 0;}, 0),
            new Test(() -> {
                int a = 0;
                for (int b = 0; b < 10; b++) {
                    a = b;
                }
                return a;}, 9),
            new Test(() -> {
                Integer[] a = {1, 1, 2, 3, 5, 8, 13};
                List<Integer> l = Arrays.asList(a);
                int c = 0;
                for (int b : l) {
                    c = b;
                }
                return c;}, 13),
            new Test(() -> {
                int[] a = {1, 1, 2, 3, 5, 8, 13};
                int c = 0;
                for (int b : a) {
                    c = b;
                }
                return c;}, 13),
            new Test(() -> {
                int d = 5;
                int e = 0;
                for (int b = 12, c = 99; e % d != (d - 1); b++, c--) {
                    e = (c - b) ;
                }
                return e;}, 79),
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
