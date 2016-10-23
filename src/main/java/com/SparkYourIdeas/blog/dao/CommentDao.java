package com.SparkYourIdeas.blog.dao;

import com.SparkYourIdeas.blog.model.BlogEntry;
import com.SparkYourIdeas.blog.model.Comment;

/**
 * Created by Mallikarjuna on 10/23/2016.
 */
public interface CommentDao {
    boolean addComment(BlogEntry blog ,Comment comment);
}
