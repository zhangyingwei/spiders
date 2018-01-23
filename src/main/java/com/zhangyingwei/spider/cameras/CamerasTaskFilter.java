package com.zhangyingwei.spider.cameras;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.IQueueTaskFilter;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class CamerasTaskFilter implements IQueueTaskFilter {
    @Override
    public boolean accept(Task task) {
        return StringUtils.isNotBlank(task.getUrl()) && task.getDeep() < 5;
    }
}
