package me.jamieburns.th05may;

import me.jamieburns.Test;
import static me.jamieburns.TestSupport.tests;

import java.util.Arrays;

public class CoreApisTest {

    public static void main(String[] args) {
        new CoreApisTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            // String methods to know for the exam
            // 
            // +, +=
            // intern(): String
            // length(): int
            // charAt(int): char   zero indexed.
            // indexOf(int=>0x0000-0xFFFF)|String): int  zero indexed. -1 if not found
            // substring(int|int, int): String
            // toLowerCase(): String
            // toUpperCase(): String
            // equals(Object): boolean  if arg is not a String, returns false
            // equalsIgnoreCase(String): boolean
            // startsWith(String): boolean
            // endsWith(String): boolean
            // contains(CharSequence): boolean
            // replace(char, char|CharSequence, CharSequence): String
            // strip(): String  remove blank space from the beginning and end of the String
            // trim(): String   remove blank space from the beginning and end of the String
            // stripLeading(): String
            // stripTrailing(): String
            // indent(int): String - +ve add, -ve subtract, zero no change, normalises String, adds linebreak at end of String if there isn't one there already
            // stripIndent(): String - remove all incidental whitespace
            // translateEscapes(): String
            // isEmpty(): boolean - ""
            // isBlank(): boolean - "  "
            // static format(String, Object...): String - %s any type, %d integer values, %f floating point values, %n line break
            // formatted(Object...): String - "%s:%n score: %f out of %d".formatted("Jamie", 95.5, 100)

            new Test(() -> {
                // beware how + is interpreted when we mix numbers and string
                // (1) BOTH operands numeric = addition
                // (2) EITHER operand String = concatenation
                // (3) work L-R
                var a = 1 + 2 + "3"; // 3 + "3" = "33"
                var b = "1" + 2 + 3; // "12" + 3 = "123"
                var c = "1" + null;  // "1null"
                var d = "1"; d += "2"; d += 3; // "123". This wouldn't work if var d = 1 because var would be an int
                                               // and our first += would be trying to store a String in an int
                return 0;}, 0),
            new Test(() -> {
                // strings a and b are computed at COMPILE TIME and are the same 
                // --> interned (added to String's string pool) 
                // --> a and b have the same reference value
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = """
                    In the beginning was the Word
                    and the Word was with God.""";
                return a == b;}, true),
            new Test(() -> {
                // strings a and b are computed at RUNTIME - because of concat() - and while the same for equals(),
                // DO NOT reference the same string for the purposes of ==
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = "In the beginning was the Word\n".concat("and the Word was with God.");
                return a == b;}, false),
            new Test(() -> {
                // strings a and b are computed at RUNTIME - because of concat() - but, because of intern(),
                // DO reference the same string for the purposes of ==
                String a = """
                    In the beginning was the Word
                    and the Word was with God.""";
                String b = "In the beginning was the Word\n".concat("and the Word was with God.").intern();
                return a == b;}, true),
            new Test(() -> {
                // lifted from the Java Language Specification, Example 3.10.5-1. String Literals
                String hello = "Hello", lo = "lo";
                var a = (hello == "Hello"); // true
                var b = (hello == ("Hel"+"lo")); //true
                var c = (hello == ("Hel"+lo)); // FALSE: lo is a variable not a string literal
                var d = (hello == ("Hel"+lo).intern()); // true
                return true;}, true),                
            new Test(() -> {
                byte[] a = {0x7f, 0100, 0b0011}; // 0x7f (127) is the largest positive integer that can fit into a byte.
                                                 // A larger value needs to be case to byte: (byte)0x80
                int b = a.length;
                return a[2] == b;}, true),
            new Test(() -> {
                char[] a = new char[3];
                String b = "God";
                String c = b.toString(); // b returns the reference to "God"
                var d = (b == c);
                return d;}, true),
            new Test(() -> {
                String a = "God";
                char[] b = {'G', 'o', 'd'};
                String c = new StringBuilder().append(a).toString();
                var d = (a == c);
                return d;}, false),
            new Test(() -> {
                String a = "God";
                char[] b = {'G', 'o', 'd'};
                String c = new String(b);
                var d = (a == c);
                return d;}, false),
            new Test(() -> {
                String[] a = {"in", "the", "beginning", "was", "God"};
                String b[] = a;
                var d = (a == b) && (a.equals(b)); // an array is an object, so we can call Object.equals(...)
                return d;}, true),
            new Test(() -> {
                String[] a = {"in", "the", "beginning", "was", "God"};
                String b[] = {"in", "the", "beginning", "was", "God"};
                var d = (a == b) && (a.equals(b)); // a and b are different objects
                return d;}, false),
            new Test(() -> {
                String[] a = {"in", null, "beginning", "was", "God"};
                String b[] = (String[])a;
                var d = (a.equals(b));
                var sb = new StringBuilder();
                for (String s : b) {
                    if (s == null) {
                        continue;
                    }
                    sb.append(s);
                }
                return sb.toString();}, "inbeginningwasGod"),
            new Test(() -> {
                String []a = {"in", "the", "beginning", null, "God"};
                var sb = new StringBuffer();
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == null) {
                        break;
                    }
                    sb.append(a[i]);
                }
                return sb.toString();}, "inthebeginning"),
            new Test(() -> {
                String[] a = {"Psalm", "23", "The", "Lord", "is", "my", "shepherd"};
                Arrays.sort(a);
                var sb = new StringBuilder();
                for (String s : a) {
                    sb.append(s);
                }
                return sb.toString();}, "23LordPsalmTheismyshepherd"), // ASCII: numberals, uppercase, lowercase            
            new Test(() -> {
                var a = new String(new char[] {'G', '\u006f', '\u0064'});
                return a;}, "God"),
            new Test(() -> {
                String[] a = {"Psalm", "23", "The", "Lord", "is", "my", "shepherd"};
                var i = Arrays.binarySearch(a, "Lord"); // Our array is not in sort order, so the result here is unpredictable
                Arrays.sort(a);
                var i2 = Arrays.binarySearch(a, "Lord"); // 1, ie a[1]
                var i3 = Arrays.binarySearch(a, "i"); // -5: not found but if it was in the array it would live immediately before "is", a[4]
                return Integer.toString(i2) + Integer.toString(i3);}, "1-5"),
            new Test(() -> {
                String[] a = {"Psalm", "23", "The", "Lord", "is", "my", "shepherd", "I"};
                String[] b = {"John", "1", "In", "the", "beginning", "was", "the", "Word"};
                var i = Arrays.compare(a, b); // a +ve int as "Psalm" is larger than "John" ????
                return i > 0;}, true), 
            new Test(() -> {
                Object[] a = {"a", "b", 21, "d", "d"};
                Object[] b = {"a", "b", 21, "d", "e"};
                var i = Arrays.mismatch(a, b); // mismatch() occurs on the a[4], b[4]
                                               // mismatch() does NOT sort the array under the hood. It uses the order provided,
                                               // comparing a[0] with b[0], a[1] with b[1], and so on.
                return i;}, 4),
            //
            // For [].equals(), Arrays.compare() and Arrays.mismatch()
            //
            // Method     | array contains | arrays are
            //            | same data      | different
            // -----------+----------------+------------------
            // equals()   | true           | false
            // compare()  | 0              | +ve or -ve number
            // mismatch() | -1             | zero or +ve index
            //
            new Test(() -> {
                int[] a = {1, 2, 3, 4, 5, 6};
                int[] b = {1, 2, 3, 4, 5, 6};
                return b.equals(a);}, false), // because an array is an object, we can call Object.equals(), 
                                              // which returns true if both a and b refer to the same object, a == b - see next test
        new Test(() -> {
            int[] a = {1, 2, 3, 4, 5, 6};
            int[] b = a;
            return b.equals(a);}, true),
/*            new Test(() -> {
                int[] a = {1, 2, 3, 4, 5, 6};        // An array object holding ints
                int[][] b = {{1,2}, {3}, {4, 5, 6}}; // An array object holding array objects holding ints. Each array in our array can be a different size.
                int[][] c = new int[3][];
                b[0] = new int[] {1,2};
                b[1] = new int[] {3};
                b[2] = new int[] {4, 5, 6};
                return b.equals(c);}, true), // WHY IS THIS FALSE??
*/            new Test(() -> {return 0;}, 0),
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
        String hello = "Hello", lo = "lo";
        boolean a = (hello == "Hello"); // true
        System.out.println(hello == ("Hel"+"lo"));
        System.out.println(hello == ("Hel"+lo));
        System.out.println(hello == ("Hel"+lo).intern());
        return 1;
    }
}
