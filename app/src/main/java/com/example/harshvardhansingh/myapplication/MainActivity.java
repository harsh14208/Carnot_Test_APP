package com.example.harshvardhansingh.myapplication;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshvardhansingh.myapplication.Models.Comment;
import com.example.harshvardhansingh.myapplication.Models.Photo;
import com.example.harshvardhansingh.myapplication.Models.Post;
import com.example.harshvardhansingh.myapplication.Models.Todo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable mTimeRunnable = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
            timestampTextView.setText(simpleDateFormat.format(new Date()));

            handler.postDelayed(mTimeRunnable, 1);
        }
    };

    Context context;
    TextView postStart,postStop,postStartSave,postStopSave,photoStart,photoStop,photoStartSave,photoStopSave,commentStart,commentStop,commentStartSave,commentStopSave, todoStart,todoStop,todoStartSave,todoStopSave,timestampTextView;

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(mTimeRunnable, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(mTimeRunnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyLog.DEBUG = false;
        context = getApplicationContext();
        timestampTextView = (TextView) findViewById(R.id.timestampTextView);
        postStart = (TextView) findViewById(R.id.post_start);
        postStop  = (TextView) findViewById(R.id.post_stop);
        postStartSave = (TextView) findViewById(R.id.post_start_save);
        postStopSave = (TextView) findViewById(R.id.post_stop_save);

        photoStart = (TextView) findViewById(R.id.photo_start);
        photoStop = (TextView) findViewById(R.id.photo_stop);
        photoStartSave = (TextView) findViewById(R.id.photo_start_save);
        photoStopSave = (TextView) findViewById(R.id.photo_stop_save);

        commentStart = (TextView) findViewById(R.id.comment_start);
        commentStop = (TextView) findViewById(R.id.comment_stop);
        commentStartSave = (TextView) findViewById(R.id.comment_start_save);
        commentStopSave = (TextView) findViewById(R.id.comment_stop_save);

        todoStart = (TextView) findViewById(R.id.todo_start);
        todoStop = (TextView) findViewById(R.id.todo_stop);
        todoStartSave = (TextView) findViewById(R.id.todo_start_save);
        todoStopSave = (TextView) findViewById(R.id.todo_stop_save);

        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);
        Button b3 = (Button) findViewById(R.id.b3);
        Button b4 = (Button) findViewById(R.id.b4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePosts(context);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePhotos(context);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveComments(context);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveTodos(context);
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FetchData(context);
            }
        }, 5000);
    }

    public void FetchData(final Context context) {
        SaveComments(context);
        SavePhotos(context);
        SavePosts(context);
        SaveTodos(context);
    }

    public void SaveComments(final Context context) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                final String currentTime = dateFormat.format(new Date());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commentStart.setText("Comment Start Time : "+currentTime);

                    }
                });
                String url = "https://jsonplaceholder.typicode.com/comments";
                RequestQueue queue = Volley.newRequestQueue(context);
                JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(final JSONArray response) {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                        final String currentTime4 = dateFormat.format(new Date());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                commentStop.setText("Comment Stop Time:    "+currentTime4);
                                            }
                                        });
                                        DatabaseHandler db = new DatabaseHandler(context);
                                        try {
                                            final String currentTime5 = dateFormat.format(new Date());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    commentStartSave.setText("Comment Start Save Time: "+currentTime5);
                                                }
                                            });

                                            for (int i = 0; i < response.length(); i++) {
                                                JSONObject jsonobject = response.getJSONObject(i);
                                                int postId = jsonobject.getInt("postId");
                                                int id = jsonobject.getInt("id");
                                                String name = jsonobject.getString("name");
                                                String email = jsonobject.getString("email");
                                                String body = jsonobject.getString("body");
                                                Comment comment = new Comment(postId, id, name, email, body);
                                                db.addComment(comment);
                                            }
                                            final String currentTime3 = dateFormat.format(new Date());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    commentStopSave.setText("Comment Stop Save Time: "+currentTime3);
                                                }
                                            });

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(jsonObjReq);
            }
        }.start();

    }

    public void SavePhotos(final Context context) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String url = "https://jsonplaceholder.typicode.com/photos";
                RequestQueue queue = Volley.newRequestQueue(context);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                final String currentTime = dateFormat.format(new Date());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        photoStart.setText("Photo Start Time :                  "+currentTime);
                    }
                });
                JsonArrayRequest jsonObjReq1 = new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(final JSONArray response) {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                        final String currentTime = dateFormat.format(new Date());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                photoStop.setText("Photo Stop Time :                "+currentTime);
                                            }
                                        });

                                        DatabaseHandler db = new DatabaseHandler(context);
                                        try {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    photoStartSave.setText("Photo Save Start Time  :   "+ currentTime);
                                                }
                                            });

                                            for (int i = 0; i < response.length(); i++) {
                                                JSONObject jsonobject = response.getJSONObject(i);
                                                int albumId = jsonobject.getInt("albumId");
                                                int id = jsonobject.getInt("id");
                                                String title = jsonobject.getString("title");
                                                String url = jsonobject.getString("url");
                                                String thumbnailUrl = jsonobject.getString("thumbnailUrl");
                                                Photo photo = new Photo(albumId, id, title, url, thumbnailUrl);
                                                db.addPhoto(photo);
                                            }
                                            final String currentTime2 = dateFormat.format(new Date());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    photoStopSave.setText("Photo Save Stop Time :  "+currentTime2);
                                                }
                                            });
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(jsonObjReq1);
            }
        }.start();

    }

    public void SaveTodos(final Context context) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "https://jsonplaceholder.typicode.com/todos";
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                final String currentTime = dateFormat.format(new Date());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        todoStart.setText("Todo Start Time  :              "+currentTime);
                    }
                });

                JsonArrayRequest jsonObjReq2 = new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(final JSONArray response) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                final String currentTime = dateFormat.format(new Date());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        todoStop.setText("Todo Stop Time  :                "+currentTime);
                                    }
                                });
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                        final String currentTime = dateFormat.format(new Date());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                todoStartSave.setText("Todo Start Save Time  :          "+currentTime);
                                            }
                                        });
                                        DatabaseHandler db = new DatabaseHandler(context);
                                        try {
                                            for (int i = 0; i < response.length(); i++) {
                                                JSONObject jsonobject = response.getJSONObject(i);
                                                int userId = jsonobject.getInt("userId");
                                                int id = jsonobject.getInt("id");
                                                String title = jsonobject.getString("title");
                                                Boolean completed = jsonobject.getBoolean("completed");
                                                Todo todo = new Todo(userId, id, title, completed);
                                                db.addTodo(todo);
                                            }
                                           final String currentTime2 = dateFormat.format(new Date());
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    todoStopSave.setText("Todo Stop Save Time :        "+currentTime2);
                                                }
                                            });
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
                queue.add(jsonObjReq2);
            }
        }.start();

    }

    public void SavePosts(final Context context) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                final String currentTime = dateFormat.format(new Date());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postStart.setText("Post Start Time  :                   "+currentTime);
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "https://jsonplaceholder.typicode.com/posts";

                JsonArrayRequest jsonObjReq3 = new JsonArrayRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(final JSONArray response) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                final String currentTime = dateFormat.format(new Date());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        postStop.setText("Post Stop Time  :               "+currentTime);
                                    }
                                });
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                                        final String currentTime = dateFormat.format(new Date());
                                        DatabaseHandler db = new DatabaseHandler(context);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                postStartSave.setText("Post Start Save Time  :     "+currentTime);
                                            }
                                        });
                                        try {
                                            for (int i = 0; i < response.length(); i++) {
                                                JSONObject jsonobject = response.getJSONObject(i);
                                                int userId = jsonobject.getInt("userId");
                                                int id = jsonobject.getInt("id");
                                                String title = jsonobject.getString("title");
                                                String body = jsonobject.getString("body");
                                                Post post = new Post(userId, id, title, body);
                                                db.addPost(post);
                                            }
                                           final String currentTime3 = dateFormat.format(new Date());
                                           runOnUiThread(new Runnable() {
                                               @Override
                                               public void run() {
                                                   postStopSave.setText("Post Stop Save Time   :     "+currentTime3);
                                               }
                                           });
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(jsonObjReq3);
            }
        }.start();

    }
}
