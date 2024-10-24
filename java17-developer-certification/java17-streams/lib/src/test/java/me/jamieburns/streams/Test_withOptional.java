package me.jamieburns.streams;

import java.util.Optional;
import java.util.function.BiPredicate;

public class Test_withOptional<T> {
    private Tester<T> tester;
    private T expectedResult;
    private Optional<BiPredicate<T, T>> optional;
    
    private Test_withOptional(Tester<T> tester, T expectedResult, Optional<BiPredicate<T, T>> optional) {
        this.tester = tester;
        this.expectedResult = expectedResult;
        this.optional = optional;
    }

    public Test_withOptional(Tester<T> tester, T expectedResult, BiPredicate<T, T> predicate) {
        this(tester, expectedResult, Optional.ofNullable(predicate));
    }

    public Test_withOptional(Tester<T> tester, T expectedResult) {
        this(tester, expectedResult, Optional.empty());
    }

    public Tester<T> tester() {return tester;}
    public T expectedResult() {return expectedResult;}
    public Optional<BiPredicate<T, T>> optional() {return optional;}
}
