package me.jamieburns.streams.nasdaq3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        var accumulator = new AccumulatingBiConsumer();
        var combiner = new CombiningBiConsumer();

        Map<String, Map<Double, Long>> transactionSummary = new DataSource().data.stream()
            //.parallel()
            .map(DataSupport::toData)
            .map(TransactionDataSupport::toTransactionData)
            .collect(// Supplier<S>, BiConsumer<S,V>, BiConsumer<S,S>
                HashMap::new,
                //() -> new HashMap<String, Map<Double, Long>>(),
                accumulator,
                combiner
            );

        System.out.println("--- transactionSummary ---");

        for (var es1 : transactionSummary.entrySet()) {
            for (var es2 : es1.getValue().entrySet()) {
                System.out.println("[" + es1.getKey() + ", " + es2.getKey() + ", " + es2.getValue() + "]");
            }
        }
    }
}
