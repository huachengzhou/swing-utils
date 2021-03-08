package com.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class HandleGoHuGo implements Serializable {
    private String title;
    private String description;
    private String date;
    private String author;
    private String lastmod;
    private boolean draft;
    private LinkedHashSet<String> tags = new LinkedHashSet<>(4) ;
    private LinkedHashSet<String> categories = new LinkedHashSet<>(4) ;

    private String vText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedHashSet<String> getTags() {
        return tags;
    }

    public void setTags(LinkedHashSet<String> tags) {
        this.tags = tags;
    }

    public LinkedHashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(LinkedHashSet<String> categories) {
        this.categories = categories;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public String getvText() {
        return vText;
    }

    public void setvText(String vText) {
        this.vText = vText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }
}
