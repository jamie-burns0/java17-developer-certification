package me.jamieburns.mo16may;

import static me.jamieburns.TestSupport.tests;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

import me.jamieburns.Test;

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
                // IntSupplier.getAsInt():int
                // LongSupplier.getAsLong():long
                // DoubleSupplier.gatAsDouble():double

                BooleanSupplier bsl = () -> Math.floor(5.4) == 5.0;
                System.out.println(bsl.getAsBoolean());

                IntSupplier isl = () -> Integer.valueOf(123).intValue();
                System.out.println(String.format("int is %06d", isl.getAsInt()));

                DoubleSupplier dsl = () -> Double.valueOf(4.0 / 3);
                System.out.println(String.format("double is %.2f", dsl.getAsDouble()));

                LongSupplier lsl = () -> Long.valueOf(Math.round(Math.random() * 1_000_000));
                System.out.println("long is " + NumberFormat.getCurrencyInstance(new Locale("en", "AU")).format(lsl.getAsLong()));

                return 0;}, 0),
            new Test(() -> {
                
                return 0;}, 0),
            new Test(() -> {
                // IntConsumer.accept(int):void
                // LongConsumer.accept(long):void
                // DoubleConsumer.accept(double):void

                IntConsumer icl = i -> System.out.println(String.format("consumed int %d", i));
                icl.accept(123);

                LongConsumer lcl = l -> System.out.println(String.format("consumed long %1d", l));
                lcl.accept(Math.round(Math.random() * 1_000_000));
                
                DoubleConsumer dcl = (double d) -> System.out.println(String.format("consumed double %,.1f", d));
                dcl.accept(Math.random() * 1_000_000);

                return 0;}, 0),
            new Test(() -> {
                // IntPredicate.test(int):boolean
                // LongPredicate...
                // DoublePredicate...

                IntPredicate ipl = i -> i % 2 == 0;
                LongPredicate lpl = l -> l % 3 == 0;
                DoublePredicate dpl = d -> d % 5 == 0;

                for (Number n : new Number[] {5, 9L, 400.5}) {
                    boolean b = switch(n.getClass().getTypeName()){
                        case "java.lang.Integer" -> ipl.test(n.intValue());
                        case "java.lang.Long" -> lpl.test(n.longValue());
                        case "java.lang.Double" -> dpl.test(n.doubleValue());
                        default -> false;
                    };
                    System.out.println("For " + n + " result is " + b);
                }
                return 0;}, 0),
            new Test(() -> {
                // IntFunction.apply(int):R
                // LongFunction...
                // DoubleFunction...

                class A {
                    public static final String a(Number n) {
                        return "I'm happy with " + n;
                    }
                }

                IntFunction<String> ifmr = A::a;
                LongFunction<String> lfmr = A::a;
                DoubleFunction<String> dfmr = A::a;

                System.out.println(ifmr.apply(12345));
                System.out.println(lfmr.apply(234567L));
                System.out.println(dfmr.apply(400.5));

                return 0;}, 0),
            new Test(() -> {
                // IntUnaryOperator.applyAsInt(int):int
                // LongUnaryOperator...
                // DoubleUnaryOperator...

                IntUnaryOperator iuol = i -> ++i;
                DoubleUnaryOperator duol = d -> ++d;

                System.out.println(iuol.applyAsInt(123));
                System.out.println(duol.applyAsDouble(3.5));

                return 0;}, 0),
            new Test(() -> {
                // IntBinaryOperator.applyAsInt(int, int):int
                // LongBinaryOperator...
                // DoubleBinaryOperator...

                DoubleBinaryOperator dbol = (var d1, var d2) -> (d1 + d2) * (d2 - d1);
                System.out.println(dbol.applyAsDouble(3.5, 12.9));

                return 0;}, 0),
            new Test(() -> {
                // ToIntFunction.applyAsInt(T):int
                // ToLongFunction...
                // ToDoubleFunction...

                record A(int i, long l, double d) {}

                class B {
                    public static final int a(A a) {return a.i();}
                    public static final long b(A a) {return a.l();}
                    public static final double c(A a) {return a.d();}
                }

                ToIntFunction<A> tifmr = B::a;
                ToLongFunction<A> tlfmr = B::b;
                ToDoubleFunction<A> tdfmr = B::c;

                A a = new A(123, 456L, 78.9);

                System.out.println(tifmr.applyAsInt(a));
                System.out.println(tlfmr.applyAsLong(a));
                System.out.println(tdfmr.applyAsDouble(a));

                return 0;}, 0),
            new Test(() -> {
                // ToIntBiFunction.applyAsInt(T, U):int
                // ToLongBiFunction.applyAsLong(T, U):int 
                // ToDoubleBiFunction...

                // DoubleToIntFunction.applyAsInt(double):int
                // LongToIntFunction.applyAsInt(long):int
                // IntToLongFunction.applyAsLong(int):long
                // DoubleToLongFunction.applyAsLong(double):long
                // IntToDoubleFunction...
                // LongToDoubleFunction...

                return 0;}, 0),
            new Test(() -> {
                // ObjIntConsumer.accept(T, int):void
                // ObjLongConsumer...
                // ObjDoubleConsumer...

                record A(String s) {}

                ObjIntConsumer<A> oicl = (final A a, int i) -> System.out.println(Integer.valueOf(a.s()).intValue() == i);
                oicl.accept(new A("123"), 123);
                oicl.accept(new A("123"), 234);

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
