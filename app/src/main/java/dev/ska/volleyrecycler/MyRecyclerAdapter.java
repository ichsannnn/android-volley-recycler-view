package dev.ska.volleyrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by skadevz on 25/12/17.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<NewsFeeds> feedsList;
    private Context context;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(Context context, List<NewsFeeds> feedsList) {
        this.context = context;
        this.feedsList = feedsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.singleitem_recyclerview, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsFeeds feeds = feedsList.get(position);
        holder.title.setText(feeds.getFeedName());
        holder.content.setText(feeds.getContent());
        holder.imageview.setImageUrl(feeds.getImgURL(), NetworkController.getInstance(context).getImageLoader());
        holder.ratingbar.setProgress(feeds.getRating());
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView content, title;
        private NetworkImageView imageview;
        private ProgressBar ratingbar;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_view);
            content = itemView.findViewById(R.id.content_view);
            imageview = itemView.findViewById(R.id.thumbnail);
            ratingbar = itemView.findViewById(R.id.ratingbar_view);
            ratingbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Rated By User: " + feedsList.get(getAdapterPosition()).getRating(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
