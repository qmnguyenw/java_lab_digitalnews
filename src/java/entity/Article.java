/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class Article {
    private int id;
    private String title;
    private String image;
    private String content;
    private Date uploadDate;
    private String author;

    public Article() {
    }

    public Article(int id, String title, String image, String content, Date uploadDate, String author) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.uploadDate = uploadDate;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortcut() {
        String shortcut = "";
        if (content.length() > 100) {
            shortcut = content.substring(0, 100) + "...";
        }
        else {
            shortcut = content;
        }
        return shortcut;
    }

    public String getFormatDate() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String monthStr = monthFormat.format(uploadDate).substring(0,3);
        return monthStr + " " + new SimpleDateFormat("dd yyyy - hh:mm aaa").format(uploadDate);
    }
    
    public String getAuthorLine() {
        return "By " + author + " | " + getFormatDate();
    }
}
