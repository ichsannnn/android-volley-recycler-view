package dev.ska.volleyrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url = "https://api.myjson.com/bins/w86a";
    RecyclerView recyclerView;
    List<NewsFeeds> feedsList = new ArrayList<NewsFeeds>();
    MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(this, feedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        queue = NetworkController.getInstance(this).getRequestQueue();

        JsonArrayRequest newsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        NewsFeeds feeds = new NewsFeeds(obj.getString("title"), obj.getString("content"), obj.getString("image"), obj.getInt("ratebar"));
                        feedsList.add(feeds);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        adapter.notifyItemChanged(i);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });

        queue.add(newsReq);
    }
}
