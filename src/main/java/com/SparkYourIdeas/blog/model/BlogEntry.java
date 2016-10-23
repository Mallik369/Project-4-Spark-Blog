package com.SparkYourIdeas.blog.model;
import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogEntry {
    private String slug;
    private String title;
    private String body;
    private Date   date;
    private List<Comment> comments = new ArrayList<>();;

    public BlogEntry(String title, String body) {
        this.title = title;
        this.body = body;
        date  = new Date();
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    public Date getDate() {
        return date;
    }

    public String getBody() {return body;}

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public List<Comment> getComments() {return comments;}

    public void setTitle(String title) { this.title = title; }

    public void setBody(String body) {this.body = body;}

}
