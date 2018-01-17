package com.zhangyingwei.spider.weibo.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import org.jsoup.select.Elements;

/**
 * Created by zhangyw on 2018/1/5.
 */
public class WeiBoStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        System.out.println(taskResponse.getContent());
    }
}
