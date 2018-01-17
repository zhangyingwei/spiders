package com.zhangyingwei.spider.cameras.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.spider.cameras.ItemObject;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/1/17.
 */
public class OpentopiaStore implements IStore {
    private OpentopiaItemStore opentopiaItemStore = new OpentopiaItemStore();

    public OpentopiaStore() throws IOException {
    }

    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("opentopia")) {
            response.select(".boxcontent").select("li").select("a").stream().forEach(element -> {
                String path = element.attr("href");
                if (path.startsWith("/webcam")) {
                    String url = "http://www.opentopia.com".concat(path);
                    try {
                        response.getQueue().push(new Task(url, "opentopia.item"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (response.isGroup("opentopia.item")) {
            Elements img = response.select("#camdetail").select(".big").select("img");
            String url = img.attr("src");
            String desc = img.attr("alt");
            if (url.startsWith("http")) {
                ItemObject itemObject = new ItemObject();
                itemObject.setUrl(url);
                itemObject.setDesc(desc);
                try {
                    Task task = new Task(url, "opentopia.item.page");
                    task.setExtr(itemObject);
                    response.getQueue().push(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if (response.isGroup("opentopia.item.page")){
            opentopiaItemStore.store(response);
        }
    }
}
