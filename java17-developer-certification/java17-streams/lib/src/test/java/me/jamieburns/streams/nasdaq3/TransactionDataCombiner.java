package me.jamieburns.streams.nasdaq3;

import java.util.Map;
import java.util.function.BinaryOperator;

public class TransactionDataCombiner implements BinaryOperator<Map<String, Map<Double, Long>>> {
    @Override
    public Map<String, Map<Double, Long>> apply(Map<String, Map<Double, Long>> leftStockMap, Map<String, Map<Double, Long>> rightStockMap) {
        /*
        System.out.println("--- leftMap ---");
        for (var leftStock : leftStockMap.entrySet()) {
            for (var leftPrice : leftStock.getValue().entrySet()) {
                System.out.println("[" + leftStock.getKey() + ", " + leftPrice.getKey() + ", " + leftPrice.getValue() + "]");
            }
        }
        System.out.println("--- rightMap ---");
        for (var rightStock : rightStockMap.entrySet()) {
            for (var rightPrice : rightStock.getValue().entrySet()) {
                System.out.println("[" + rightStock.getKey() + ", " + rightPrice.getKey() + ", " + rightPrice.getValue() + "]");
            }
        }
        */
        for (var rightStock : rightStockMap.entrySet()) { // each stock
            if (leftStockMap.containsKey(rightStock.getKey()) == false) {
                leftStockMap.put(rightStock.getKey(), rightStock.getValue());
                continue;
            }
            // if we got to here, leftStockMap contains our stock
            Map<Double, Long> leftPriceMap = leftStockMap.get(rightStock.getKey());
            for (var rightPrice : rightStock.getValue().entrySet()) { // each price
                if (leftPriceMap.containsKey(rightPrice.getKey()) == false) {
                    leftPriceMap.put(rightPrice.getKey(), rightPrice.getValue());
                    continue;
                }
                // if we got to here, leftPriceMap contains our price
                long leftCount = leftPriceMap.get(rightPrice.getKey());
                long rightCount = rightPrice.getValue();

                leftCount += rightCount;

                // if our price count is now 0 (zero), let's remove it from the map,
                // otherwise, let's update it in our left map

                if (leftCount == 0) {
                    leftPriceMap.remove(rightPrice.getKey());
                } else {
                    leftPriceMap.put(rightPrice.getKey(), leftCount);
                }                
            }
        }

        return leftStockMap;
    } 
}
