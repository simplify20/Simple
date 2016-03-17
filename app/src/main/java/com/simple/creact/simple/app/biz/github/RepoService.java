package com.simple.creact.simple.app.biz.github;


import com.simple.creact.library.framework.repository.DataCallback;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public interface RepoService {

    /**
     * 获取某个人的全部仓库
     *
     * @param owner
     * @return
     */
    void getRepos(String owner, DataCallback callback);
}
