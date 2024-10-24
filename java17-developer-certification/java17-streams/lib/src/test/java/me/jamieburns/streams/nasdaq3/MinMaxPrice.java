package me.jamieburns.streams.nasdaq3;

public record MinMaxPrice(String stockCode, double minimumPrice, double maximumPrice) implements Comparable<MinMaxPrice> {

    @Override
    public int compareTo(MinMaxPrice that) {
        return this.stockCode.compareTo(that.stockCode);
    }
}
