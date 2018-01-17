package com.zhangyingwei.spider.weibo.generator;

import com.zhangyingwei.cockroach.common.generators.MapGenerator;
import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/1/5.
 */
public class WeiBoHeaderGenerator implements MapGenerator{
    private Map headers;

    public Map getHeaders() {
        this.headers = new HashMap();
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
        return headers;
    }

    @Override
    public Map get(Task task) {
        return this.getHeaders();
    }
}
