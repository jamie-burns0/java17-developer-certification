package me.jamieburns.tu10may;

public class Static {
    public static void main(String[] args) {
        new Thread(new Static2().new NonStaticNested_aka_Inner()).start();
        new Thread(new Static2.StaticNested()).start();
        new Static2().someMethod();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // so nothing
        }
    }
}

class Static2 {

    static String a = "1";
    String b = "2";

    static final class P {
        static void p(final Object o) {
            System.out.println(Thread.currentThread() + ", " + o);
        }
    }
    static class StaticNested implements Runnable {
        @Override
        public void run() {
            var a = Static2.a; // var a = a would be ambiguous
            var x = a;
            // var y = b; not allowed because b needs an enclosing instance
        }
    }

    class NonStaticNested_aka_Inner implements Runnable {
        static String a = "3";
        String b = "4";
        @Override
        public void run() {
            P.p(a); // this and the next one access the same variable
            P.p(NonStaticNested_aka_Inner.a);
            P.p(Static2.a);
            P.p(b);
            P.p(Static2.this.b); // access b from the enclosing class
        }
    }

    void someMethod() {
        class LocalClass implements Runnable {
            @Override
            public void run() {}            
        }
        System.out.println(Thread.currentThread().getName());
        new Thread(new LocalClass()).start();
        new Thread(new StaticNested()).start();
        new Thread(new NonStaticNested_aka_Inner()).start();
    }
}
