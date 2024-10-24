package me.jamieburns.streams.nasdaq3;

public final class ActionSupport {
    static Action fromDataAction(String dataAction) {
        return switch(dataAction) {
            case "TRADE" -> Action.TRADE;
            case "CANCEL" -> Action.CANCEL;
            default -> throw new RuntimeException("Cannot convert action [" + dataAction + "] to an Action");
        };
    }
}
