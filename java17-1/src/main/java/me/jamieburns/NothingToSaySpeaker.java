package me.jamieburns;

public class NothingToSaySpeaker extends DefaultSpeaker {

    public NothingToSaySpeaker() {
        super((new Speaker() {
            public String say() {
                return "nothing to say";
            }
        }));
    }
}
