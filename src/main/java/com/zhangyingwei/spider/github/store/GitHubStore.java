package com.zhangyingwei.spider.github.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * Created by zhangyw on 2018/1/23.
 */
public class GitHubStore implements IStore {
    private LanguageStore languageStore = new LanguageStore();
    private LanguagePageStore languagePageStore = new LanguagePageStore();
    private LanguagePageItemStore languagePageItemStore = new LanguagePageItemStore();

    @Override
    public void store(TaskResponse response) throws Exception {
        if (response.isGroup("github.lang")) {
            languageStore.store(response);
        } else if (response.isGroup("github.lang.page")) {
            languagePageStore.store(response);
        } else if (response.isGroup("github.lang.page.item")) {
            languagePageItemStore.store(response);
        }
    }
}
