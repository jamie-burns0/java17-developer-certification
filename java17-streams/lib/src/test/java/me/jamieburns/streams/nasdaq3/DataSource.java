package me.jamieburns.streams.nasdaq3;

import java.util.List;

public class DataSource {
    List<String> data = List.of(
        "TRADE, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 102.0",
        "CANCEL, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 101.4",
        "TRADE, BHP, 101.5", "CANCEL, AMP, 64.5", "TRADE, BHP, 102.0",
        "TRADE, BHP, 101.5", "TRADE, AMP, 64.5", "TRADE, BHP, 102.5",
        "TRADE, AMP, 64.5", "TRADE, BHP, 101.4", "TRADE, BHP, 101.5",
        "CANCEL, AMP, 64.5", "TRADE, BHP, 102.0", "TRADE, BHP, 101.5"
    );
}
