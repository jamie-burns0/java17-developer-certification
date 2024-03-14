package me.jamieburns;

public class DefaultSpeaker implements Speaker 
{
    private String phrase;

    public DefaultSpeaker() {
        phrase = Speaker.DEFAULT_PHRASE;
    }

    public DefaultSpeaker(Speaker s) {
        this.phrase = s.say();
    }

    public String say() {
        return phrase;
    }
    @Override
    public void increaseVolume() {
        phrase = phrase.toUpperCase();
    }
    
}
