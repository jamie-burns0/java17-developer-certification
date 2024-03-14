package me.jamieburns;

public class LearnSwitch {
    
    public String returnDessertComment(Dessert dessert) {
        return dessert.comment();
    }

    public String dessertDemand(Dessert dessert) {
        
        return switch(dessert) {
            case CHOCOLATE, CAKE -> "high";
            case PIE, ICECREAM -> "medium";
            case CHEESE, FRUIT -> "low";
        };
    }
}
