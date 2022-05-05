package me.jamieburns;

import static org.testng.Assert.assertTrue;

public final class TestSupport {
    public final static void tests(Test... testArray) {
        int testCount = 0;
        for (Test test : testArray) {
            Object result = test.tester().test();
            System.out.println(++testCount + ": " + result + ", " + result.getClass().getSimpleName());
            assertTrue(result.equals(test.expectedResult()), test.toString());
        }
    }
}
