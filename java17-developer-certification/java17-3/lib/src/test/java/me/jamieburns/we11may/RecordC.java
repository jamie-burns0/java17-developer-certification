package me.jamieburns.we11may;

public record RecordC(String c) implements Reverser {
    @Override
    public String reverse() {
        char[] a = c.toCharArray();
        char[] b = new char[a.length];
        int i = a.length;
        for (char c : a) {
            b[--i] = c;
        }
        return new String(b);
    }    
}
