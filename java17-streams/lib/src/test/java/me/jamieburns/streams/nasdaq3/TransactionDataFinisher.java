package me.jamieburns.streams.nasdaq3;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

public class TransactionDataFinisher implements Function<Map<String, Map<Double, Long>>, SortedSet<MinMaxPrice>>{

    @Override
    public SortedSet<MinMaxPrice> apply(Map<String, Map<Double, Long>> transactionSummary) {
        var result = new TreeSet<MinMaxPrice>();
        for (var es1 : transactionSummary.entrySet()) {
            var stockCode = es1.getKey();
            var minimumPrice = Double.MAX_VALUE;
            var maximumPrice = 0.0;
            for (var es2 : es1.getValue().entrySet()) {
                var price = es2.getKey();
                if (price < minimumPrice) {
                    minimumPrice = price;
                }
                if (price > maximumPrice) {
                    maximumPrice = price;
                }
            }
            result.add(new MinMaxPrice(stockCode, minimumPrice, maximumPrice));
        }
        return result;
    }
    
}
