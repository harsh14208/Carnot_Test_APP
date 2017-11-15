package com.example.harshvardhansingh.myapplication.Models;

/**
 * Created by Harsh Vardhan Singh on 15-11-2017.
 */
public class Comment {

    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;


    public Comment() {
        super();
    }
    public int getId()
    {
        return this.id;
    }

    public int getPostId()
    {
        return this.postId;
    }
    public String getName()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getBody()
    {
        return this.body;
    }


    public Comment(int postId,int id,String name,String email,String body) {
        super();
        this.postId = postId;
        this.id=id;
        this.name=name;
        this.email=email;
        this.body=body;
    }
}
