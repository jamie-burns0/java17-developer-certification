package me.jamieburns;

public interface Speaker {

    String DEFAULT_PHRASE = "This is the default phrase";
    String[] RANDOM_PHRASE_ARRAY = {
        "what a wonderful world", 
        "all the rivers run",
        "in the beginning",
        "you light up my life"
    };

    default String say() {
        return DEFAULT_PHRASE;
    }

    default void increaseVolume() {
        // do nothing
    };

    static String getRandomPhrase() {
        return RANDOM_PHRASE_ARRAY[Math.toIntExact(Math.round(Math.random()*1000)) % 4];
    }
}
