package me.jamieburns;

public enum Dessert {
    CHOCOLATE("yum"), 
    PIE("the best"), 
    ICECREAM("love icecream"), 
    FRUIT("nice"), 
    CHEESE("good"), 
    CAKE("ahhhh");

    private final String comment;

    Dessert(String comment) {
        this.comment = comment;
    }

    public String comment() {
        return comment;
    }
}
