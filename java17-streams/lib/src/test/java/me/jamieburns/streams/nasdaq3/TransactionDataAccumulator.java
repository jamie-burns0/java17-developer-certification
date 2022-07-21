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
        
        // What should we do if td.price.count != 0? We could add any count to
        // the price in our map, or we could flag this is unexpected and bail
        // out here. For now, let's assume everything is well behaved and count
        // will be 0 (zero)

        priceMap.compute(td.price(), (k, v) -> {
            return switch(td.action()) {
                case TRADE -> ++v;
                case CANCEL -> --v;
            };
        });

        // if our price count is now 0 (zero), let's remove it from the map

        long count = (Long)priceMap.get(td.price()).longValue();

        if (count == 0) {
            priceMap.remove(td.price());
        }
    }
    
}
