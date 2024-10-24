package me.jamieburns.streams;

import static org.testng.Assert.assertTrue;

public final class TestSupport {
    public final static void tests(Test<?>... testArray) {
        int testCount = 0;
        for (Test<?> test : testArray) {
            test.executeTest();
            printActualResult(++testCount, test.actualResult());            
            assertTrue(test.assertResult(), test.toString());
        }
    }

    private static void printActualResult(int testCount, Object actualResult) {
        if (actualResult != null) {
            withActualResult(testCount, actualResult);
        } else {
            withNullActualResult(testCount);
        }
    }

    private static void withActualResult(int testCount, Object actualResult) {
        System.out.println(testCount + ": " + actualResult + ", " + actualResult.getClass().getSimpleName());
    }

    private static void withNullActualResult(int testCount) {
        System.out.println(testCount + ": null, null");
    }
}
