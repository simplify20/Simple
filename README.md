**Simple：一个Android Model层架构**
----------

####**Simple**主要实现了**MVP**或**MVVM**中的M层，是一个Model层框架。其利用了Repository Pattern作为实现方式。Simple可以简化应用中的Model层编码，提供清晰的业务层实现思路。
#### 编写的Simple的目的有以下几点：

- 提高Model层代码的可测性；
- 将Model层代码进行分层；
- 降低对第三方库的依赖以及第三方库代码的侵入性；
- 提供近乎插件的灵活性和可扩展性；

####**Simple的特性**
- 低侵入性。框架层未引用任何第三方库或Android API，全部基于JDK开发，通过抽象进行依赖，低层对高层透明；
- 可测性。Model的层级清晰，相互之间通过接口或抽象类耦合，耦合性很低，便于单元测试；
- 灵活性。Simple分为三层，每一层都可以被其他实现替代，下层的改变不会影响上层，下层对上层透明，利用注解库如Dagger可以轻松切换每一层的实现方式。
- 运用设计模式设计，符合设计原则。

####**Simple架构简图**

![这里写图片描述](http://img.blog.csdn.net/20160318112247332)

- Business Service。业务层，通过接口定义功能，View层使用此接口实现业务操作。业务层可以维持多个Repository,用于实现不同的业务需求；
- Repository。数据仓库，DataSource的代理，用于控制业务代码对数据源的访问（参考[代理模式][1]）；
- DataSource。数据源，可以管理多个数据请求者，实现数据源切换。将获取数据的请求和请求的实现者进行解耦（参考[命令模式][2]）
- DataFetcher。数据请求者，真正请求数据和数据层打交道的对象。
- 数据层。数据的存储位置。

各模块的依赖关系由上到下。
####**Simple各层依赖关系：**
![Simple Depends](http://img.blog.csdn.net/20160318121406134)
####**Simple在MVP架构中的位置：**
![MVP](http://img.blog.csdn.net/20160318120303145)
####**在MVVM中的位置：**
![MVVM](http://img.blog.csdn.net/20160318120339911)

####**RepositoryManager：**
用于管理业务逻辑代码所涉及的所有Repository，在创建View(Repository)时注册，在View销毁时，取消注册，并关闭Repository。
1.**配置**
在应用的build.gradle文件中：
引入Simple library
>compile project(':library')
    
添加Dagger2支持:
在project的build.gradle文件下添加classpath 依赖（注：1.4与databinding不兼容）：
> dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
    
   在app的build.gradle文件下，应用注解处理器插件：
>apply plugin: 'com.neenbedankt.android-apt'//注解处理器

app的dependencies下加入Dagger2依赖：
>   //dagger2
    apt 'com.google.dagger:dagger-compiler:2.0.2'
    compile 'com.google.dagger:dagger:2.0.2'
    //producer,可选
    compile 'com.google.dagger:dagger-producers:2.0-beta'
    provided 'javax.annotation:jsr250-api:1.0'
    
 app的android配置下启用Databinding
 > dataBinding{
	       enabled true
    }
    
  **2.写布局：activity_repo_search.xml**( [参考databinding教程](https://realm.io/news/data-binding-android-boyar-mount/))
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app = "http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <data>

        <import type="android.view.View" />

        <variable
            name="repoVm"
            type="com.simple.creact.simple.app.presentation.viewmodel.RepoViewModel" />
    </data>

    <RelativeLayout android:padding="10dp">

        <LinearLayout
            android:id="@+id/search_container"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:orientation="horizontal">

            <EditText
                android:id="@+id/input_name"
                android:layout_width="200dp"
                android:layout_height="wrap_content"
                android:singleLine="true"
                android:hint="输入git用户名"
                android:textColorHint="#c7c6c6" />

            <Button
                android:id="@+id/search_btn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="right"
                android:onClick="search"
                android:text="Search" />
        </LinearLayout>

        <android.support.v7.widget.RecyclerView
            android:id="@+id/repos_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:visibility="@{repoVm.isEmpty?View.GONE:View.VISIBLE}"
            android:layout_below="@id/search_container"></android.support.v7.widget.RecyclerView>

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:visibility="@{repoVm.isEmpty?View.VISIBLE:View.GONE}">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true"
                android:text="没有相关的仓库"
                android:textColor="#c7c6c6" />
        </RelativeLayout>
    </RelativeLayout>
</layout> 
```

**3.写ViewModel**:
```java
public class RepoViewModel {
	
	//observable objects
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
```
**4.在SearchRepoActivity中绑定ViewModel**:
```java
 private RepoViewModel repoViewModel = new RepoViewModel();
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_search);
        //bind view model
        activityMainBinding.setRepoVm(repoViewModel);
        mDataCallback = new RepoCallback(this);
        initViews();
        //注入依赖
        injectDepens();
    }
```
**5.编写业务接口**
```java
public interface RepoService {

    /**
     * 获取某个人的全部仓库
     *
     * @param owner
     * @param callback UICallback
     * @return 
     */
    void getRepos(String owner, DataCallback callback);
}
```
**6.编写业务实现类**
```java
public class RepoServiceImpl implements RepoService {
	//使用抽象基类，而不引入具体类，降低耦合
    private BaseRepository repository;
	//inject concrete Repository
    @Inject
    public RepoServiceImpl(@Named(QualifierConstants.PROVIDE_REPO_REPOSITORY_DAGGER) BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getRepos(String owner, DataCallback repoListCallback) {
		//构造请求参数
        RequestParameter extraParams = RequestParameter.newActionParameter(QualifierConstants.PROVIDE_REPO_REPOSITORY);
        //设置回调
        repository.setCallback(repoListCallback);
        //请求数据
        repository.getData(extraParams, owner);
    }
}
```
**7.编写Repository**
```java
public class RepoRepository extends BaseObservableRepository<List<Repo>, List<Repo>> {
	//inject concrete datasource
    @Inject
    public RepoRepository(@Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_RX)DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
}
```
这里直接继承`BaseObservableRepository`，也可以继承`BaseDaggerRepository` 实现另一种策略。

**8.编写DataSource和DataFetcher**

```java
//RepoDataSource 
public class RepoDataSource extends BaseObservableDataSource<List<Repo>, List<Repo>> {

    @Inject
    public RepoDataSource(DataFetcher<List<Repo>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public List<Repo> convert(List<Repo> repos) {
        return repos;
    }
	//RepoFetcher
    public static class RepoFetcher extends GitHubDataFetcher<List<Repo>> {

        @Inject
        public RepoFetcher(GitHubApi gitHubApi) {
            super(gitHubApi);
        }

        @Override
        public List<Repo> fetchDataImpl(@NonNull RequestParameter parameter) throws Exception {
            call = gitHubApi.listRepos(parameter.get("user"));
            List<Repo> result = call.execute().body();
            return result;
        }

        @Override
        public IParameter putValues(@NonNull IParameter<String, String> parameter, @NonNull String... values) {
            if (values == null || values.length == 0)
                return parameter;
            parameter.put("user", values[0]);
            return parameter;
        }
    }
}
```
**9.编写Module和Component** [参考Dagger2教程](http://google.github.io/dagger/)
`RepoComponent`:
```java
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = RepoModule.class)
public interface RepoComponent {
    void inject(SearchRepoActivity searchRepoActivity);

    RepoService getRepoService();
}
```
`RepoModule`:
```java
@Module(includes = {GitHubApiModule.class})
public class RepoModule {
    /**************************************
     * DataFetcher
     **************************************/
    @ActivityScope
    @Provides
    DataFetcher<List<Repo>> reposDataFetcher(RepoDataSource.RepoFetcher reposFetcher) {
        return reposFetcher;
    }

    /***************************************
     * DataSource
     ***************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_DATA_SOURCE_RX)
    DataSource repoDataSource(DataFetcher<List<Repo>> dataFetcher) {
        return new RepoDataSource(dataFetcher);
    }

    /**************************************
     * Repository
     **************************************/
    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_REPO_REPOSITORY)
    BaseRepository reposRepository(RepoRepository repository) {
        return repository;
    }

    /*************************************
     * Service
     *************************************/
    @ActivityScope
    @Provides
    RepoService repoService(RepoServiceImpl repoService) {
        return repoService;
    }


}
```
`RepoModule`依赖的`GitHubApiModule`，这里使用了[Retrofit](http://square.github.io/retrofit/)作http请求。
```java
@Module
public class GitHubApiModule {
    public static final String API_URL = "https://api.github.com";
    private String baseUrl = API_URL;

    public GitHubApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public GitHubApiModule() {
    }

    /***************************
     * API
     ***************************/

    @ActivityScope
    @Provides
    @Named(QualifierConstants.PROVIDE_GIT_HUB_API)
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScope
    @Provides
    GitHubApi gitHubApi(@Named(QualifierConstants.PROVIDE_GIT_HUB_API) Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }


}
```
**10.SearchRepoActivity中使用RepoService**
```java
//使用Dagger注入RepoService，引用接口以降低耦合
@Inject
RepoService repoService;
```
```java
//请求仓库数据
repoService.getRepos(name.toString(), mDataCallback);
```
**10.RepoDataCallback**
```java
    /**
     * DataCallback实现
     */
    private static class RepoCallback extends DataCallbackAdapter<List<Repo>> {
	    //使用WeakReference
        private WeakReference<SearchRepoActivity> searchRepoActivityWeak;

        public RepoCallback(SearchRepoActivity searchRepoActivity) {
            searchRepoActivityWeak = new WeakReference(searchRepoActivity);
        }

        @Override
        public void postUIError(Throwable e) {
	        //处理异常
            SearchRepoActivity searchRepoActivity = searchRepoActivityWeak.get();
            if (searchRepoActivity != null)
                searchRepoActivity.showError();
        }

        @Override
        public void postUISuccess(List<Repo> repoList) {
	        //处理返回的数据
            SearchRepoActivity searchRepoActivity = searchRepoActivityWeak.get();
            if (searchRepoActivity != null) {
                searchRepoActivity.showSuccess(repoList);
            }
        }

    }
```
####**示例工程说明：**
包结构：

![这里写图片描述](http://img.blog.csdn.net/20160318124547753)

**biz**：组织与业务实现有关的代码，如业务接口，业务实现，和Repository,可以在该包下细分多个业务模块。
**data**：组织与数据有关的模块，如di（依赖注入）,bean,datasource,datafetcher等。
**presentation**：组织UI相关的代码，如Activity,Fragment,ViewModel等
**util**：工具类
####**相关类说明**：
biz->`BaseDaggerRepository`:

- 依赖于google guava的`ListenableFuture` 实现异步或同步请求,如果你想使用guava,可以继承这个Repository实现自己特定业务场景的Repository

biz ->`BaseObservableRepository`：

- 依赖RxJava的`Observable` 实现异步或同步请求

data ->datasource->`BaseObservableDataSource`：

- 依赖于RxJava的DataSource,返回Observable

data->datasource->`BaseDaggerDataSource`：

- 依赖于google guava 实现的DataSource，返回`ListenableFuture`

data->datasource->`BaseDaggerMultiDataSource`：

- 依赖于Guava的可以配置多个DataFetcher的DataSource基类。使用它可以实现以下效果：
![这里写图片描述](http://img.blog.csdn.net/20160318133036422)
利用Dagger2可以配置DataFetcher非常容易，只用改变Module中的Porvide配置即可。

当然，你可以通过继承`BaseDataSource` 或 `BaseRepository` 实现自定义的DataSource或者Repository基类

**注：在具体业务场景的Repository或者DataSource中，应避免导入第三方API**，例如：

- 在`RepoDataSource extends BaseObservableDataSource<List<Repo>, List<Repo>>` 中
除自定义类型和JDK类型外未引入RxJava或者Guava的API,这样可以最大程度减少第三方库的侵入性，在需要更换实现时，这些具体类可以不用改变，只用你改变父类的实现即可。

- 可参考`RepoDaggerDataSource `,`RepoRepository`,`RepoDaggerDataSource` 等类的实现，编写你的低侵入式类。

`interface DataCallback<C>`：

Repository中有个方法名为`setCallback(DataCallback<C> callback)` 异步请求时，View层在在调业务方法时可以传入DataCallback,在异步处理结束后，Repository会通过此回调将数据或者异常返给相应View.

`UIDataCallback<C> implements DataCallback<C>`：

实现了将非UI thread的结果post到UI thread,并且使用了装饰模式，可以将一个非`UIDataCallback<C>` 类型包装为`UIDataCallback<C>` 类型，在编写自己的DataCallback时，可以直接继承`DataCallbackAdapter<C> `(继承自` UIDataCallback<C>`),重写`postUISuccess`,`postUIError` 等方法，在这些方法中进行UI相关的操作。



####**参考链接：**

**博文地址**：http://blog.csdn.net/u012825445/article/details/50921345)

**Dagger2(Google):**
Video:https://www.youtube.com/watch?v=oK_XtfXPkqw
Guid:http://google.github.io/dagger/
Github:https://github.com/google/dagger
More:http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/

**DataBinding:**
https://realm.io/news/data-binding-android-boyar-mount/
https://www.zybuluo.com/shark0017/note/256112
http://developer.android.com/intl/zh-cn/tools/data-binding/guide.html

[1]:http://blog.csdn.net/chenssy/article/details/11179815
[2]:https://zh.wikipedia.org/wiki/%E5%91%BD%E4%BB%A4%E6%A8%A1%E5%BC%8F