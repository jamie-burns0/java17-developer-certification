package me.jamieburns.streams;

import java.util.function.BiPredicate;

public class Test3<T> {
    private Tester<T> tester;
    private T expectedResult;
    private BiPredicate<T, T> predicate;
    
    public Test3(Tester<T> tester, T expectedResult, BiPredicate<T, T> predicate) {
        this.tester = tester;
        this.expectedResult = expectedResult;
        this.predicate = predicate;
    }

    public Test3(Tester<T> tester, T expectedResult) {
        this(tester, expectedResult, (T a, T b) -> a.equals(b));
    }

    public Tester<T> tester() {return tester;}
    public T expectedResult() {return expectedResult;}
    public BiPredicate<? super T, ? super T> predicate() {return predicate;}
}
