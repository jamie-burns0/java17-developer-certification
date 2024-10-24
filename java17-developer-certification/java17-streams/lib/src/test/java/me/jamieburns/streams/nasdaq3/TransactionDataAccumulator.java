package me.jamieburns.streams.nasdaq3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class TransactionDataAccumulator implements BiConsumer<Map<String, Map<Double, Long>>, TransactionData> {
    @Override
    public void accept(Map<String, Map<Double, Long>> stockMap, final TransactionData td) {

        // if our stock code is not in the map, add it
        Map<Double, Long> priceMap = stockMap.computeIfAbsent(td.stockCode(), k -> new HashMap<>());

        // if our price is not in the map, add it
        priceMap.computeIfAbsent(td.price(), k -> Long.valueOf(0));

        // update our price count

        priceMap.compute(td.price(), (price, count) -> {
            return switch(td.action()) {
                case TRADE -> ++count;
                case CANCEL -> --count;
            };
        });

        // if our price count is now 0 (zero), let's remove it from the map

        // NOTE: Our accumulator should usable in a parallel context. For example,
        // say, for a price, we have a TRADE and then later a CANCEL. In a parallel
        // context, it's possible, and normal, that one accumlator handles the
        // TRADE while another accumulator handles the CANCEL. So while, we are
        // removing a price with a 0 (zero) count, we should expect that we will
        // have prices with -ve counts.

        long count = (Long)priceMap.get(td.price()).longValue();

        if (count == 0) {
            priceMap.remove(td.price());
        }
    }
    
}
