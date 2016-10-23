package com.SparkYourIdeas.blog.dao;

import com.SparkYourIdeas.blog.Exception.NotFoundException;
import com.SparkYourIdeas.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mallikarjuna on 10/21/2016.
 */
public class BlogDaoImpl implements BlogDao {

    List<BlogEntry> mBlogsList; //Blogs List in Website

    public BlogDaoImpl() {
        mBlogsList = new ArrayList<>();
    }
    //Add new Blog to Website to mBlogsList
    public boolean addEntry(BlogEntry blogEntry) {
        return mBlogsList.add(blogEntry);

    }
    // List all the Blogs in Website (mBlogList)
    @Override
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<>(mBlogsList);
    }

    //To obtain Blog by Url Slug
    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return mBlogsList.stream()
                .filter(Blog -> Blog.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
    //To Edit Current Blog
    @Override
    public BlogEntry editCurrentBlog(String title, String body, String slug) {
        BlogEntry blogEntry = findEntryBySlug(slug);
        blogEntry.setTitle(title);
        blogEntry.setBody(body);
        return blogEntry;

    }
}
