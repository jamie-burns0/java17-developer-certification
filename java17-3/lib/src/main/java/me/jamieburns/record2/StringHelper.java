package me.jamieburns.record2;

public final class StringHelper {
    
    public static final boolean hasNoValue(String s) {
        return s == null || s.isEmpty() || s.isBlank();
    }

    public static final boolean hasValue(String s) {
        return !StringHelper.hasNoValue(s);
    }
}
