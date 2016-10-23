package com.SparkYourIdeas.blog.dao;

import com.SparkYourIdeas.blog.model.BlogEntry;
import java.util.List;

public interface BlogDao {
    boolean addEntry(BlogEntry blogEntry);
    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);
    BlogEntry editCurrentBlog(String title, String body, String slug);
}
