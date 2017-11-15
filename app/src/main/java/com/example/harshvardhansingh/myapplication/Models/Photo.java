package com.example.harshvardhansingh.myapplication.Models;


/**
 * Created by Harsh Vardhan Singh on 15-11-2017.
 */
public class Photo {

    public int albumId;
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;


    public Photo() {
        super();
    }

    public int getId()
    {
     return this.id;
    }

    public int getAlbumId()
    {
        return this.albumId;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getUrl()
    {
        return this.url;
    }
    public String getThumbnailUrl()
    {
        return this.thumbnailUrl;
    }


    public Photo(int albumId,int id,String title,String url,String thumbnailUrl) {
        super();
        this.albumId = albumId ;
        this.id=id;
        this.title=title;
        this.url=url;
        this.thumbnailUrl=thumbnailUrl;
    }
}
