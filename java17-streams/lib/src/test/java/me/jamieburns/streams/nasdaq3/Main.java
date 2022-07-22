package me.jamieburns.streams.nasdaq3;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {

        var accumulator = new TransactionDataAccumulator();
        var combiner = new TransactionDataCombiner();
        var finisher = new TransactionDataFinisher();

        var collector = Collector.of(
            () -> new HashMap<String, Map<Double, Long>>(),
            accumulator, 
            combiner, 
            finisher, 
            Collector.Characteristics.UNORDERED,
            Collector.Characteristics.CONCURRENT);        

        SortedSet<MinMaxPrice> minMaxPriceSet = new DataSource().data.stream()
            .parallel() 
            .map(DataSupport::toData)
            .map(TransactionDataSupport::toTransactionData)
            .collect(collector);

        System.out.println("--- minMaxPriceSet ---");

        for (var minMaxPrice : minMaxPriceSet) {
            System.out.println(minMaxPrice.stockCode() + ", " + minMaxPrice.minimumPrice() + ", " + minMaxPrice.maximumPrice());
        }
    }
}
