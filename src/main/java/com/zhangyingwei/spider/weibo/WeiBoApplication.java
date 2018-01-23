package com.zhangyingwei.spider.weibo;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.spider.weibo.generator.WeiBoCookieGeneration;
import com.zhangyingwei.spider.weibo.generator.WeiBoHeaderGenerator;
import com.zhangyingwei.spider.weibo.store.WeiBoStore;

/**
 * Created by zhangyw on 2018/1/5.
 */
@EnableAutoConfiguration
@ThreadConfig(num = 1, sleep = 1000)
@CookieConfig(cookieGenerator = WeiBoCookieGeneration.class)
@HttpHeaderConfig(headerGenerator = WeiBoHeaderGenerator.class)
@Store(WeiBoStore.class)
@AutoClose(true)
public class WeiBoApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://m.weibo.cn/p/second?containerid=1005051750783647_-_FOLLOWERS"));
        CockroachApplication.run(WeiBoApplication.class, queue);
    }
}
