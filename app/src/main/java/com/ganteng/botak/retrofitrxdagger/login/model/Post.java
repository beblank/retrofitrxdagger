package com.ganteng.botak.retrofitrxdagger.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adityahadiwijaya on 4/21/17.
 */

public class Post {

    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("body")
    @Expose
    String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title = " + title + '\'' +
                ", body = " + body + '\'' +
                "}";
    }
}
