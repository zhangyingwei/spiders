package com.zhangyingwei.spider.cameras.store;

import com.zhangyingwei.cockroach.executer.Task;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.cockroach.utils.FileUtils;
import com.zhangyingwei.cockroach.utils.NameUtils;
import com.zhangyingwei.spider.cameras.ItemObject;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2018/1/17.
 */
public class OpentopiaItemStore implements IStore {
    private String fileName = NameUtils.name(OpentopiaItemStore.class);
    private File file = new File("src/main/resources/images/opentopia/csv/"+fileName+".csv");
    private BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    public OpentopiaItemStore() throws IOException {
    }

    @Override
    public void store(TaskResponse response) throws Exception {
        byte[] bytes = response.getContentBytes();
        String fileName = FileUtils.getFileNameOrUuid(response).concat(".jpg");
        FileUtils.save(bytes,"src/main/resources/images/opentopia",fileName);
        ItemObject itemObject = (ItemObject) response.getTask().getExtr();
        itemObject.setImgName(fileName);
        String line = itemObject.toCsvLine();
        System.out.println(line);
        writer.write(line);
        writer.newLine();
        writer.flush();
    }
}
