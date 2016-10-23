package com.SparkYourIdeas.blog.dao;

import com.SparkYourIdeas.blog.model.BlogEntry;
import com.SparkYourIdeas.blog.model.Comment;

import java.util.List;

/**
 * Created by Mallikarjuna on 10/23/2016.
 */
public class CommentDaoImpl implements CommentDao{
    @Override
    public boolean addComment(BlogEntry blog, Comment comment) {
        List<Comment> comments = blog.getComments();
        boolean added = comments.add(comment);
        //comments.sort( (c1, c2) -> -c1.getCreationTime().compareTo(c2.getCreationTime()));
        return added;
    }
}
