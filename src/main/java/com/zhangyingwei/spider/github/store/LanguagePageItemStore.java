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
        String watch = response.select("#js-repo-pjax-container > div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav > div > ul > li:nth-child(2) > form > div.select-menu.js-menu-container.js-select-menu > a.social-count.js-social-count").text();
        String star = response.select("#js-repo-pjax-container > div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav > div > ul > li:nth-child(3) > div > form.unstarred.js-social-form > a").text();
        String fork = response.select("#js-repo-pjax-container > div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav > div > ul > li:nth-child(4) > a.social-count").text();

        RepItem item = (RepItem) response.getTask().getExtr();
        item.setWatch(watch);
        item.setStar(star);
        item.setFork(fork);

        System.out.println(item);
    }
}
