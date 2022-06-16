package me.jamieburns.th16may;

import static me.jamieburns.TestSupport.tests;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

import me.jamieburns.Test;

public class Chapter9Test {
    
public static void main(String[] args) {
        new Chapter9Test().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                // Comparable<T>.compareTo(T):int

                final class A implements Comparable<A> {
                    //public final long n = Instant.now().toEpochMilli();
                    public final long n = Math.round(Math.random() * 1_000_000);

                    private final ToIntBiFunction<A, A> fn = (a1, a2) -> {
                        // handle null args?
                        if (a1.n == a2.n) {
                            return 0;
                        } else if (a1.n < a2.n) {
                            return -1;
                        } else {
                            return 1;
                        }
                    };

                    @Override
                    public int compareTo(A a) {
                        return fn.applyAsInt(this, a);
                    }

                    @Override
                    public String toString() {
                        return "" + n;
                    }
                }

                List<A> aList = Arrays.asList(new A[] {new A(), new A(), new A()});
                aList.forEach(System.out::println);
                
                Set<A> tSet = new TreeSet<>(aList);
                tSet.forEach(System.out::println);

                Map<A, A> tMap = new TreeMap<>();
                Map<A, A> hMap = new HashMap<>();
                aList.forEach(a -> tMap.put(a, new A())); // Tree will use Comparable interface to sort
                aList.forEach(a -> hMap.put(a, new A()));

                tMap.forEach((a1, a2) -> System.out.println(a1 + ": " + a2));
                hMap.forEach((a1, a2) -> System.out.println(a1 + ": " + a2));

                aList.sort((a1, a2) -> a1.compareTo(a2)); // sort will use Comparator function
                aList.forEach(System.out::println);

                return 0;}, 0),
            new Test(() -> {
                // Comparator

                final record A(long n1, long n2) {
                    private static final LongSupplier lfn = () -> Math.round(Math.random() * 1_000_000);
                    A() {
                       this(lfn.getAsLong(), lfn.getAsLong());
                    }
                    @Override
                    public String toString() {
                        return "[" + n1 + ", " + n2 + "]";
                    }
                }

                List<A> list = new ArrayList<>(Arrays.asList(new A[] {new A(), new A(), new A(), new A(), new A()}));
                list.forEach(System.out::println);

                Comparator<A> cl = Comparator.comparing(A::n1); // returns a Comparable<A> lambda. Our sort key is n1
                Comparator<A> cl2 = (a1, a2) -> Long.compare(a1.n1(), a2.n1());
                Comparator<A> cl3 = Comparator.comparingLong(A::n2); // comparingLong uses ToLongFunction - takes a Type and gives us a long
                Comparator<A> cl4 = (a1, a2) -> {
                    int a1mod = (int)a1.n1() % 2;
                    int a2mod = (int)a2.n1() % 2;
                    if (a1mod == a2mod) {
                        return 0;  // both even or both odd
                    } else if (a1mod == 0) {
                        return -1; // a1 is even, a2 is odd
                    } else {
                        return 1;  // a1 is odd, a2 is even
                    }
                };
                Comparator<A> cl5 = cl4.thenComparing(cl3); // if first Comparator returns 0, use the second Comparator

                List<Comparator<A>> cList = List.of(cl, cl2, cl3, cl4, cl5);

                BiConsumer<List<A>, Comparator<A>> cfn = (l, fn) -> {
                    System.out.println("---");
                    List<A> l2 = new ArrayList<>();
                    l.forEach(l2::add);
                    l2.sort(fn);
                    l2.forEach(System.out::println);
                };
                
                cList.forEach(c -> cfn.accept(list, c));

                return 0;}, 0),
            new Test(() -> {
                // Collections.binarySearch


                return 0;}, 0),
            new Test(() -> {
                class A {
                    public static <T extends Number> T m1(List<T> t) {
                        return t.get(0);
                    }

                    public static <T extends Number> List<T> m2(List<T> t) {
                        return t;
                    }
                }

                Number n1 = A.m1(Arrays.<Integer>asList(1));
                List<Integer> n2 = A.m2(Arrays.asList(2));

                return 0;}, 0),
            new Test(() -> {
                
                class A {
                    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
                        int count = 0;
                        for (T e : anArray)
                            if (e.compareTo(elem) > 0)
                                ++count;
                        return count;
                    }

                    public static <T> T doSomething(List<? extends Number> list, T t) {
                        Number n = list.get(0);
                        return t;
                    }
                }

                int c = A.countGreaterThan(new String[] {"james", "robert", "burns"}, "liam");
                String s = A.doSomething(Arrays.<Integer>asList(Integer.valueOf(1)), "james");
                LocalDateTime dt = A.doSomething(Arrays.<Long>asList(Long.valueOf(1L)), LocalDateTime.now().plusDays(2L));

                return 0;}, 0),
                new Test(() -> {

                    /*
                    copy(src, dest) where src is an "in" arg and dest is an "out" arg
                    
                    An "in" variable is defined with an upper bounded wildcard, using the extends keyword.
                    An "out" variable is defined with a lower bounded wildcard, using the super keyword.
                    In the case where the "in" variable can be accessed using methods defined in the Object class, use an unbounded wildcard.
                    In the case where the code needs to access the variable as both an "in" and an "out" variable, do not use a wildcard.

                    Using a wildcard as a return type should be avoided because it forces programmers using the code to deal with wildcards.
                    */

                    class A {
                        public static void m1(List<Number> bl) {}
                        public static void m2(List<? extends Number> ubl) {for (Number n : ubl) {n.byteValue();}}
                        public static void m3(List<? super Integer> lbl) {for (Object o : lbl) {o.toString();}}
                        public static void m4(List<?> ul) {System.out.println(ul);}
                    }
                    
                    A.m1(Arrays.<Number>asList(1, 2L, 3.0)); // Integer, Long, Double
                    // A.m1(Arrays.<Integer>asList(...));  * List<Integer> is not a sub-class of List<Number>
                    
                    A.m2(Arrays.<Number>asList(1, 2L)); // Integer, Long
                    A.m2(Arrays.<Integer>asList(1, 2)); // m2 accepts a List of Number and a List of any of Number's subclasses

                    A.m3(Arrays.<Integer>asList(1, 2)); 
                    A.m3(Arrays.<Number>asList(1, 2L)); // accepts a List of Integer and a List of any of Integer's super classes
                    A.m3(Arrays.<Object>asList(1, 2L, new Object())); // accepts a List of Integer and a List of any of Integer's super classes

                    A.m4(Arrays.<Number>asList(1, 2L));
                    A.m4(Arrays.<Integer>asList(1, 2));
                    A.m4(Arrays.<String>asList("jamie", "burns"));
                    A.m4(Arrays.<Object>asList("jamie", 1, 2.0, LocalDateTime.now()));

                    return 0;}, 0),
                new Test(() -> {
                    class A {
                        public static Predicate<Number> pfn = n -> n.doubleValue() % 2 == 1;
                        public static int countOdds(Collection<? extends Number> c) {
                            int count = 0;
                            for (Number n : c) {
                               if (pfn.test(n)) {
                                   count++;
                               }
                            }
                            return count;
                        }
                    }

                    int oddCount = A.countOdds(Arrays.asList(1, 2.0, 3L, 0100, Integer.valueOf(5), Long.valueOf(9)));

                    System.out.println(oddCount);
                    
                    return 0;}, 0),
                new Test(() -> {
                    class A {
                        public static <T> void swap(T[] ar, T e1, T e2) {
                            int e1index = 0, e2index = 0;
                            for (int i = 0; i < ar.length; i++) {
                                if (ar[i].equals(e1)) {
                                    e1index = i;
                                } else if (ar[i].equals(e2)) {
                                    e2index = i;
                                }
                            }

                            /* EITHER this,
                            T temp = ar[e1index];
                            ar[e1index] = ar[e2index];
                            ar[e2index] = temp;
                            */

                            /* OR this */
                            List<? extends T> list = Arrays.<T>asList(ar); // effectively wraps an array
                            System.out.println(list);
                            Collections.swap(list, e1index, e2index);
                            System.out.println(list);
                            /* */
                        }
                    }

                    Number[] na = new  Number[] {1, 2, 3.0, 4L};
                    System.out.println(na[0]);
                    A.swap(na, Integer.valueOf(1), Double.valueOf(3.0));
                    System.out.println(na[0]);

                    return 0;}, 0),
                new Test(() -> {
                    class A {
                        public static <T extends Object & Comparable<T>> T max(List<? extends T> list, int begin, int end) {
                            T maxElem = list.get(begin);
                            for (++begin; begin < end; ++begin)
                                if (maxElem.compareTo(list.get(begin)) < 0)
                                    maxElem = list.get(begin);
                            return maxElem;
                        }
                    }

                    List<Integer> ln = Arrays.asList(1, 2, 3, 0100, Integer.valueOf(5), Integer.valueOf(9));
                    Integer max = A.max(ln, 1, 6);
                    System.out.println(max);
                    
                    return 0;}, 0),
                new Test(() -> {
                    Object[] o = new Object[] {new Object(), new String()};
                    Object[] o2 = {o, new Object(), "jamie"};

                    int search = Arrays.binarySearch(new int[] {1, 2, 4}, 3);
                    System.out.println(search);
                    return 0;}, 0),
                new Test(() -> {
                    LocalDate d = LocalDate.of(2022, 03, 13);
                    LocalTime t = LocalTime.of(2, 30);
                    ZoneId z = ZoneId.of("US/Eastern");
                    ZonedDateTime zdt = ZonedDateTime.of(d, t, z);
                    System.out.println(zdt);

                    LocalDate d2 = LocalDate.of(2022, 11, 06);
                    LocalTime t2 = LocalTime.of(1, 30);
                    ZoneId z2 = ZoneId.of("US/Eastern");
                    ZoneOffset zo4 = ZoneOffset.ofHours(-4);
                    ZoneOffset zo5 = ZoneOffset.ofHours(-5);

                    System.out.println(ZonedDateTime.of(d2, t2, z2));              // our 01:30 daylight time 01:30-04:00[US/Eastern]
                    System.out.println(ZonedDateTime.of(d2, t2, z2).plusHours(1)); // our 01:30 standard time 01:30-05:00[US/Eastern]
                    System.out.println(ZonedDateTime.of(d2, t2, zo4));             //                                ^
                    System.out.println(ZonedDateTime.of(d2, t2, zo5));

                    return 0;}, 0),
                new Test(() -> {
                    var base = "ewe\nsheep\\t";
                    System.out.println(base.length());
                    System.out.println(base.translateEscapes().length());

                    int a = 6; 
                    long b = 6;
                    
                    return 0;}, 0),
                new Test(() -> {
                    class A {
                        public void a(int a) {System.out.println("a(int)");}
                        public void a(Integer a) {System.out.println("a(Integer)");}
                        public void a(int... a) {System.out.println("a(int...)");}
                        public void a(Object a) {System.out.println("a(Object)");}
                    }
                    return 0;}, 0),
                new Test(() -> {
                    class A {
                        public static String a = "A";
                        public String b = "A";
                        public static String c() {return "A";}
                        public String d() {return "A";}
                    }

                    class B extends A {
                        public static String a = "B"; // B.a hides A.a
                        public String b = "B";
                        public static String c() {return "B";}
                        public String d() {return "B";}
                    }

                    System.out.println("* 2 *");
                    System.out.println(new A().a);   // A
                    System.out.println(new A().b);   // A
                    System.out.println(new A().c()); // A
                    System.out.println(new A().d()); // A
                    
                    System.out.println("--- 1");
                    System.out.println(((A)new B()).a);   // A
                    System.out.println(((A)new B()).b);   // A
                    System.out.println(((A)new B()).c()); // A **
                    System.out.println(((A)new B()).d()); // B **

                    System.out.println("--- 2");
                    System.out.println(new B().a);   // B
                    System.out.println(new B().b);   // B
                    System.out.println(new B().c()); // B
                    System.out.println(new B().d()); // B

                    return 0;}, 0),
                new Test(() -> {
                    record Box<T>(T t) {}
                    interface A {
                        default void a() {System.out.println("A::a");}
                    }
                    interface B extends A {
                        default void b() {System.out.println("B::b");}
                    }
                    interface C extends B {
                        default void c() {System.out.println("C::c");}
                    }
                    class Z {
                        public static void m1(Box<? super B> o) {
                            // parameterised type can be B, A or Object
                            // BUT, we can only call methods on Object
                            o.t().toString();}
                        public static void m2(Box<? extends B> o) {
                            // parameterised type can be B or C
                            // BUT, we can call methods on B, A or Object
                            o.t().a();
                            o.t().b();
                        }
                        public static void m3(Box<?> o) {
                            // parameterised type can be any type
                            // BUT, we can only call methods on Object
                            o.t().toString();
                        }
                    }
                    Z.m1(new Box<Object>(new Object(){}));
                    Z.m1(new Box<Object>(new A(){}));
                    Z.m1(new Box<Object>(new B(){}));
                    Z.m1(new Box<Object>(new C(){}));

                    Z.m1(new Box<A>(new A(){}));
                    Z.m1(new Box<A>(new B(){}));
                    Z.m1(new Box<A>(new C(){}));
                    
                    Z.m1(new Box<B>(new B(){}));
                    Z.m1(new Box<B>(new C(){}));

                    Z.m2(new Box<B>(new B(){}));
                    Z.m2(new Box<B>(new C(){}));

                    Z.m2(new Box<C>(new C(){}));
           
                    return 0;}, 0),
