package com.cameronwhite.dejsontest;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cameron on 10/25/2017.
 */

public class ListViewAdapter  extends RecyclerView.Adapter<ListViewAdapter.ViewHolder>{

    private JSONArray jsonArray;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView descriptionTextView, authorTextView;
        public ImageView posterImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descTextView);
            authorTextView = (TextView) itemView.findViewById(R.id.authorTextView);
            posterImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ListViewAdapter(JSONArray arr) {
        this.jsonArray = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder h = new ViewHolder(v);

        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JSONObject jObject = null;

        try {
            jObject = jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jObject != null)
        {
            try
            {
                holder.descriptionTextView.setText(jObject.getString("title"));

                String authorText = "";
                if (jObject.has("author"))
                {
                    authorText = "Author: " + jObject.getString("author");
                }
                holder.authorTextView.setText(authorText);

                if (jObject.has("imageURL"))
                {
                    String url = jObject.getString("imageURL");
                    Picasso.with(holder.posterImageView.getContext())
                            .load(url)
                            .into(holder.posterImageView);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }
}
