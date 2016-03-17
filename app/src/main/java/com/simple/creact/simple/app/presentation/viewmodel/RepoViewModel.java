package com.simple.creact.simple.app.presentation.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.simple.creact.simple.app.data.entity.github.Repo;

import java.util.List;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class RepoViewModel {

    public ObservableArrayList<Repo> observableRepoList = new ObservableArrayList<>();
    public ObservableBoolean isEmpty = new ObservableBoolean(false);

    public void add(Repo repo) {
        observableRepoList.add(repo);
        isEmpty.set(observableRepoList.isEmpty());
    }

    public void add(int index, Repo repo) {
        observableRepoList.add(index, repo);
        isEmpty.set(observableRepoList.isEmpty());
    }

    public void addAll(List<Repo> repos) {
        observableRepoList.clear();
        if (repos != null)
            observableRepoList.addAll(repos);
        isEmpty.set(observableRepoList.isEmpty());
    }
}
