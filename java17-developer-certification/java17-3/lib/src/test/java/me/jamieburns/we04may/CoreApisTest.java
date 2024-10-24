package me.jamieburns.we04may;

import static org.testng.Assert.assertTrue;

public class CoreApisTest {

    private record Test(Tester tester, Object expectedResult) {}

    private interface Tester {
        public Object test();
    }

    public static void main(String[] args) {
        
    }

    private void tests(Test... testArray) {
        int testCount = 0;
        for (Test test : testArray) {
            Object result = test.tester().test();
            System.out.println(++testCount + ": " + result + ", " + result.getClass().getSimpleName());
            assertTrue(result.equals(test.expectedResult()), test.toString());
        }
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                return 1 + 2 + "a";}, "3a"),
            new Test(() -> {
                return "" + 1 + 2 + "a";}, "12a"),
            new Test(() -> {
                return "a" + 1 + 2;}, "a12"),
            new Test(() -> {
                return "a" + null;}, "anull"),
            new Test(() -> {
                return null + "a";}, "nulla"),
            new Test(() -> {
                int a = 3;
                String b = "4";
                return 1 + 2 + a + b;}, "64"),
            new Test(() -> {
                String _a = "1";
                return _a += 2;}, "12"),                              
            new Test(() -> {
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                var i = a.indexOf("God"); // 0-based index. Returns 52 even though we would say G in God is the 53 character in the block of text. 
                var c = a.charAt(i); // 0-based index, char[52] is G
                return c;}, 'G'),
            new Test(() -> {
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                var s = a.substring(25, 29); 
                // 25 is the W of Word. 29 is the character after 'Word'
                // study guide tip - see pg 160. Think of the index as right before the
                // character they point to. In our case substring(25, 29) returns "Word"
                //
                //  t h e   W o r d
                // ^ ^ ^ ^ ^ ^ ^ ^ ^
                //         25------29
                //
                // substring(25, 25) would return an empty string "" 
                return s;}, "Word"),
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
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0)
        );
    }

    private int a() {
        int a = 0;
        int d = 5;
        int e = 0;
        for (int b = 0, c = 99; (e = (c - b) % a) == 5; b++, c--) {
            // do nothing
        }
        System.out.println("e="+ e);
        return e;
    }
}
