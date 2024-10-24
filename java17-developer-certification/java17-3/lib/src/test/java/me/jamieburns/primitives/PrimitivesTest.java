package me.jamieburns.primitives;

//import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class PrimitivesTest {

    private interface Assigner<T> {
        public void assign(T t);
    }

    private <T> void tests(Assigner<T> assigner, T... args) {
        System.out.println(args[0].getClass().getName());
        for (T arg : args) {
            assigner.assign(arg);
            System.out.println(arg);
        }
    }

    @Test
    public void testAssignments() {

        tests(
            (b) -> {byte b2 = b.byteValue();},
            Byte.MAX_VALUE,
            Byte.valueOf("25"),
            Byte.valueOf((byte)0x10)
        );

        tests(
            (s) -> {short s2 = s.shortValue();},
            (short)123,
            (short)255,
            (short)0x18181818,
            Short.valueOf("10000"),
            Short.MAX_VALUE
        );

        tests(
            (i) -> {int i2 = i.intValue();},
            Integer.MAX_VALUE,
            Integer.MIN_VALUE,
            (int)Long.MAX_VALUE,
            (int)Long.MIN_VALUE,
            011,
            0xfffffc,
            0b100100100100
        );

        tests(
            (l) -> {long l2 = l.longValue();},
            Long.valueOf(Long.MAX_VALUE),
            9_223_372_036_854_775_806L,
            12L,
            3_456_345_345L
        );

        tests(
            (b) -> {boolean b2 = b.booleanValue();},
            Boolean.valueOf(true),
            true,
            Boolean.valueOf("1"),
            Boolean.valueOf("0"),
            Boolean.valueOf("true"),
            Boolean.valueOf("TRUE")
        );

        tests(
            (f) -> {float f2 = f.floatValue();},
            Float.valueOf(123.456F),
            456F,
            Float.valueOf(Float.MAX_VALUE)
        );

        tests(
            (d) -> {double d2 = d.doubleValue();},
            Double.valueOf(123.456),
            456.789,
            Double.valueOf(Double.MAX_VALUE)
        );

        tests(
            (c) -> {char c2 = c.charValue();},
            Character.valueOf('A'),
            Character.valueOf((char)77),
            Character.valueOf('\u0077'),
            'B',
            (char)Short.valueOf("78").shortValue()
        );
    }

    @Test
    public void testTextBlock() {

        String b = """
            In the beginning was the Word,
              and the Word was with God,
              and the Word was God.
            
            He was with God in the beginning.
            
            Through Him all things were made,
              and without Him nothing was made \
            that has been made.""";

        System.out.println(b);
    }

    public static void main(String... args) {
        System.out.println("Running main()");
        int $someStange_identifierName2 = 1;
        
        var another$trange1dentifierName = "fred";
        another$trange1dentifierName.charAt(1);

        var _whyDoWeA11owThi$Name = Integer.valueOf("123");
        _whyDoWeA11owThi$Name.byteValue();
    }
}
