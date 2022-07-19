package me.jamieburns.streams.nasdaq3;

import java.util.Map;
import java.util.function.BiConsumer;

public class CombiningBiConsumer implements BiConsumer<Map<String, Map<Double, Long>>, Map<String, Map<Double, Long>>> {
    @Override
    public void accept(Map<String, Map<Double, Long>> stockMap1, Map<String, Map<Double, Long>> stockMap2) {
        System.out.println("--- stockMap1 ---");
        for (var es1 : stockMap1.entrySet()) {
            for (var es2 : es1.getValue().entrySet()) {
                System.out.println("[" + es1.getKey() + ", " + es2.getKey() + ", " + es2.getValue() + "]");
            }
        }
        System.out.println("--- stockMap2 ---");
        for (var es1 : stockMap2.entrySet()) {
            for (var es2 : es1.getValue().entrySet()) {
                System.out.println("[" + es1.getKey() + ", " + es2.getKey() + ", " + es2.getValue() + "]");
            }
        }
        
    }    
}
