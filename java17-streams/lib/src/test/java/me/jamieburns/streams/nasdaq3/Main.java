package me.jamieburns.streams.nasdaq3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        var accumulator = new TransactionDataAccumulator();
        var combiner = new TransactionDataCombiner();

        Map<String, Map<Double, Long>> transactionSummary = new DataSource().data.stream()
            //.parallel() 
            // If we allow parallel, we need to update our accumulator to allow a -ve count on a price
            // For a price with a TRADE and a CANCEL, one accumulator could get the TRADE while
            // another accumulator could get the CANCEL.
            // Our combiner would then need to do the math when it is combining two price counts.
            .map(DataSupport::toData)
            .map(TransactionDataSupport::toTransactionData)
            .collect(
                //HashMap::new,
                () -> new HashMap<String, Map<Double, Long>>(), // Supplier<Map<String, Map<Double, Long>>
                accumulator, // BiConsumer<Map<String, Map<Double, Long>>, TransactionData>
                combiner // BiConsumer<Map<String, Map<Double, Long>>, Map<String, Map<Double, Long>>>
            );

        System.out.println("--- transactionSummary ---");

        for (var es1 : transactionSummary.entrySet()) {
            var stockCode = es1.getKey();
            var minimumPrice = Double.MAX_VALUE;
            var maximumPrice = 0.0;
            for (var es2 : es1.getValue().entrySet()) {
                System.out.println("[" + es1.getKey() + ", " + es2.getKey() + ", " + es2.getValue() + "]");
                var price = es2.getKey();
                if (price < minimumPrice) {
                    minimumPrice = price;
                }
                if (price > maximumPrice) {
                    maximumPrice = price;
                }
            }
            System.out.println(stockCode + ", " + minimumPrice + ", " + maximumPrice);
        }

    }
}
