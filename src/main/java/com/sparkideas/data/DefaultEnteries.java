package com.sparkideas.data;

import com.sparkideas.dao.BlogDao;
import com.sparkideas.dao.BlogDaoImpl;
import com.sparkideas.model.BlogEntry;

import java.util.Arrays;
import java.util.List;

public class DefaultEnteries {
    private static final List<BlogEntry> DEFAULT_BLOGS = Arrays.asList(

        new BlogEntry("The best day I’ve ever had","Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Nunc ut rhoncus felis vel tincidunt neque."),
        new BlogEntry("The Worst day I’ve ever had","Cras egestas ac ipsum in posuere. " +
                "Fusce suscipit, libero id malesuada placerat, orci velit semper " +
                "metus,quis pulvinar sem nunc vel augue."),
        new BlogEntry("That time at the mall","Etiam pretium, sapien non fermentum consequat, " +
                "dolor augue gravida lacus, non accumsan lorem odio id risus. Vestibulum pharetra tempor molestie.")

    );
    public void DefaultBlogEntries(BlogDao dao) {
        for (BlogEntry blogEntry: DEFAULT_BLOGS) {
            dao.addEntry(blogEntry);
        }
    }
}
