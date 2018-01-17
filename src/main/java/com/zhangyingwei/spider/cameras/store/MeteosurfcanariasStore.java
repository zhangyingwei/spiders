package com.zhangyingwei.spider.cameras.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;

import java.io.IOException;

/**
 * Created by zhangyw on 2018/1/17.
 */
public class MeteosurfcanariasStore implements IStore {
    private MeteosurfcanariasItemStore meteosurfcanariasItemStore = new MeteosurfcanariasItemStore();

    public MeteosurfcanariasStore() throws IOException {
    }

    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("meteosurfcanarias")) {
            if (response.select(".display-webcams-peq").size() > 3) {
                response.select(".display-webcams-peq").select("a").stream().forEach(element -> {
                    String path = element.attr("href");
                    String url = "http://www.meteosurfcanarias.com".concat(path);
                    try {
                        response.getQueue().push(new Task(url,"meteosurfcanarias.item"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else {
                response.select("map").select("area").stream().forEach(element -> {
                    String href = element.attr("href");
                    String url = "http://www.meteosurfcanarias.com".concat(href);
                    try {
                        response.getQueue().push(new Task(url, "meteosurfcanarias"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        if (response.isGroupStartWith("meteosurfcanarias.item")) {
            this.meteosurfcanariasItemStore.store(response);
        }
    }
}
