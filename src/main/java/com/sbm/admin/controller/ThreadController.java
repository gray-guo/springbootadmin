package com.sbm.admin.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.sbm.admin.thread.CompletableFutureDisorder;
import com.sbm.admin.thread.CompletableFutureOrder;
import com.sbm.admin.util.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;


/**
 * 多线程 DEMO
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @SaIgnore
    @RequestMapping(value = "/test")
    public R test() throws ExecutionException, InterruptedException {

        CompletableFutureOrder completableFutureOrder = new CompletableFutureOrder();
        CompletableFutureDisorder completableFutureDisorder = new CompletableFutureDisorder();

        // 顺序执行
        completableFutureOrder.run();

        // 无序执行
//        completableFutureDisorder.run();

        return R.ok();
    }




}
