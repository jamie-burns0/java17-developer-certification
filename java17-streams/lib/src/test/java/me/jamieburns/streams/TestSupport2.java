package me.jamieburns.streams;

import java.util.function.BiPredicate;

import static org.testng.Assert.assertTrue;

public final class TestSupport2 {
    /**
     * Compiler warning for raw types and type safety have been disabled.
     * 
     * The exercise in Test, Tester and Optional Test was to enforce the
     * test result type. For Test, we want the result of our test and
     * the expected result of our test to be the same type.
     * 
     * Generally, we say the test result and expected result are the same
     * if a.equals(b) is true. However, for a result type like an 
     * Exception object, this is only true if a == b. So Test was updated
     * to allow an option test specific Predicate.
     * 
     * This was all good, but falls apart in TestSupport. The only way
     * around was to allow raw types and ignore type safety in our tests
     * method.
     * 
     * So we have a trade-off of ensuring our test result and expected results
     * are of the same type at compile-time, vs compromising on type safety at
     * runtime. For the purposes of this exercise, we are prepared to make that
     * compromise.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SafeVarargs
    public final static void tests(Test2<?>... testArray) {
        int testCount = 0;
        for (Test2<?> test : testArray) {
            Object result = test.tester().test();
            Object expectedResult = test.expectedResult();
            System.out.println(++testCount + ": " + result + ", " + result.getClass().getSimpleName());
            boolean assertTest = false;
            BiPredicate<Object, Object> defaultBiPredicate = (Object a, Object b) -> a.equals(b);
            if(test.optional().isPresent()) {
                // use the BiPredicate provided by test
                BiPredicate predicate = test.optional().get();    
                assertTest = predicate.test(result, expectedResult);
            } else {
                // use our default BiPredicate
                assertTest = defaultBiPredicate.test(result, expectedResult);
            }
            assertTrue(assertTest, test.toString());
        }
    }
}
