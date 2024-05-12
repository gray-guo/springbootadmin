package com.sbm.admin.thread;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * CompletableFuture 有序
 */
public class CompletableFutureOrder {


    public void run(){
        List<String> list = Arrays.asList("接口1", "接口2", "接口3");

        try {
            List<CompletableFuture<String>> futures = list.stream()
                    .map(string -> CompletableFuture.supplyAsync(() -> doSomeWork(string)))
                    .collect(Collectors.toList());

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

            CompletableFuture<List<String>> allCompleted = allFutures.thenApply(v ->
                    futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList())
            );

            List<String> result = allCompleted.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static String doSomeWork(String input) {
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread ID: " + threadId + ", 完成: " + input);
        // 这里可以放入你真正的业务逻辑
        return "Thread ID: " + threadId + ", 完成: " + input;
    }
}
