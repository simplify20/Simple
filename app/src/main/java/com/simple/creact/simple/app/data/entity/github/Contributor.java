package com.simple.creact.simple.app.data.entity.github;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class Contributor {


    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }

}
