package com.zhangyingwei.spider.github;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.spider.github.store.GitHubStore;

/**
 * Created by zhangyw on 2018/1/23.
 */
@EnableAutoConfiguration
@AppName("github spider")
@Store(GitHubStore.class)
@ThreadConfig(num = 1, sleep = 1000)
@AutoClose(false)
public class GithubApplication {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("https://github.com/search/advanced?q=java", "github.lang"));
        CockroachApplication.run(GithubApplication.class, queue);
    }
}
