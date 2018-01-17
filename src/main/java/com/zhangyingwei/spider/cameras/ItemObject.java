package com.zhangyingwei.spider.cameras;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by zhangyw on 2018/1/17.
 */
public class ItemObject {
    private String url;
    private String desc;
    private String imgName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String toCsvLine() {
        String host = "";
        try {
            host = new URL(this.getUrl()).getHost();
        } catch (MalformedURLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        String url = this.getUrl();
        String desc = this.getDesc();
        String imageName = this.getImgName();
        String result = String.format("[%s] [%s] [%s] [%s]", host, url, desc, imageName);
        return result;
    }

    @Override
    public String toString() {
        return "ItemObject{" +
                "url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
