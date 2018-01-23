package com.zhangyingwei.spider.github;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class RepItem {
    private String name;
    private String url;
    private String watch;
    private String star;
    private String fork;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getFork() {
        return fork;
    }

    public void setFork(String fork) {
        this.fork = fork;
    }

    @Override
    public String toString() {
        return "RepItem{" +
                "name='" + name + '\'' +
                ", watch='" + watch + '\'' +
                ", star='" + star + '\'' +
                ", fork='" + fork + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
