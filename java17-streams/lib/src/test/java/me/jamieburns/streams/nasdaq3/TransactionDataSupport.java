package me.jamieburns.streams.nasdaq3;

public final class TransactionDataSupport {
    static TransactionData toTransactionData(Data data) {
        // For now, we are assuming data is clean
        Action action = ActionSupport.fromDataAction(data.action());
        Double price = Double.parseDouble(data.price());
        return new TransactionData(action, data.stockCode(), price);
    }
}
