package me.jamieburns.we11may;

public enum EnumB {
    BRAN {
        public String use() {return "I added " + BRAN + " to my breakfast.";}
    }, 
    BOT {
        public String use() {return "The " + BOT + " crashed into the wall";}
    },
    BUBBLE {
        public String use() {return "That burst my " + BUBBLE;}
    };

    public abstract String use();    
}
