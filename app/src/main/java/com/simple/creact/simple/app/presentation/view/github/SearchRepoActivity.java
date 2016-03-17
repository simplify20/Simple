package com.simple.creact.simple.app.presentation.view.github;

import android.app.ProgressDialog;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.simple.creact.library.framework.repository.DataCallback;
import com.simple.creact.library.framework.repository.impl.DataCallbackAdapter;
import com.simple.creact.simple.R;
import com.simple.creact.simple.app.SimpleApplication;
import com.simple.creact.simple.app.biz.github.RepoService;
import com.simple.creact.simple.app.data.di.github.component.DaggerRepoComponent;
import com.simple.creact.simple.app.data.entity.github.Repo;
import com.simple.creact.simple.app.presentation.view.BaseActivity;
import com.simple.creact.simple.app.presentation.view.Bindable;
import com.simple.creact.simple.app.presentation.viewmodel.RepoViewModel;
import com.simple.creact.simple.app.util.DeviceUtil;
import com.simple.creact.simple.app.util.ToastUtil;
import com.simple.creact.simple.databinding.ActivityRepoSearchBinding;
import com.simple.creact.simple.databinding.CellRepoBinding;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class SearchRepoActivity extends BaseActivity {

    @Inject
    RepoService repoService;
    private ActivityRepoSearchBinding activityMainBinding;
    private RepoAdapter repoAdapter;
    private RepoViewModel repoViewModel = new RepoViewModel();
    private ProgressDialog progressDialog;
    private DataCallback<List<Repo>> mDataCallback;

    /**
     * Not recommend do like this,static method is hard to test
     *
     * @param recyclerView
     * @param repos
     */
    @BindingAdapter("dataSet")
    public static void setRepoList(RecyclerView recyclerView, List<Repo> repos) {
        RepoAdapter repoAdapter = (RepoAdapter) recyclerView.getAdapter();
        if (repos == null || repos.size() == 0) {
            ToastUtil.toastShortMsg("没有搜到任何结果~");
            return;
        }
        repoAdapter.setRepos(repos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_search);
        activityMainBinding.setRepoVm(repoViewModel);
        mDataCallback = new RepoCallback(this);
        initViews();
        injectDepens();
    }

    private void initViews() {
        repoAdapter = new RepoAdapter();
        activityMainBinding.reposList.setAdapter(repoAdapter);
        activityMainBinding.reposList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void injectDepens() {
        DaggerRepoComponent
                .builder()
                .applicationComponent(SimpleApplication.getApplicationComponent())
                .build()
                .inject(this);
    }


    public void search(View v) {
        DeviceUtil.toggleSoftInput(activityMainBinding.inputName, true);
        CharSequence name = activityMainBinding.inputName.getText();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        progressDialog = ProgressDialog.show(this, "搜索中...", "");
        repoService.getRepos(name.toString(), mDataCallback);
    }


    private void showError() {
        Toast.makeText(SearchRepoActivity.this, "出错了~", Toast.LENGTH_SHORT);
        progressDialog.dismiss();
    }

    private void showSuccess(List<Repo> repoList) {
        repoViewModel.addAll(repoList);
        progressDialog.dismiss();
    }

    /**
     * DataCallback
     */
    private static class RepoCallback extends DataCallbackAdapter<List<Repo>> {
        private WeakReference<SearchRepoActivity> searchRepoActivityWeak;

        public RepoCallback(SearchRepoActivity searchRepoActivity) {
            searchRepoActivityWeak = new WeakReference(searchRepoActivity);
        }

        @Override
        public void postUIError(Throwable e) {
            SearchRepoActivity searchRepoActivity = searchRepoActivityWeak.get();
            if (searchRepoActivity != null)
                searchRepoActivity.showError();
        }

        @Override
        public void postUISuccess(List<Repo> repoList) {
            SearchRepoActivity searchRepoActivity = searchRepoActivityWeak.get();
            if (searchRepoActivity != null) {
                searchRepoActivity.showSuccess(repoList);
            }
        }

    }

    private static class RepoViewHolder extends RecyclerView.ViewHolder implements Bindable<Repo> {
        private CellRepoBinding cellRepoBinding;

        public RepoViewHolder(View itemView) {
            super(itemView);
            cellRepoBinding = CellRepoBinding.bind(itemView);
        }

        @Override
        public void bindData(Repo repo) {

            cellRepoBinding.setRepo(repo);
            cellRepoBinding.executePendingBindings();
        }
    }

    private class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
        private List<Repo> repos;

        public void setRepos(List<Repo> repos) {
            this.repos = repos;
            notifyDataSetChanged();
        }

        @Override
        public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_repo, parent, false));
        }

        @Override
        public void onBindViewHolder(RepoViewHolder holder, int position) {
            holder.bindData(repos.get(position));
        }

        @Override
        public int getItemCount() {
            return repos == null ? 0 : repos.size();
        }
    }


}
