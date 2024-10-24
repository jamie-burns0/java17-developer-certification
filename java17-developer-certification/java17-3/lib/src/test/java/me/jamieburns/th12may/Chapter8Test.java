package me.jamieburns.th12may;

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
                class A {
                    public void aa(LambdaA a) {a.a();}
                }
                
                // LambdaA.a():void - takes no arguments, returns nothing

                new A().aa(() -> System.out.println("in LambdaA"));
                
                LambdaA l = () -> System.out.println("by lambda");
                LambdaA mr = System.out::println; // compiler won't let me do System.out::println("by method reference");

                new A().aa(l);
                new A().aa(mr);

                LambdaA l2 = () -> DoNothing.withNothing();
                LambdaA mr2 = DoNothing::withNothing;

                new A().aa(l2);
                new A().aa(mr2);

                return 0;}, 0),
            new Test(() -> {
                class B {
                    public void bb(String s, LambdaB b) {b.b(s);}
                }

                // LambdaB.b(String):void - takes one String argument, returns nothing

                new B().bb("B1", s -> System.out.println(s));

                LambdaB l = s -> System.out.println(s);
                LambdaB mr = System.out::println;

                new B().bb("B2", l);
                new B().bb("B3", mr);

                LambdaB l2 = o -> DoNothing.withOneThing(o);
                LambdaB mr2 = DoNothing::withOneThing;

                new B().bb("battleship", l2);
                new B().bb("balloon", mr2);

                return 0;}, 0),
            new Test(() -> {
                class C {
                    public void cc(String s1, String s2, LambdaC c) {c.c(s1, s2);}
                }

                // LambdaC.c(String, String):void - takes two String arguments, returns nothing

                new C().cc("C1", "C2", (s1, s2) -> System.out.println(s1 + s2));
                new C().cc("CAKE", "CAR", (s1, s2) -> DoNothing.withTwoThings(s1, s2));
                new C().cc("CALM", "COOL", DoNothing::withTwoThings);
                 
                return 0;}, 0),
            new Test(() -> {
                class D {
                    public void dd(Object[] oa, LambdaD d) {d.d(oa);}
                }

                new D().dd(new String[] {"D1", "D2", "D3"}, (oa) -> {
                    StringBuilder sb = new StringBuilder();
                    for (Object o : oa) {
                       sb.append(o.toString() + " "); 
                    }
                    System.out.println(sb.toString());
                });

                new D().dd(new String[] {"DRAKE", "DROP", "DRAIN"}, DoNothing::withManyThings);

                return 0;}, 0),
            // all the above method references were on static methods. Let's try
            new Test(() -> {
                
                // (1) static method on a class
                // 
                // our single abstract method takes one String and returns a String
                // for (1), our *method reference* must be a static method on any class that takes one String, s, and returns a String
                //
                // anyStaticMethod(String):String
                //
                interface StringThing {
                    String apply(String s);
                }

                class A {
                    public static String myStaticStringMethod(String s) {
                        return s.concat("we've been in A.myStaticStringMethod()");
                    }
                }

                String s = "Bunny";

                StringThing l = s2 -> s2.toUpperCase();
                StringThing mr = A::myStaticStringMethod;

                System.out.println(l.apply(s));
                System.out.println(mr.apply(s));

                return 0;}, 0),
            new Test(() -> {

                // (2) instance method on an object
                //
                // our single abstract method takes one String and returns a String
                // for (2), our *method reference* method must be a String instance method that takes one String, s, and returns a String
                //
                // stringInstanceMethod(String):String
                //
                interface StringThing {
                    String apply(String s); 

                }

                String s = "Effective";

                StringThing l = s2 -> s2.toUpperCase(); // toUpperCase():String
                StringThing mr = s::concat; // method takes a String and returns a String. concat(String):String
                // StringThings mr2 = s::compareTo; // method must return a String. compareTo(String):int
                // StringThings mr3 = s::toUpperCase; // method must take a String. toUpperCase():String

                System.out.println(l.apply(s));
                System.out.println(mr.apply(s));

                return 0;}, 0),
            new Test(() -> {
                
                // (3) instance method on a parameter

                // our single abstract method takes a String and returns a boolean
                // for (3), our *method reference* method must be a method on a String instance, s, that takes no arguments and returns a boolean, 
                //
                // stringInstanceMethod():boolean
                //
                // in this case the String we pass to apply() becomes the String our String instance method is called on. Its the same as,
                //
                // boolean b = s.isEmpty()
                //
                interface StringThing {
                    boolean apply(String s);
                }

                String s = "famous";

                StringThing mr = String::isEmpty;

                System.out.println(mr.apply(s));

                // our single abstact method takes two Strings and returns a String
                // for (3), our *method reference* must be a method on a String instance, s, that takes one String, s2, and returns a String
                //
                // stringInstanceMethod(String):String

                interface StringThing2 {
                    String apply(String s, String s2);
                }

                String s2 = "favoured";
                String s3 = "flavour";

                StringThing2 mr2 = String::concat; // this will become s.concat(s2)

                System.out.println(mr2.apply(s2, s3));

                return 0;}, 0),
            new Test(() -> {
                
                // (4) constructor reference
                //
                // our single abstract method takes no arguments and returns a String
                // for (4), our *method reference* must be a constructor that takes no arguments and constructs a String
                //
                // stringConstructor():String
                //
                // Same as,
                //
                // String s = new String();
                interface StringThing {
                    String apply();
                }

                StringThing mr = String::new;

                System.out.println(mr.apply().getClass().getName());

                // our single abstract method takes one String and returns a StringBuilder
                // for (4), our *method reference* must be a constructor that takes one String and constructs a StringBuilder
                interface StringThing2 {
                    StringBuilder apply(String s);
                }

                String s = "Glorious";

                StringThing2 mr2 = StringBuilder::new;

                System.out.println(mr2.apply(s).toString());

                return 0;}, 0),
            new Test(() -> {
                // Supplier.get():T

                Supplier<String> l = () -> new String(java.time.LocalTime.now().toString());
                Supplier<String> mr = String::new; // (4) constructor

                System.out.println(l.get());
                System.out.println(mr.get());
                
                return 0;}, 0),
            new Test(() -> {
                // Consumer.accept(T):void

                StringBuilder sb = new StringBuilder();
                List<String> sl = new ArrayList<>();

                Consumer<String> l = s2 -> sl.add(s2);
                Consumer<String> mr = sl::add; // (2) instance method on an object
                Consumer<String> l2 = s2 -> sb.append(s2);
                Consumer<String> mr2 = sb::append; // (2) instance method on an object
                Consumer<String> mr3 = String::toString; // (3) instance method on a parameter
                Consumer<String> mr4 = String::valueOf; // (1) static method

                l.accept("Wobbly");
                mr.accept("Xray");
                l2.accept("Yellow");
                mr2.accept("Zombie");
                mr3.accept("Admirable");
                mr4.accept("Beautiful");

                System.out.println(sb.toString());
                System.out.println(sl.toString());

                return 0;}, 0),
            new Test(() -> {
                // BiConsumer.accept(T, U):void

                record A(StringBuilder sb) {
                    void add(String s1, String s2) {
                        sb.append(s1);
                        sb.append(s2);
                    }
                }

                StringBuilder sb = new StringBuilder();
                List<String> sl = new ArrayList<>();

                A a = new A(sb);

                BiConsumer<String, String> l = (s1, s2) -> {sl.add(s1); sl.add(s2);};
                BiConsumer<String, String> mr = a::add; // (2) instance method on an object
                BiConsumer<String, String> l2 = (s1, s2) -> sb.append(s1).append(s2);
                BiConsumer<StringBuilder, String> mr2 = StringBuilder::append; // (2) instance method on an object

                l.accept("India", "Juliette");
                mr.accept("Kilo", "Lima");
                l2.accept("Mother", "November");
                mr2.accept(sb, "Oscar");

                System.out.println(sb);
                System.out.println(sl);

                return 0;}, 0),
            new Test(() -> {
                // Predicate.test(T):boolean

                Predicate<String> l = s -> "Alien".equalsIgnoreCase(s);
                Predicate<String> mr = String::isBlank; // type (3) instance method on a parameter
                Predicate<String> l2 = s-> Boolean.parseBoolean(s);
                Predicate<String> mr2 = Boolean::parseBoolean; // (1) static method

                boolean b = l.test("Non-alien");  // false
                boolean b2 = mr.test("   a    "); // false
                boolean b3 = l2.test("TRUE"); // true
                boolean b4 = mr2.test("Whats up doc"); // false

                System.out.println("" + b + b2 + b3 + b4);

                return 0;}, 0),
            new Test(() -> {
                // BiPredicate.test(T, U): boolean

                record A(String firstWord, String secondWord) {}
                record B(String firstName, String lastName) {
                    boolean equivalent(A a) {return firstName.equals(a.firstWord()) && lastName.equals(a.secondWord());}
                }

                BiPredicate<String, Integer> l = (s, i) -> s.equals(i.toString());
                BiPredicate<String, String> mr = String::equalsIgnoreCase; // (3) instance method on a parameter
                BiPredicate<A, B> l2 = (a, b) -> b.equivalent(a);
                BiPredicate<B, A> mr2 = B::equivalent; // (3) instance method on a parameter
                
                boolean b = l.test("123", 123); // true
                boolean b2 = mr.test("apples", "APPLES"); // true
                boolean b3 = l2.test(new A("Jamie", "Burns"), new B("Jamie", "Burns")); // true
                boolean b4 = mr2.test(new B("hot", "dog"), new A("fairy", "floss")); // false

                System.out.println("" + b + b2 + b3 + b4);

                return 0;}, 0),
            new Test(() -> {
                // Function.apply(T):R

                Function<String, Integer> l = s -> Integer.valueOf(s);
                Function<String, Integer> mr = Integer::valueOf; // (1) static method

                Integer i = l.apply("123");
                Integer i2 = mr.apply("234");

                System.out.println(i);
                
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
            new Test(() -> {return 0;}, 0)
        );
    }
}
