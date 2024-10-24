package me.jamieburns.we11may;

public enum EnumC {
    CATCH("ball"),
    COPPER("wire"), 
    CACOPHNY("orchestra");

    private final String association;

    private EnumC(String association) {
        this.association = association;
    }

    public final String association() {return association;}
}
