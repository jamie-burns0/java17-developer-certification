package me.jamieburns.th05may;

import static org.testng.Assert.assertTrue;

import me.jamieburns.Test;

public class RevisionTest {

    public static void main(String[] args) {
        new RevisionTest().testsToRun();
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
                String a = """
                    In the beginning was the Word
                        and the Word was with God
                        and the Word was God.""";
                long _lines = a.chars().filter(c -> c == '\n').count() + 1; // +1 because the last line doesn't have \n
                return _lines;}, 3L),
            new Test(() -> {
                String a = """
                    In the beginning was the Word
                        and the Word was with God
                        and the Word was God.""";
                long $pace$ = a.chars().filter(c -> c == ' ').count();
                return $pace$;}, 22L),
            new Test(() -> {
                String a = """
                    In the beginning was the Word\
                        and the Word was with God\
                        and the Word was God.""";
                long l_ine$ = a.chars().filter(c -> c == '\n').count() + 1;
                return l_ine$;}, 1L),
            new Test(() -> {
                String a = """
                    In the beginning was the Word\
                        and the Word was with God\
                        and the Word was God."""; // Our \ at the end of the line prevents a linebreak. Our line is
                                                  // '...the Word(4 spaces)and the Word...with God(4 spaces)and the Word...'
                long spac3s = a.chars().filter(c -> c == ' ').count();
                return spac3s;}, 22L),
            new Test(() -> {
                String a = """
                    In the beginning was the Word      \
                        and the Word was with God\
                        and the Word was God."""; // Our \ at the end of the line prevents a linebreak. The spaces after Word are preserved.
                                                  // Our line is
                                                  // '...the Word(6 spaces)(4 spaces)and the Word...with God(4 spaces)and the Word...'
                long spaces = a.chars().filter(c -> c == ' ').count();
                return spaces;}, 28L),                 
            new Test(() -> {
                String a = """
                    In the beginning was the Word     \s
                        and the Word was with God
                        and the Word was God."""; // Our \s preserves the whitespace between Word and \s and is itself a space. 
                                                  // So we have 6 spaces after Word on the first line. Our text is
                                                  // '...the Word(6 spaces)(linebreak)(4 spaces)and the Word...'
                /*
                int i = 0;
                for (char c : a.toCharArray()) {
                    var u = String.format("\\u%04x", (int) c);
                    System.out.println("" + i++ + ", " + c + ", " + u);
                }
                System.out.println(a.length());
                */
                long spaces = a.chars().filter(c -> c == ' ').count();
                return spaces;}, 28L),
            new Test(() -> {
                String a = """
                    And God said, \"Let there be light,\"\
                    and there was light."""; // 'And God said...there was light'
                long spaces = a.chars().filter(c -> c == ' ').count();
                return spaces;}, 9L),
            new Test(() -> {
                String a = """
                    In the beginning was the Word \
                    and the Word was with God \
                    and the Word was God.""";
                var ia = a.chars().toArray();
                char[] c = new char[a.length()];
                int i = 0;
                for (int i2 : ia) {
                    c[i++] = (char) (i2 + 1);
                }
                System.out.println(new StringBuilder().append(c).toString());
                c = new char[a.length()];
                i = 0;
                for (int i2 : ia) {
                    c[i++] = (char) (i2 % 32 + 32);
                }
                System.out.println(new StringBuilder().append(c).toString());
                return 0;}, 0),
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
                var s = a.substring(25, 29); // 25 is the W of Word. 29 is the character after 'Word'
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
