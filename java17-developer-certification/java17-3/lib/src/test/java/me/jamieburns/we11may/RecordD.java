package me.jamieburns.we11may;

public record RecordD(String d) {

    private static final int REVERSE_LIMIT = 8;
    // private int anInstanceVariable = 3  // is not allowed in a Record

    public RecordD(String a, String b) {
        this(transform(a, b)); // this(...) must be the first statement in this constructor - see constructor rules
        // cannot do anymore transformation after this point
    }
    
    private static String transform(String a, String b) {
        var c = a.toUpperCase() + ", " + b.toLowerCase();
        char[] d = c.toCharArray();
        char[] e = new char[d.length];
        int i = d.length;
        int n = 0;
        boolean reversing = true;
        for (char f : d) {
            if (reversing) {
                e[--i] = f;
                n++;
            }

            if (!reversing) {
                e[i++] = f;
            }

            if(reversing && n == REVERSE_LIMIT) {
                reversing = false;
                i = 0;
            }            
        }
        return new String(e);
    }
}
