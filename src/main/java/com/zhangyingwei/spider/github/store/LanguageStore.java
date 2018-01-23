package com.zhangyingwei.spider.github.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class LanguageStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        response.select("#search_language > optgroup:nth-child(3)").select("option").stream().map(item -> {
            return item.attr("value");
        }).filter(item -> {
//            return item.toLowerCase().contains("java");
            return true;
        }).forEach(item -> {
            String url = "https://github.com/search?l=&o=desc&q=language%3A"+item+"&ref=advsearch&s=stars&type=Repositories&utf8=%E2%9C%93";
            try {
                response.getQueue().push(new Task(url,"github.lang.page").nextDeepBy(response.getTask()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
