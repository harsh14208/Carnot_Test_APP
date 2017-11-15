package com.example.harshvardhansingh.myapplication.Models;

/**
 * Created by Harsh Vardhan Singh on 15-11-2017.
 */


public class Todo  {

    public int userId;
    public int id;
    public String title;
    public Boolean completed;


    public Todo() {
        super();
    }
    public int getUserId()
    {
        return this.userId;
    }
    public int getId()
    {
        return this.id;
    }
    public String getTitle()
    {
        return this.title;
    }
    public Boolean getCompleted()
    {
        return this.completed;
    }

    public Todo(int userId,int id,String title,Boolean completed) {
        super();
        this.userId = userId;
        this.id=id;
        this.title=title;
        this.completed=completed;
    }
}
