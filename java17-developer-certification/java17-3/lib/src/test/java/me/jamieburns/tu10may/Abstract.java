package me.jamieburns.tu10may;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Abstract {
    public static void main(String[] args) {
        System.out.println(new WalkAction().doIt());
        System.out.println(new JumpAction().doIt());
    }
}

abstract class Action {
    protected LocalTime lt;

    public Action() {
        lt = LocalTime.now();
    }

    public String doIt() {
        String actionResult = action(); // our abstract method
        LocalTime lt2 = LocalTime.now();
        lt.until(lt2, ChronoUnit.MILLIS);
        StringBuilder sb = new StringBuilder()
            .append(actionResult)
            .append(" took ")
            .append(lt.until(lt2, ChronoUnit.NANOS))
            .append("ns");
        return sb.toString();
    }

    abstract String action();
}

class WalkAction extends Action {
    @Override
    String action() {
        return "walk walk walk";
    }
}

class JumpAction extends Action {
    @Override
    String action() {
        return "jump jump jump";
    }    
}
