package com.qsoft.OnlineDio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qsoft.OnlineDio.ImageCache.Image;
import com.qsoft.OnlineDio.Model.Feed;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;

/**
 * User: Dell 3360
 * Date: 10/20/13
 * Time: 10:01 AM
 */
public class FeedsAdapterCustom extends ArrayAdapter<Feed>
{
    private ImageView ivAvatar;
    private TextView tvTitle;
    private TextView tvUserName;
    private TextView tvLike;
    private TextView tvComment;
    private TextView tvTime;
    private final ArrayList<Feed> feeds;
    private final Context context;

    public FeedsAdapterCustom(Context context, int tvResourceId, ArrayList<Feed> feeds)
    {
        super(context, tvResourceId, feeds);
        this.feeds = feeds;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.home_items_layout, null);
        }
        Feed feed = feeds.get(position);
        setUpViewFindByID(v);
        if (feed != null)
        {
            Image i=new Image(context);
            i.DisplayImage(feed.getAvatar(),ivAvatar);
            tvTitle.setText(feed.getTitle());
            tvUserName.setText(feed.getUsername());
            tvLike.setText("Like:"+feed.getLikes());
            tvComment.setText("Comment:"+feed.getComments()+"");
            tvTime.setText(feed.getCreated_at());
        }
        return v;
    }

    private void setUpViewFindByID(View v)
    {
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvUserName = (TextView) v.findViewById(R.id.tvName);
        tvLike = (TextView) v.findViewById(R.id.tvLike);
        tvComment = (TextView) v.findViewById(R.id.tvComment);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        ivAvatar=(ImageView)v.findViewById(R.id.HomeFeed_ivAvatar);
    }
}
