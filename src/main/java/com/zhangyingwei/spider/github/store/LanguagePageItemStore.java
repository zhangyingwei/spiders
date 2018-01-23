package com.zhangyingwei.spider.github.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.spider.github.RepItem;
import org.jsoup.select.Elements;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class LanguagePageItemStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {

        Elements actions = response.select(".pagehead-actions");
        String watch = actions.select("li").get(0).select(".social-count").text();
        String star = actions.select("li").get(1).select(".social-count").text();
        String fork = actions.select("li").get(2).select(".social-count").text();

        RepItem item = (RepItem) response.getTask().getExtr();
        item.setWatch(watch);
        item.setStar(star);
        item.setFork(fork);

        System.out.println(item);
    }
}
