package me.jamieburns.streams.nasdaq3;

public final class DataSupport {
    static Data toData(String data) {
        String[] record = data.split(",");
        // For now, we are assuming data is clean
        return new Data(record[0], record[1], record[2]);
    }
}
