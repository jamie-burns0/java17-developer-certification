package me.jamieburns.mo16may;

import me.jamieburns.Test;

import static me.jamieburns.TestSupport.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Chapter8Test {
    
    public static void main(String[] args) {
        new Chapter8Test().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                interface A {
                    Integer apply(String s);
                }
                
                String s = "123";

                Integer i = new A() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.valueOf(s);
                    }
                }.apply("234");

                A a = s1 -> Integer.valueOf(s1);
                a.apply(s);

                A a2 = Integer::valueOf;
                a2.apply("345");

                return 0;}, 0),
            new Test(() -> {
                // Function.apply(T):R

                String s = "123";

                Function<String, Integer> l = s2 -> Integer.valueOf(s + s2);
                Function<String, Integer> mr = Integer::valueOf;

                System.out.println(l.apply("234"));
                System.out.println(mr.apply(s + "345"));
                
                return 0;}, 0),
            new Test(() -> {
                // BiFunction.apply(T, U):R

                interface A {
                    Integer a(String s1, String s2);
                    default Integer b(String s1, String s2) {
                        return Integer.valueOf(s1 + s2);
                    }
                    static Integer c(String s1, String s2) {
                        return Integer.valueOf(s1 + s2);
                    }
                }

                BiFunction<String, String, Integer> l = (s1, s2) -> Integer.valueOf(s1 + s2);

                A a = (s1, s2) -> Integer.valueOf(s1 + s2);

                BiFunction<String, String, Integer> mr = a::a; // (2) instance method on an object
                BiFunction<String, String, Integer> mr2 = A::c; // (1) static method

                interface B {
                    Integer b(String s);
                }

                B lb = s -> Integer.valueOf(s);
                B mrb = Integer::valueOf;

                interface C {
                    Integer c(B b, String s);
                }

                BiFunction<C, String, Integer> l2 = (c, s) -> { return c.c(lb, s);};

                BiFunction<B, String, Integer> mr3 = B::b; // (3) instance method on a parameter
                System.out.println(mr3.apply(lb, "234567"));
                System.out.println(mr3.apply(mrb, "345678"));
                
                return 0;}, 0),
            new Test(() -> {
                // UnaryOperator(T):T

                UnaryOperator<Float> l = f -> Math.nextUp(f);
                UnaryOperator<Float> mr = Math::nextUp; // (1) static method

                System.out.println(l.apply(3.2F));
                System.out.println(mr.apply(4.6F));

                Function<UnaryOperator<Float>, Float> l2 = uol -> uol.apply(3.2F);
                System.out.println(l2.apply(l));

                BiFunction<UnaryOperator<Float>, Float, Float> l3 = (uol, f) -> uol.apply(f);
                System.out.println(l3.apply(mr, 4.6F));

                return 0;}, 0),
            new Test(() -> {
                // BinaryOperator.apply(T, T):T

                BinaryOperator<String> bol = (s1, s2) -> s1.concat(s2);
                BinaryOperator<String> bomr = String::concat; // (3) instance method on a parameter

                System.out.println(bol.apply("Apple", "Pie"));
                System.out.println(bomr.apply("Mango", "Smoothie"));

                record A(String a) {}

                interface B {
                    A join(A a1, A a2); // our single abstract method
                }

                B bl = (a1, a2) -> new A(a1.a() + a2.a()); // our single abstract method implementation

                BinaryOperator<A> bol2 = (a1, a2) -> bl.join(a1, a2);
                BinaryOperator<A> bol3 = (a1, a2) -> new B() {
                    @Override
                    public A join(A a1, A a2) {
                        return new A(a1.a() + a2.a());
                    }
                }.join(a1, a2);
            
                System.out.println(bol2.apply(new A("Red"), new A("Hot")));
                System.out.println(bol3.apply(new A("Wipe"), new A("Out")));
                
                return 0;}, 0),
            new Test(() -> {
                
                interface A {
                    Integer asInteger();
                }

                record B(String s) implements A {

                    private static final String PREPEND_NUMBER = "4";

                    public static String b(String s) {
                        return PREPEND_NUMBER.concat(s);
                    }

                    B {
                        s = b(s);
                    }

                    @Override
                    public Integer asInteger() {
                        return Integer.valueOf(s);
                    }
                };

                StringBuilder sb = new StringBuilder();
                
                class Sum {
                    int i = 0;
                }

                final Sum sum = new Sum();

                Consumer<A> c = a -> {
                    sum.i += a.asInteger().intValue();
                };

                for (String s : new String[] {"123", "234", "345"}) {
                    c.accept(new B(s));
                }

                System.out.println(sum.i);

                return 0;}, 0),
            new Test(() -> {
                // Consumer.andThen(Consumer):void

                class A {
                    int i = 0;
                }
                
                A a = new A();

                Consumer<String> lc = s -> System.out.println(s + a.i++);

                Consumer<String> comblc = lc.andThen(lc).andThen(lc).andThen(lc);

                comblc.accept("target");

                return 0;}, 0),
            new Test(() -> {
                // Function.andThen(Function):R
                // Function.compose(Function):R

                class A {
                    int i = 0;
                }
                
                A a = new A();
                
                Function<String, String> lf = s -> s.concat("in lf");
                Function<String, String> lf2 = s -> s.concat("in lf2");
                Function<String, String> comblf = lf.andThen(lf2);

                String s2 = comblf.apply("Anthem");

                System.out.println(s2);

                Function<String, String> complf = lf.compose(lf2);

                a.i = 0;

                String s3 = complf.apply("Anthem");

                System.out.println(s3);

                return 0;}, 0),
            new Test(() -> {
                // Predicate.and(...):boolean
                // Predicate.or(...):boolean
                // Predicate.negate(...):boolean
                class A {
                    Boolean a = null;
                    Boolean b = null;
                }
                
                A a = new A();

                record B(String s) {};

                Predicate<B> lb = b -> a.a = new B("Fried").equals(b);
                Predicate<B> lb2 = b -> a.b = new B("Eggs").equals(b);

                Predicate<B> andlb = lb.and(lb2);               
                andlb.test(new B("Fried"));
                System.out.println(a.a + ", " + a.b);

                a.a = null;
                a.b = null;

                Predicate<B> orlb = lb.or(lb2);
                orlb.test(new B("Fried"));
                System.out.println(a.a + ", " + a.b);

                a.a = null;
                a.b = null;

                Predicate<B> negatelb = lb.and(lb2.negate()); // a.b will still be falise. negate() will return true.
                negatelb.test(new B("Fried"));
                System.out.println(a.a + ", " + a.b);
                
                return 0;}, 0),
            new Test(() -> {
                // BooleanSupplier.getAsBoolean():boolean

                BooleanSupplier bsl = () -> Math.floor(5.4) == 5.0;
                System.out.println(bsl.getAsBoolean());

                return 0;}, 0),
            new Test(() -> {
                
                return 0;}, 0),
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
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0)
        );
    }

    private void a() {
        interface A {
            Integer apply(String s);
        }

        A a = new A() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };
    }
}
