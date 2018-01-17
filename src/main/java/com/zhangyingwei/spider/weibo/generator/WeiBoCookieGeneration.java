package com.zhangyingwei.spider.weibo.generator;

import com.zhangyingwei.cockroach.common.generators.StringGenerator;
import com.zhangyingwei.cockroach.executer.Task;

/**
 * Created by zhangyw on 2018/1/5.
 */
public class WeiBoCookieGeneration implements StringGenerator {
    private String cookie;

    public String getCookie() {
        this.cookie = "M_WEIBOCN_PARAMS=luicode%3D10000012%26lfid%3D1005051750783647_-_FOLLOWERS; expires=Fri, 05-Jan-2018 07:02:58 GMT; Max-Age=600; path=/; domain=.weibo.cn; HttpOnly";
        return cookie;
    }

    @Override
    public String get(Task task) {
        return this.getCookie();
    }
}
