package me.jamieburns.streams.nasdaq2;

import java.util.List;
import java.util.stream.Stream;

public class TroubleshootNasdaqTest {
    
    public static void main(String... args) {
        new TroubleshootNasdaqTest().troubleshoot();
    }

    public void troubleshoot() {
        me.jamieburns.streams.TestSupport.tests(
            new me.jamieburns.streams.Test<String>(() -> {

                List<String> data = List.of(
                    "TRADE, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 102.0",
                    "CANCEL, BHP, 100.0", "TRADE, AMP, 64.5", "TRADE, BHP, 101.4",
                    "TRADE, BHP, 101.5", "CANCEL, AMP, 64.5", "TRADE, BHP, 102.0"
                );

                class Action {Action(String action) {}}
                class StockCode{StockCode(String stockCode) {}}
                class Price{Price(double price) {}}
                class Data {
                    Data(String a, String b, String c) {}
                    Action action() {return new Action(null);}
                }

                record MappedData2(Action action, StockCode stockCode, Price price) {}
                class MappedData {
                    MappedData(Action action, StockCode stockCode, Price price) {}
                }
                
                class DataSupport {
                    Data toData(String record) {
                        String[] fields = record.split(",");
                        // For now, we are assuming data is clean
                        return new Data(fields[0], fields[1], fields[2]);
                    }
                    /*
                    static MappedData toMappedData(Data data) {
                        // For now, we are assuming data is clean
                        return new MappedData(new Action(data.action()), new StockCode(data.stock()), new Price(data.price()));
                    }
                    */
                }

                DataSupport ds = new DataSupport();

                Stream<Data> s = data.stream()
                    .map(ds::toData);

            return "";}, "")
        );
    }
}
/*
@FunctionalInterface
interface Lambda {
    void fn();
}
*/