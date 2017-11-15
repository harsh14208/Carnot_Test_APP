package com.example.harshvardhansingh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.harshvardhansingh.myapplication.Models.Comment;
import com.example.harshvardhansingh.myapplication.Models.Photo;
import com.example.harshvardhansingh.myapplication.Models.Post;
import com.example.harshvardhansingh.myapplication.Models.Todo;


/**
 * Created by Harsh Vardhan Singh on 15-11-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "DB_photos";

    private static final String TABLE_PHOTOS = "photos";
    private static final String TABLE_COMMENTS = "comments";
    private static final String TABLE_POSTS = "posts";
    private static final String TABLE_TODOS = "todos";
    private static final String ALBUM_ID = "albumId";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String THUMBNAILURL = "thumbnailUrl";
    private static final String POST_ID = "postId";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String BODY = "body";
    private static final String USER_ID = "userId";
    private static final String COMPLETED = "completed";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PHOTOS_TABLE = "CREATE TABLE " + TABLE_PHOTOS + "("
                + ALBUM_ID +" INTEGER,"+ ID + " INTEGER , " + TITLE + " TEXT, "+ URL + " TEXT, "
                + THUMBNAILURL + " TEXT" + ")";
        db.execSQL(CREATE_PHOTOS_TABLE);
        String CREATE_COMMENTS_TABLE = "CREATE TABLE " + TABLE_COMMENTS + "("
                + POST_ID +" INTEGER,"+ ID + " INTEGER , " + NAME + " TEXT, "+ EMAIL + " TEXT, "
                + BODY + " TEXT" + ")";
        db.execSQL(CREATE_COMMENTS_TABLE);
        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS + "("
                + USER_ID +" INTEGER,"+ ID + " INTEGER , " + TITLE + " TEXT, "+ BODY + " TEXT "
                + ")";
        db.execSQL(CREATE_POSTS_TABLE);
        String CREATE_TABLE_TODOS = "CREATE TABLE " + TABLE_TODOS + "("
                + USER_ID +" INTEGER,"+ ID + " INTEGER , " + TITLE + " TEXT, "+ COMPLETED + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_TODOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODOS);

        onCreate(db);
    }

    void addPhoto(Photo photo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ALBUM_ID, photo.getAlbumId());
        values.put(ID, photo.getId());
        values.put(TITLE,photo.getTitle());
        values.put(URL,photo.getUrl());
        values.put(THUMBNAILURL,photo.getThumbnailUrl());

        db.insert(TABLE_PHOTOS, null, values);
        db.close();
    }

    void addPost(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, post.getUserId());
        values.put(ID, post.getId());
        values.put(TITLE,post.getTitle());
        values.put(BODY,post.getBody());

        db.insert(TABLE_POSTS, null, values);
        db.close();
    }

    void addComment(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(POST_ID, comment.getPostId());
        values.put(ID, comment.getId());
        values.put(NAME,comment.getName());
        values.put(EMAIL,comment.getEmail());
        values.put(BODY,comment.getBody());

        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    void addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, todo.getUserId());
        values.put(ID, todo.getId());
        values.put(TITLE,todo.getTitle());
        values.put(COMPLETED,todo.getCompleted());

        db.insert(TABLE_TODOS, null, values);
        db.close();
    }

    public int getPHOTOSCount() {
        int count = 0;
            String countQuery = "SELECT  * FROM " + TABLE_PHOTOS;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            if(cursor != null && !cursor.isClosed()){
                count = cursor.getCount();
                cursor.close();
            }
            db.close();
            return count;

    }
    public int getCOMMENTSCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_COMMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;

    }
    public int getPOSTSCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_POSTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;

    }
    public int getTODOSCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_TODOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;

    }

}
