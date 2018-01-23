package com.zhangyingwei.spider.cameras;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.AutoClose;
import com.zhangyingwei.cockroach.annotation.EnableAutoConfiguration;
import com.zhangyingwei.cockroach.annotation.Store;
import com.zhangyingwei.cockroach.annotation.ThreadConfig;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.spider.cameras.store.MeteosurfcanariasStore;
import com.zhangyingwei.spider.cameras.store.OpentopiaStore;
import com.zhangyingwei.spider.douban.store.DouBanStore;

/**
 * Created by zhangyw on 2018/1/17.
 */
@EnableAutoConfiguration
@ThreadConfig(num = 20, sleep = 10)
//@Store(OpentopiaStore.class)
@Store(MeteosurfcanariasStore.class)
@AutoClose(false)
public class CamerasApplicatoin {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of().filter(new CamerasTaskFilter());
//        for (int i = 1; i < 72; i++) {
//            queue.push(new Task("http://www.opentopia.com/hiddencam.php?showmode=standard&country=%2A&seewhat=highlyrated&p="+i,"opentopia"));
//        }
        queue.push(new Task("http://www.meteosurfcanarias.com/en/webcams","meteosurfcanarias"));
        CockroachApplication.run(CamerasApplicatoin.class, queue);
    }
}
