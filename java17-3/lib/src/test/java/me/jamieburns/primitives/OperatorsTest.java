package me.jamieburns.primitives;

import static org.testng.Assert.assertTrue;

public class OperatorsTest {

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
            new Test(() -> {return 2 + 3;}, 5), 
            new Test(() -> {return 2 + 3 * 4;}, 14),
            new Test(() -> {return (short)2 + 3 + 4L;}, (long)9),
            new Test(() -> {
                int a = 3;
                return a + a * a;}, 12),
            new Test(() -> {
                short a = 3;
                return (long)a + a * a;}, 12L),
            new Test(() -> {
                short a = (short)3L;
                return (long)a + a * a;}, 12L),
            new Test(() -> {
                short a = 3;
                return a + ++a * a;}, 19),
            new Test(() -> {
                short a = 3;
                return ++a * a + a;}, 20),
            new Test(() -> {
                int a = 3;
                return ~a;}, -4), // bitwise operator value = (-a - 1) OR (-1a - 1) OR negate a and minus 1
            new Test(() -> {return -(2 + 3);}, -5),
            new Test(() -> {return !true;}, false),
            new Test(() -> {
                int a = 3;
                return a + (a = a + 1) * a;}, 19),             
            new Test(() -> {
                int a = 3;
                return (a = a + 1) * a + a;}, 20),
            new Test(() -> {
                int a = 3;
                return (a = a + 1) * a + a;}, 20),
            new Test(() -> {
                int a = 3;
                return (a = a + 1) * a + a == 20;}, true),  
            new Test(() -> {
                int a = 3;
                return (a = a + 1) * a == 16 != (a == 20);}, true),                          
            new Test(() -> {
                int a = 3;
                return ++a * a == 16 == ((a = 20) == 16);}, false), 
            new Test(() -> {
                int a = 3;
                return ++a * a <= 16 != (a = 20) <= 16;}, true), // <= has higher precedence than !=
            new Test(() -> {
                int a = 3;
                return a++ * a == 12 != !((a = 20) == 16);}, false),    
            new Test(() -> {
                int a = 3;
                boolean b = a++ * a == 12 || !((a = 20) == 16);
                return a;}, 4),  
            new Test(() -> {
                int a = 3;
                boolean b = a++ * a == 12 && !((a = 20) == 16);
                return a;}, 20), 
            new Test(() -> {
                int a = 3;
                boolean b = a++ * a == 12 | !((a = 20) == 16);
                return a;}, 20), 
            new Test(() -> {
                int a = 3;
                boolean b = a++ * a == 12 | !((a += 20) == 16);
                return a;}, 24),  
            new Test(() -> {
                int a = 3;
                boolean b = a++ * (a -= 2) < 12 ^ !((a += 20) < 16); 
                return a;}, 22), // < has higher precedence than ^
            new Test(() -> {
                int a = 3;
                boolean b = a++ * (a -= 2) <= 6 ^ (a += 20) <= 22;
                return b;}, false), // exlusive OR: true ^ true -> false
            new Test(() -> {
                int a = 3;
                return ++a * a < 0 && (a = 20) <= 16;}, false), // <=, < have higher precedence than &&
            new Test(() -> {
                int a = 16;
                return a >> 3 * 5 - 14;}, 8),  // >> has lower precedence than * and +  
            new Test(() -> {
                int a = 4;
                return (a >> 1) * 3 + 4;}, 10), // >> has lower precedence than * and +
            new Test(() -> {
                int a = 4;
                int b = a++ > 4 ? a += 5 : (++a + (a *= 2)) < (a -= 4) ? (a = a + 4) : (a *= ++a);
                a = 4;
                if (a++ > 4) {
                    a += 5;
                } else {
                    System.out.println("(1): " + a);
                    if ((++a + (a *= 2)) < (a -= 4)) {
                        a = a + 4;
                    } else {
                        System.out.println("(2): " + a);
                        a *= ++a;
                        System.out.println("(3): " + a);
                    }
                }
                return a;
            }, 72), // ternary: 
            //                (1) evaluate boolean L-R a++ > 4 -> F
            //                (2) evaluate false expression R-L (++a + (a += 2) < (a -= 3))
            new Test(() -> {
                byte a = 126;
                byte b = (byte)(++a + 1);
                byte c = (byte)(a + b);
                // byte c = (byte)a + (byte)b; - still converts a and b to int
                System.out.println("(1): " + a);
                return a;}, (byte)127), // for our lambda, we need to explicity cast our expected result to byte. See the first few tests above.
            new Test(() -> {
                int a = 3;
                int b = a++ + 2 * --a; // b = 9
                System.out.println("(1): " + b);
                return a;}, 3),
            new Test(() -> {
                int a = -3;
                int b = +a; // this does NOT change -3 to 3
                return a;}, -3),
            new Test(() -> {
                int a = 3;
                int b = -a; // this does NOT change a, however b = -3
                return a;}, 3),
            new Test(() -> {
                int a = 4; 
                int b = 7;
                int c = a & b; // bitwise &, c = 4, 0b0100 & 0b0111 = 0b0100
                boolean d = true & false; // logical &, d = false
                return c;}, 4),
            new Test(() -> {
                int a = 4;
                int b = 7;
                int c = a ^ b; // bitwise ^, c = 3, 0b0100 & 0b0111 = 0b0011
                boolean d = true ^ false; // logical &, d = true
                return c;}, 3),
            new Test(() -> {
                boolean a = true ^ false ^ true; // same as   (true ^ false) ^ true
                                                 //         = true ^ true
                                                 //         = false
                return a;}, false),
            new Test(() -> {
                int a = 4;
                int b = 7;
                int c = a | b; // bitwise |, c = 7, 0b0100 & 0b0111 = 0b0111
                boolean d = true | false; // logical &, d = true
                return c;}, 7),      
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
        int a = 4;
        int b = a++ < 4 ? a += 5 : ++a == (a -= 3) ? (a = a + 4) : (a *= a);
    }

    private void b() {
        int a = 4;
        int b = a++ > 4 ? a += 5 : (++a + (a *= 2)) < (a -= 4) ? (a = a + 4) : (a *= ++a);
    }
}
