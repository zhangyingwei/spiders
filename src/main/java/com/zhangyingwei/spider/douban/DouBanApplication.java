package com.zhangyingwei.spider.douban;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AutoClose;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.spider.douban.store.DouBanStore;

/**
 * Created by zhangyw on 2018/1/5.
 */

@EnableAutoConfiguration
@ThreadConfig(num = 10, sleep = 1000)
@Store(DouBanStore.class)
@AutoClose(false)
public class DouBanApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://www.douban.com/group/haixiuzu/","douban"));
        CockroachApplication.run(DouBanApplication.class, queue);
    }
}