/*                new Test(() -> {
                    class Box<T> {
                        private T t;
                        Box(T t) {
                            this.t = t;
                        }
                        public T t() {return t;}
                        private T m;
                        public void m(T t) {m = t;}
                        public T m() {return m;}
                        private T m2;
                        public void m2(T t) {m2 = t;}
                        public T m2() {return m2;}
                    }
                    record Box2<T extends Number>(T t) {}
                    record RawBox(Object o) {
                        public void m(Object o) {}
                    }
                    interface A {
                        default void a() {System.out.println("A::a");}
                    }
                    interface B extends A {
                        default void b() {System.out.println("B::b");}
                    }
                    interface C extends B {
                        default void c() {System.out.println("C::c");}
                    }
                    class Z {
                        public static void m1(Box<? super B> box) {
                            // parameterised type can be B, A or Object
                            box.t(); // can only call Object methods on parameterised type t
                            box.m(new B(){}); // can pass B or any subclass of B into a method on box
                            box.m2(new C(){});
                            Object m = box.m();
                            Object m2 = box.m2();
                        }
                        public static void mm1() {
                            Box<?> box3 = new Box<>("jamie");
                            //box3.m("Integer.valueOf(1)");
                            //box3.m2(new A(){});
                            //Z.m1(box3);

                            Box<Temporal> box4 = new Box<>(Instant.now());
                            box4.m(LocalDate.now());
                            box4.m2(ZonedDateTime.now());
                            //Z.m1(box4);

                            var box = new Box<Integer>(Integer.valueOf(1));
                            var box2 = new Box2<Integer>(Integer.valueOf(1));
                            Z.m1(new Box<Object>(new Object()));
                            Z.m1(new Box<Object>(new A(){}));
                            Z.m1(new Box<Object>(new B(){}));
                            Z.m1(new Box<Object>(new C(){}));

                            Z.m1(new Box<A>(new A(){}));
                            Z.m1(new Box<A>(new B(){}));
                            Z.m1(new Box<A>(new C(){}));

                            Z.m1(new Box<B>(new B(){}));
                            Z.m1(new Box<B>(new C(){}));
                        }
                        public static void m1_1(RawBox box) {
                            box.m(new Object());
                            box.m(new A(){});
                            box.m(new B(){});
                            box.m(new C(){});
                        }
                        public static void m1_2(Box<?> box) { // is this the same as Box<? extends Object>
                            
                        }
                        public static void m1_3(Box<? super Object> box) {
                            //box.add(new Object());
                            //box.add(new A(){});
                            //...
                        }
                        public static void m1_4(List<? super B> list) {
                            // parameterised type can be B, A or Object
                            // We can only add anything assignable to B
                            list.add(new B(){});
                            list.add(new C(){});
                        }
                        public static void m2(Box<? extends B> box) {
                            // parameterised type can be B or C
                            // BUT, we can call methods on B, A or Object
                            //box.add(new Object(){});
                            box.t().a();
                            box.t().b();
                            //box.m(new B(){});
                        }
                        public static void mm2() {
                            //Z.m2(new Box<B>(new B(){}));
                        }
                        public static void m2_1(List<? extends B> o) {
                            // parameterised type can be B or C
                            // BUT, we can call methods on B, A or Object
                            //o.add(new B(){});
                            //o.add(new C(){});
                        }
                        public static void m3(Box<?> o) {
                            // parameterised type can be any type
                            // BUT, we can only call methods on Object
                        }
                        public static void m3_1(List<?> o) {
                            // parameterised type can be any type
                            // BUT, we can only call methods on Object
                            //o.add(new Object());
                            //o.add(new A(){});
                        }
                    }

                    List<B> l1 = new ArrayList<B>();

                    Z.m1(new Box<Object>(new Object()));
                    Z.m1(new Box<Object>(new A(){}));
                    Z.m1(new Box<Object>(new B(){}));
                    Z.m1(new Box<Object>(new C(){}));

                    Z.m1_1(new RawBox(new ArrayList<Object>()));
                    Z.m1_1(new RawBox(Integer.valueOf(1)));
                    Z.m1_1(new RawBox(2.0));

                    Z.m1(new Box<A>(new A(){}));
                    Z.m1(new Box<A>(new B(){}));
                    Z.m1(new Box<A>(new C(){}));
                    
                    Z.m1(new Box<B>(new B(){}));
                    Z.m1(new Box<B>(new C(){}));

                    Z.m2(new Box<B>(new B(){}));
                    Z.m2(new Box<B>(new C(){}));

                    Z.m2(new Box<C>(new C(){}));
            
                    return 0;}, 0),  
                */ 
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
