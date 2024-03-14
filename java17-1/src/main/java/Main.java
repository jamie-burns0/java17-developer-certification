import me.jamieburns.NothingToSaySpeaker;
import me.jamieburns.SomethingToSaySpeaker;
import me.jamieburns.Speaker;
import me.jamieburns.SpeakerFactory;
import me.jamieburns.UnknownSpeaker;

public class Main {

    public static void main(String[] args) {

        System.out.println((new Speaker() {}).say());
        System.out.println(SpeakerFactory.createSpeakerWithSomethingToSay().say());
        System.out.println(SpeakerFactory.createSpeakerWithNothingToSay().say());
    
        new Main().say(new Speaker() {});
        new Main().say(new SomethingToSaySpeaker());
        new Main().say(new NothingToSaySpeaker());

        new Main().say(new Speaker() {
            public String say() {
                return "i can say anything";
            }
        });

        Main.say2(new Speaker() {
            public String say() {
                return "what can i say";
            }
        });

        Speaker s = new NothingToSaySpeaker();
        s.increaseVolume();

        Main.sayWithDebug(s);
        Main.sayWithDebug(new UnknownSpeaker());

        Main.sayWithDefaultPhrase();
        Main.sayWithRandomPhrase();
    }

    private static void sayWithDefaultPhrase() {
        System.out.println(Speaker.DEFAULT_PHRASE);
    }

    private static void sayWithRandomPhrase() {
        System.out.println(Speaker.getRandomPhrase());
    }

    private static void sayWithDebug(Speaker s) {
        Debug d = debug(s);
        System.out.println("c: " + d.c + ", m: " + d.m + ", " + s.say());
    }

    private static void say2(Speaker s) {
        assert(s != null);
        sayWithDebug(s);
    }

    private void say(Speaker s) {
        Main.sayWithDebug(s);
    }

    private static Debug debug(Speaker s) {
        Debug d = new Debug();
        d.c = s.getClass().getName();
        d.m = new Exception().getStackTrace()[2].getMethodName();
        return d;
    }
}
