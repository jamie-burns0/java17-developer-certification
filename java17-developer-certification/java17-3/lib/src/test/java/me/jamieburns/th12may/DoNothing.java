package me.jamieburns.th12may;

public class DoNothing {
    static void withNothing() {System.out.println("doing nothing with nothing");}
    static void withOneThing(Object o) {System.out.println("doing nothing with " + o);}
    static void withTwoThings(Object o1, Object o2) {System.out.println("doing nothing with " + o1 + " and " + o2);}
    static void withManyThings(Object... oa) {
        StringBuilder sb = new StringBuilder().append("doing nothing with");
        for (Object o : oa) {
            sb.append(" " + o);
        }
        System.out.println(sb.toString());
    }
}
