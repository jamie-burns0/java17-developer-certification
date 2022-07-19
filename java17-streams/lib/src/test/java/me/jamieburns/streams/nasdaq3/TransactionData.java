package me.jamieburns.streams.nasdaq3;

public record TransactionData(Action action, String stockCode, Double price) {}
