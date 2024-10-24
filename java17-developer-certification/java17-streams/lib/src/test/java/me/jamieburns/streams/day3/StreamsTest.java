package me.jamieburns.streams.day3;

import static me.jamieburns.streams.TestSupport.tests;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import me.jamieburns.streams.Test;

public class StreamsTest {
    
public static void main(String[] args) {
        new StreamsTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test<>(() -> {
                
                // version 1

                record Request(Integer time) {}

                class TransactionWindow {

                    public final int windowSize;
                    public final int allowedRequests;
                    public int leftSecond;
                    public int currentSecond;
                    public Deque<Request> requestList = new LinkedList<>();

                    public TransactionWindow(int windowSize, int allowedRequests) {
                        this.windowSize = windowSize;
                        this.allowedRequests = allowedRequests;
                        currentSecond = 1;
                        leftSecond = 1;
                    }

                    public void accept(Request request) {
                        // has our window advanced
                        if (currentSecond < request.time()) {
                            updateWindowBoundary(request);
                            dropTrailingRequests();
                        }
                        requestList.offerFirst(request);
                    }

                    public boolean allowRequest() {
                        return requestList.size() <= allowedRequests;
                    }

                    private void updateWindowBoundary(Request request) {
                        currentSecond = request.time();
                        if (currentSecond - windowSize > 0) {
                            leftSecond = currentSecond - windowSize + 1;
                        }
                    }

                    private void dropTrailingRequests() {
                        Request lastRequest = requestList.peekLast();
                        while (lastRequest != null && lastRequest.time() < leftSecond) {
                            requestList.pollLast();
                            lastRequest = requestList.peekLast();
                        }
                    }
                }

                class TransactionManager {
                    List<TransactionWindow> twList = Arrays.asList(
                        new TransactionWindow(1, 3),
                        new TransactionWindow(4, 10),
                        new TransactionWindow(60, 60)
                    );

                    public boolean allowRequest(Request request) {
                        for (TransactionWindow tw : twList) {
                            tw.accept(request);
                            if (tw.allowRequest() == false) {
                                return false;
                            }
                        }
                        return true;
                    }
                }

                List<Integer> l = Arrays.asList(1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8);

                TransactionManager tm = new TransactionManager();

                for (int i = 0; i < l.size(); i++) {
                    System.out.println(tm.allowRequest(new Request(l.get(i))));
                }
                
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
            new Test<>(() -> {return 0;}, 0)
        );
    }
}
