package com.sparkideas.dao;

import com.sparkideas.model.BlogEntry;
import com.sparkideas.model.Comment;

/**
 * Created by Mallikarjuna on 10/23/2016.
 */
public interface CommentDao {
    boolean addComment(BlogEntry blog , Comment comment);
}
