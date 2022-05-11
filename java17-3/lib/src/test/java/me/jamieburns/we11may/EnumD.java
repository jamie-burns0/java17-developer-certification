package me.jamieburns.we11may;

public enum EnumD implements Reverser {

    DREADNOUGHT, DROUGHT, DESSERT;

    @Override
    public String reverse() {
        char[] a = toString().toCharArray();
        char[] b = new char[a.length];
        int i = a.length;
        for (char c : a) {
            b[--i] = c;
        }
        return new String(b);
    }
    
}

interface Reverser {
    String reverse();
}
