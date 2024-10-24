package me.jamieburns.streams.nasdaq2;

import static me.jamieburns.streams.TestSupport.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import me.jamieburns.streams.Test;

public class NasdaqTest {
    
    public static void main(String[] args) {
            new NasdaqTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test<>(() -> {
                
                record Data(String stockCode, double price) {}

                class HighLow {
                    double highPrice;
                    double lowPrice;
                }

                Map<String, HighLow> results = new HashMap<>();

                //try {
                    //File file = new File("/path/to/file");
                    //FileReader fr = new FileReader(file);
                    //BufferedReader br = new BufferedReader(fr);
                    String line = "";
                    String[] tempArr;
                    //while((line = br.readLine()) != null) {
                       tempArr = line.split(",");
                       String code = tempArr[0];
                       double price = Double.parseDouble(tempArr[1]);
                       if (results.containsKey(code)) {
                            HighLow hl = results.get(code);
                            if (price > hl.highPrice) {
                                hl.highPrice = price;
                            } else if (price < hl.lowPrice) {
                                hl.lowPrice = price;
                            }
                        } else {
                            HighLow hl = new HighLow();
                            hl.highPrice = price;
                            hl.lowPrice = price;
                            results.put(code, hl);
                        }
                    //}
                    //br.close();
                //} catch(IOException ioe) {
                    //ioe.printStackTrace();
                //}

                for (var item : results.entrySet()) {
                    System.out.println("%s, %s, %s".formatted(item.getKey(), item.getValue().lowPrice, item.getValue().highPrice));
                }
                
                return 0;}, 0),
            new Test<>(() -> {
                
                List<String> data = List.of(
                    "TRADE, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 102.0",
                    "CANCEL, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 101.4",
                    "TRADE, BHP, 101.5", "CANCEL, AMP, 64.5", "TRADE, BHP, 102.0"
                );

                //class Action {Action(String action) {}}
                //class StockCode{StockCode(String stockCode) {}}
                //class Price{Price(double price) {}}
                /*class Data {
                    Data(String a, String b, String c) {}
                    Action action() {return null;}
                }
                */
                /*
                record MappedData2(Action action, StockCode stockCode, Price price) {}
                class MappedData {
                    MappedData(Action action, StockCode stockCode, Price price) {}
                }

                class DataSupport {
                    Data toData(String data) {
                        String[] record = data.split(",");
                        // For now, we are assuming data is clean
                        return new Data(record[0], record[1], record[2]);
                    }

                    MappedData toMappedData(Data data) {
                        // For now, we are assuming data is clean
                        return new MappedData(new Action(data.action()), new StockCode(data.stock()), new Price(data.price()));
                    }
                }
                class DecoratedPrice {
                    double value;
                    long count;

                    Price(double value) {
                        this.value = value;
                        count++;
                    }

                    void traded() {count++;}
                    void cancelled() {count--;}

                    public int hashCode() {return Double.valueOf(value).hashCode();}
                }
                
                class TradingPrices {
                    final Set<Price> priceSet = new HashSet<>();
                    TradingPrices add(Price price) {

                    }
                }

                class StockSupport {
                    void consume(Map<StockCode, TradingPrices> map, MappedData data) {
                        // can we move this to a mapper that returns MappedData.action, stock, price
                        var action = data.action();
                        var stock = data.stockCode();
                        var price = data.price();

                        if (map.containsKey(stock) == false) {
                            map.put(stock);
                        }
                        
                        var tradingPrices = map.get(stock);
                        tradingPrices.
                    }

                    boolean stockContainsPrice(StockCode stockCode, Double price) {
                        return stockCode.priceSet.contains(price);
                    }
                }


                final DataSupport ds = new DataSupport();

                HashSet<StockCode> stock = data.stream()
                    .map(ds::toData)
                    .collect(// Supplier<S>, BiConsumer<S,V>, BiConsumer<S,S>
                        //HashSet::new,
                        () -> new HashSet<Stock>(),
                        null,
                        null
                    );
                */
                return 0;}, 0),
            new Test<>(() -> {
                
                return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0),
            new Test<>(() -> {return 0;}, 0)
        );
    }
}

