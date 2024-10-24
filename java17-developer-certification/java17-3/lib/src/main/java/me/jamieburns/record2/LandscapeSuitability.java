package me.jamieburns.record2;

public enum LandscapeSuitability {

    G("Garden"),
    OS("Open Space"),
    S("Street"),
    R("Rural"),
    UNK("Unknown");

    private String longName;

    private LandscapeSuitability(String longName) {
        this.longName = longName;
    }

    public String longName() {
        return longName;
    }
}
