package com.example.ymsandroid.fragment;

public class ToDoList {
    private String listTitle;
    private String listCategory;
    private String listDate;

    public ToDoList(String listTitle, String listCategory, String listDate) {
        this.listTitle = listTitle;
        this.listCategory = listCategory;
        this.listDate = listDate;
    }

    public String getTvListTitle() {
        return listTitle;
    }

    public void setTvListTitle(String tvListTitle) {
        this.listTitle = listTitle;
    }

    public String getTvListCategory() {
        return listCategory;
    }

    public void setTvListCategory(String listCategory) {
        this.listCategory = listCategory;
    }

    public String getTvListDate() {
        return listDate;
    }

    public void setTvListDate(String listDate) {
        this.listDate = listDate;
    }
}
