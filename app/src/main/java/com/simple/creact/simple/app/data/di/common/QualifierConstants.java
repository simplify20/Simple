package com.simple.creact.simple.app.data.di.common;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public final class QualifierConstants {
    private QualifierConstants() {
    }

    /**
     * API: in case "includes" lead to conflict
     */
    public static final String PROVIDE_GIT_HUB_API = "github";

    /**
     * providers with same return type(abstract class or interface without Generic Parameters etc.)
     */
    public static final String PROVIDE_CONTRIBUTORS_REPOSITORY = "getContributors";
    public static final String PROVIDE_REPO_REPOSITORY = "getRepo";
    public static final String PROVIDE_REPO_REPOSITORY_DAGGER = "daggerGetRepo";
    public static final String PROVIDE_SINGLE_THREAD_EXECUTOR = "executor_single_thread_pool";
    public static final String PROVIDE_POST_SCHEDULE = "post_schedule";
    public static final String PROVIDE_WORK_SCHEDULE = "work_schedule";
    public static final String PROVIDE_REPO_DATA_FETCHER_DAGGER = "dagger_repo_fetcher";
    public static final String PROVIDE_REPO_DATA_FILE_FETCHER_DAGGER = "dagger_repo_file_fetcher";
    public static final String PROVIDE_REPO_DATA_DB_FETCHER_DAGGER = "dagger_repo_db_fetcher";
    /**
     * remove Generic Parameters to reduce invasion
     */
    public static final String PROVIDE_REPO_DATA_SOURCE_RX = "repo_source_rx";

    public static final String PROVIDE_REPO_DATA_SOURCE_GUAVA = "repo_source_guava";

    public static final String PROVIDE_CONTRIBUTOR_DATA_SOURCE_RX = "contributor_source_rx";


}
