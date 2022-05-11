package me.jamieburns.we11may;

public record RecordB(String b) {
    public RecordB { // compact constructor
        if (b == null) {
            b = new String();
        } else {
            b = b.toUpperCase();
        }
    }    
}
