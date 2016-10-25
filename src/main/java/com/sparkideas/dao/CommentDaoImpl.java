package com.sparkideas.dao;

import com.sparkideas.model.BlogEntry;
import com.sparkideas.model.Comment;

import java.util.List;

/**
 * Created by Mallikarjuna on 10/23/2016.
 */
public class CommentDaoImpl implements CommentDao{
    @Override
    public boolean addComment(BlogEntry blog, Comment comment) {
        List<Comment> comments = blog.getComments();
        boolean added = comments.add(comment);
        return added;
    }
}
