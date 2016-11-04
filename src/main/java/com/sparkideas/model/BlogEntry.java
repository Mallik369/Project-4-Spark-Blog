package com.sparkideas.model;
import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogEntry {
    private static int blogcount;
    private String slug;
    private Slugify slugify = new Slugify();
    private String title;
    private String body;
    private Date   date;
    private List<Comment> comments = new ArrayList<>();;

    public BlogEntry(String title, String body) {
        blogcount++;
        this.title = title;
        this.body = body;
        date  = new Date();
        slug = slugify.slugify(title+-blogcount);

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

    public void setSlug(String slug1) {
        this.slug = slug1;
    }

    public List<Comment> getComments() {return comments;}

    public void setTitle(String title) { this.title = title; }

    public void setBody(String body) {this.body = body;}

}
