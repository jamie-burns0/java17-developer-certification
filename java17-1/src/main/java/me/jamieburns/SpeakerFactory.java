package me.jamieburns;

public class SpeakerFactory {

    public static Speaker createSpeakerWithNothingToSay() {
        return new Speaker() {
            public String say() {
                return "I'm a speaker with nothing to say";
            }
        };
    }

    public static Speaker createSpeakerWithSomethingToSay() {
        return new Speaker() {
            public String say() {
                return "I'm a speaker with something to say";
            }
        };
    }
}
