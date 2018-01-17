package com.zhangyingwei.spider.cameras.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.FileUtils;
import com.zhangyingwei.cockroach.utils.NameUtils;
import com.zhangyingwei.spider.cameras.ItemObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhangyw on 2018/1/17.
 */
public class MeteosurfcanariasItemStore implements IStore {
    private String fileName = NameUtils.name(OpentopiaItemStore.class);
    private File file = new File("src/main/resources/images/meteosurfcanarias/csv/"+fileName+".csv");
    private BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    public MeteosurfcanariasItemStore() throws IOException {
    }

    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("meteosurfcanarias.item")) {
            response.select(".display-webcams-rest").select("div").stream().forEach(element -> {
                String path = element.select("img").attr("src");
                String url = "http://www.meteosurfcanarias.com".concat(path);
                String desc = element.select("p").text();
                if (path.startsWith("/1-webcams")) {
                    ItemObject itemObject = new ItemObject();
                    itemObject.setUrl(url);
                    itemObject.setDesc(desc);
                    Task task = new Task(url, "meteosurfcanarias.item.page");
                    task.setExtr(itemObject);
                    try {
                        response.getQueue().push(task);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (response.isGroup("meteosurfcanarias.item.page")) {
            byte[] bytes = response.getContentBytes();
            String fileName = FileUtils.getFileNameOrUuid(response).concat(".jpg");
            FileUtils.save(bytes,"src/main/resources/images/meteosurfcanarias",fileName);
            ItemObject itemObject = (ItemObject) response.getTask().getExtr();
            itemObject.setImgName(fileName);
            String line = itemObject.toCsvLine();
            System.out.println(line);
            writer.write(line);
            writer.newLine();
            writer.flush();
        }
    }
}
