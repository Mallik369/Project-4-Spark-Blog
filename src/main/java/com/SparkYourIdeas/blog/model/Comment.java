package com.SparkYourIdeas.blog.model;

import java.util.Date;

public class Comment {
    private String user;
    private String content;
    private Date date;

    public Comment(String user, String comment) {
        this.user = user;
        this.content = comment;
        date = new Date();
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
