package me.jamieburns.tu10may;

public class OverridingAndHiding {
    public static void main(String[] args) {
        var a = new A();
        var b = new B();

        System.out.println(a.s("myString"));
        System.out.println(a.i(123));
        System.out.println(b.s("myString"));
        System.out.println(b.i(123));
        System.out.println(((A)b).s("myString"));
        System.out.println(((A)b).i(123));
        System.out.println(b.s);
        System.out.println(((A)b).s);

        A a2 = new B();
        System.out.println(a2.s("myString"));
    }
}

class A {
    String s = "A";
    CharSequence s(String s) {
        return s.toLowerCase();
    }
    Number i(Integer i) {
        return i;
    }
    private String m() {
        return "A";
    }
    static String m2() {
        return "A";
    }
}

class B extends A {
    String s = "B"; // A.s is HIDDEN by B.s. - ie A.s is NOT overridden by B.s - because Java doesn't override instance variables
    @Override
    protected String s(String s) {
        return s.toUpperCase();
    }
    @Override
    public Integer i(Integer i) {
        return Integer.reverse(i);
    }
    private Integer m() { // Overriding rules are not applied to "overridden" private methods. This is not an override even though it looks like it.
        return 123;
    }
    static String m2() { // A.m2() is HIDDEN by B.m2() because these are static methods with the same signature
        return "B";
    }
}
