package com.example.harshvardhansingh.myapplication.Models;

/**
 * Created by Harsh Vardhan Singh on 15-11-2017.
 */

public class Post  {

    public int userId;
    public int id;
    public String title;
    public String body;


    public Post() {
        super();
    }

    public int getId()
    {
        return this.id;
    }

    public int getUserId()
    {
        return this.userId;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getBody()
    {
        return this.body;
    }

    public Post(int userId,int id,String title,String body ) {
        super();
        this.userId = userId;
        this.id=id;
        this.title=title;
        this.body=body;
    }
}
