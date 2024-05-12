package com.sbm.admin.thread;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 无序 CompletableFuture
 */
public class CompletableFutureDisorder {


    public void run(){
        List<String> list = Arrays.asList("接口1", "接口2", "接口3");

        try {
            List<CompletableFuture<String>> allCompleted = new ArrayList<>();

            for (String item : list) {
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> doSomeWork(item))
                        .thenApplyAsync(resultItem -> {
                            System.out.println("处理完成: " + resultItem);
                            return resultItem;
                        });
                allCompleted.add(future);
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(
                    allCompleted.toArray(new CompletableFuture[0])
            );

            CompletableFuture<List<String>> allDone = allOf.thenApplyAsync(v ->
                    allCompleted.stream()
                            .map(CompletableFuture::join)
                            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
            );

            List<String> result = allDone.get();
            System.out.println("最终结果: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static String doSomeWork(String input) {
        long threadId = Thread.currentThread().getId();
        // 这里可以放入你真正的业务逻辑
        return "Thread ID: " + threadId + ", 完成: " + input;
    }
}
