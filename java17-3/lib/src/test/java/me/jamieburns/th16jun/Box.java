package me.jamieburns.th16jun;

public class Box<T> { // generic class type
    T t; 
    Box(T t) {}
    T t(T t) {return null;}
    <S, U> Box(S s, U u) {} // generic method type is local to the method
    // static T m(T t) {...} // class parameterised types are instance level
    // <T, U> t(T t, U u) {...} // cannot mix class and method parameterised type
    static <W extends Number> W m(W w) {return null;} // bounded method type
    <X extends Number & A> X m2(X x) {return null;}
    Box<?> m2(Box<?> box) {return null;} // unbounded wildcard (unknown)
    Box<? extends B> m4(Box<? extends B> box) {return null;} // upper bounded wildcard
    Box<? super B> m6(Box<? super B> box) {return null;} // lower bounded wildcard   
}

class Box2<T, U> {}
class Box3<T extends A & B> {} // bounded class type

