package com.zhangyingwei.spider.github.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.spider.github.RepItem;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class LanguagePageStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        response.select(".repo-list-item").select("h3").select("a").stream().map(element -> {
            return element.attr("href");
        }).forEach(url -> {
            String resalUrl = "https://github.com" + url;
            try {
                Task task = new Task(resalUrl, "github.lang.page.item").nextDeepBy(response.getTask());
                RepItem item = new RepItem();
                item.setName(url);
                item.setUrl(resalUrl);
                task.setExtr(item);
                response.getQueue().push(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
