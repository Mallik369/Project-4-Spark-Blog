package com.sparkideas;

import com.github.slugify.Slugify;
import com.sparkideas.Exception.NotFoundException;
import com.sparkideas.dao.BlogDao;
import com.sparkideas.dao.BlogDaoImpl;
import com.sparkideas.dao.CommentDao;
import com.sparkideas.dao.CommentDaoImpl;
import com.sparkideas.model.BlogEntry;
import com.sparkideas.model.Comment;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
/**
 * Created by Mallikarjuna on 10/21/2016.
 */
public class Main {
    private static final BlogDao dao = new BlogDaoImpl();
    private static final CommentDao commentDao = new CommentDaoImpl();
    private static final HandlebarsTemplateEngine hbts = new HandlebarsTemplateEngine();
    private static final String FLASH_MESSAGE_KEY = "flash_message"; //display flash Message to user
    private static final String origin=""; //obtain origin path before authentication
    public static void main(String[] args) {

        staticFileLocation("/public"); //path location of css Files

        //Handler to set cookie value to password
        before((req,res) -> {
            if(req.cookie("password") != null) {
                req.attribute("password",req.cookie("password"));
            }
        });
        //Handler to authenticate user before adding Blog
        before("/new",(req, res) -> {
            if (req.attribute("password") == null) {
                setFlashMessage(req,"Sign-in to Add New Blog");
                setOrigin(req,req.uri());
                res.redirect("/password");
                halt();
            }
        });
        //Handler to authenticate user before editing blog
        before("/edit/:slug",(req,res) -> {
            if (req.attribute("password") == null) {
                setFlashMessage(req,"Sign-in to Edit Current Blog");
                setOrigin(req,req.uri());
                res.redirect("/password");
                halt();
            }

        });
        //Router to display home page on obtaining (input : localhost:4567) from browser
        get("/",(req,res) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("entries",dao.findAllEntries());
            return new ModelAndView(model,"index.hbs");
        }, hbts);
        //Router to display new.hbs page o
        get("/new",(req,res) -> {
            Map<String,String> model = new HashMap<>();
            model.put("flashMessage",captureFlashMessage(req));
            return new ModelAndView(model,"new.hbs");
        },hbts);
        //Router to display password.hbs to User before adding new blog or editing blog
        get("/password",(req,res) -> {
            Map<String,String> model = new HashMap<>();
            model.put("flashMessage",captureFlashMessage(req));
            return new ModelAndView(model,"password.hbs");
        },hbts);
        //Router to accept or reject password
        post("/password",(req,res) -> {
            //reject : On wrong password display message on browser to re-enter password
            if(!req.queryParams("password").equals("admin")) {
                setFlashMessage(req,"Your Password is InValid!!!");
                res.redirect("/password");
             //accept : On Correct password redirect to add new Blog or edit Blog
            }else {
                String path = getOrigin(req);
                req.session().removeAttribute(origin);
                res.cookie("password","admin");
                res.redirect(path);
            }
            return null;
        });
        //On Successful Submission Router to add new Blog to Website
        post("/",(req,res) -> {
            String title = req.queryParams("title");
            String body = req.queryParams("entry");
            //Accepts new Blog
            //Add New Blog to List of Entries
            if(!title.isEmpty()) {
                BlogEntry blogEntry = new BlogEntry(title,body);
                dao.addEntry(blogEntry);
                res.redirect("/");
                //Rejects new blog without Title and Display message to User
            }else {
                setFlashMessage(req,"Blog Title can't be Empty"); //Display Flash Message to User
                res.redirect("/new");
            }
            return null;
        });
        //Router to display detail.hbs page to display detailed blog content
        get("/entry/:slug",(req,res) -> {
            Map<String,Object> model = new HashMap<>();
            BlogEntry blogEntry = dao.findEntryBySlug(req.params(":slug"));
            model.put("entry",blogEntry);
            model.put("flashMessage",captureFlashMessage(req));
            return new ModelAndView(model,"detail.hbs");
        },hbts);
        //Router to display edit.hbs to edit current blog
        get("/edit/:slug",(req,res) -> {
            Map<String,Object> model = new HashMap<>();
            BlogEntry blogEntry = dao.findEntryBySlug(req.params(":slug"));
            model.put("flashMessage",captureFlashMessage(req));
            model.put("entry",blogEntry);
            return new ModelAndView(model,"edit.hbs");
        },hbts);
        //Router to display Enhanced Content of Blog after submitting by user
        post("/entry/:slug",(req,res) -> {
            Slugify slugify = new Slugify();
            String modifiedTitle = req.queryParams("title"); //modified blogtitle by user
            String modifiedBody  = req.queryParams("entry"); //modified blogbody by user
            String modifiedslug = slugify.slugify(modifiedTitle); //modified slug based based changes in title
            String slug  = req.params("slug");
            //if the modified_title is empty blog entry is rejected
            if(!modifiedTitle.isEmpty()) {
                Map<String, Object> model = new HashMap<>();
                BlogEntry blogEntry = dao.editCurrentBlog(modifiedTitle
                        ,modifiedBody,modifiedslug);
                model.put("entry", blogEntry);
                res.redirect("/entry/"+modifiedslug);
                return new ModelAndView(model, "detail.hbs");
            } else {
               setFlashMessage(req,"Title can't be Empty"); //Display Flash Message to User
                res.redirect("/entry/"+slug);
            }
            return null;
        },hbts);
        //Adding comment to Blog
        post("/entry/:slug/comment",(req,res) -> {
            String slug = req.params("slug");
            BlogEntry blogEntry = dao.findEntryBySlug(slug);
            String user = req.queryParams("name");
            String comment = req.queryParams("comment");
            //Empty user name is set to Anonymous
            if(user.isEmpty()) {
                user = "Anonymous";
            }
            //Empty Content is not accepted
            if (!comment.isEmpty()){
                Comment comment1 = new Comment(user,comment);
                commentDao.addComment(blogEntry,comment1);
                res.redirect("/entry/" + slug);
            } else{
                setFlashMessage(req,"Comment Can't be Empty"); //Display Flash Message to User
                res.redirect("/entry/"+slug);
            }
            return null;
        },hbts);
        //Router to display not-found.hbs page
        exception(NotFoundException.class, (exc, req, res) -> {
            res.status(404);
            String html = hbts.render(new ModelAndView(null, "not-found.hbs"));
            res.body(html);
        });
        DefaultBlogEntries();
    }

    private static void setOrigin(Request req, String path) {
        req.session().attribute(origin,path);
    }
    private static String getOrigin(Request req) {
        return (String) req.session().attribute(origin);
    }

    private static void setFlashMessage(Request req,String message) {
        req.session().attribute(FLASH_MESSAGE_KEY,message);
    }
    private static String getFlashMessage(Request req) {
        if(req.session(false) == null) {
            return null;
        }
        if(!req.session().attributes().contains(FLASH_MESSAGE_KEY)) {
            return null;
        }
        return (String) req.session().attribute(FLASH_MESSAGE_KEY);
    }
    private static String captureFlashMessage(Request req) {
        String message = getFlashMessage(req);
        if(message !=null) {
            req.session().removeAttribute(FLASH_MESSAGE_KEY);
        }
        return message;
    }
    //Default Blog Entries
    private static void DefaultBlogEntries() {
        dao.addEntry(new BlogEntry("The best day I’ve ever had"
                ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque."));
        dao.addEntry(new BlogEntry("The absolute worst day I’ve ever had"
                ,"Cras egestas ac ipsum in posuere. Fusce suscipit, libero id malesuada placerat, orci velit semper metus, quis pulvinar sem nunc vel augue."));
        dao.addEntry(new BlogEntry("That time at the mall"
                ,"Etiam pretium, sapien non fermentum consequat, dolor augue gravida lacus, non accumsan lorem odio id risus. Vestibulum pharetra tempor molestie."));
    }
}
