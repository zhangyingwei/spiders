package com.zhangyingwei.spider.douban.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Created by zhangyw on 2018/1/5.
 */
public class DouBanStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("douban")) {
            response.select("table").select(".title").select("a").stream().map(element -> {
                return element.attr("href");
            }).filter(url -> StringUtils.isNotBlank(url)).forEach(url -> {
                try {
                    response.getQueue().push(new Task(url,"douban.item"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (response.isGroup("douban.item")) {
            response.select(".article").select(".topic-content").select("img").stream().map(element -> {
                return element.attr("src");
            }).filter(url -> StringUtils.isNotBlank(url)).forEach(url -> {
                try {
                    response.getQueue().push(new Task(url,"douban.item.img"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (response.isGroup("douban.item.img")) {
            byte[] bytes = response.getContentBytes();
            String name = FileUtils.getFileNameOrUuid(response);
            FileUtils.save(bytes,"D:\\haixiu", UUID.randomUUID().toString().concat(".jpg"));
        }
    }
}
