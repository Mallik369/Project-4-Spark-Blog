# Project-4-Spark-Blog

Blog using Spark is a Web Application is developed to apply and practice knowledge and skills of [Spark](http://sparkjava.com/) mirco framework for creating web application in Java 8 with minimal Effort.

## Technical Details

Java IDE : [IntelliJ IDEA](https://www.jetbrains.com/)   
Web FrameWork : [Sparkjava](http://sparkjava.com/)   
Programming Language : [Java](https://www.java.com/en/)   
Views and Templates : [Handle Bars](http://handlebarsjs.com/)   
Build Tool: [Gradle](https://gradle.org/)   
Mark Up : HTML5   
Design Pattern : [DAO](https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm)   
Dependencies : [SEO-Friendly URL'S with Slugify](https://github.com/slugify/slugify.git)   

## Implementation

* Add the Dependencies in *build.gradle* 
```
compile "com.github.slugify:slugify:2.1.7"
compile "com.sparkjava:spark-core:2.5"
compile "org.slf4j:slf4j-simple:1.7.21"
compile "com.sparkjava:spark-template-handlebars:2.3"
  ```
* Design  : Data Access Object Pattern


![Design of Blog ](https://github.com/Mallik369/Project-4-Spark-Blog/tree/master/src/main/resources/public/design/Blog.png)

### User Stories Implement Functionality of Blog Application

In this project, you will build your own blog using Spark. The main (index) page will list blog entry titles with a title and date/time
created. Each blog entry title will link to a detail page that displays the blog entry title, date, body, and comments, along with a 
comment form with that allows anonymous users to post comments. Comments will have a name and a body. You should include the ability to 
add or edit blog entries. The edit page should be password-protected and will give the ability for an admin to add or edit blog entries. 
Use CSS to make your blog reflect your personal style.

* Use the supplied mockup files to build a personal blog.
* Create model classes for blog entries and blog comments.
```
BlogEntry.java
Comment.java
```
* Create a DAO interface for data storage and access and implement it.
```
BlogDao
CommentDao
```
* Add necessary routes
```
get("/",(req,res)
get("/new",(req,res)
get("/password",(req,res)
post("/password",(req,res)
post("/",(req,res)
get("/entry/:slug",(req,res)
get("/edit/:slug",(req,res)
post("/entry/:slug",(req,res)
 post("/entry/:slug/comment",(req,res)
 
 ```
* Create index view as the homepage. This view contains a list of blog entries, which displays Title, Date/Time Created. 
Title should be hyperlinked to the detail page for each blog entry. Include a link to add an entry.

```
index.hbs
```
* Create detail page displaying blog entry and submitted comments. Detail page should also display a comment form with 
Name and Comment. Include a link to edit the entry.

```
detail.hbs
```
* Create a password page that requires a user to provide 'admin' as the username. This page should display before adding 
or editing a blog entry if there is no cookie present that has the value 'admin'.

```
password.hbs
```
* Create add/edit page that is blocked by a password page, using before filter.

```
add.hbs
edit.hbs
```
* Use CSS to style headings, font colors, blog entry container colors, body colors.

```
normalize.css
site.css
```
