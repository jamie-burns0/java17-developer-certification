package me.jamieburns.streams;

import java.util.function.BiPredicate;

public class Test<T> {
    private Tester<T> tester;
    private T expectedResult;
    private BiPredicate<T, T> predicate;

    private T actualResult;
    
    public Test(Tester<T> tester, T expectedResult, BiPredicate<T, T> predicate) {
        this.tester = tester;
        this.expectedResult = expectedResult;
        this.predicate = predicate == null ? defaultPredicate(expectedResult) : predicate;
    }

    public Test(Tester<T> tester, T expectedResult) {
        this(tester, expectedResult, null);
    }

    private BiPredicate<T, T> defaultPredicate(T expectedResult) {
        if (expectedResult == null) {
            return (T a, T b) -> a == b;
        } else {
            return (T a, T b) -> a.equals(b);
        }
    }

    public void executeTest() {actualResult = tester.test();}
    public boolean assertResult() {return predicate.test(actualResult, expectedResult);}
    public T actualResult() {return actualResult;}
}
