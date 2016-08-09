package com.bdcorps.videonews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TopicsAdapter extends BaseAdapter {

    String[] topicNames;
    String[] topicCodes;
    Context context;
    int[] topicLogos;
    String[] topicColors;

    private static LayoutInflater inflater = null;

    public TopicsAdapter(Context context, String[] topicNames, int[] topicLogos, String[] topicColors, String[] topicCodes) {
        this.topicNames = topicNames;
        this.topicCodes = topicCodes;
        this.context = context;
        this.topicLogos = topicLogos;
        this.topicColors = topicColors;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return topicNames.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        CardView topicCardView;
        TextView topicName;
        ImageView topicLogo;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.topic_item_fragment, null);
        holder.topicName = (TextView) rowView.findViewById(R.id.topic_name);
        holder.topicLogo = (ImageView) rowView.findViewById(R.id.topic_logo);
        holder.topicCardView = (CardView) rowView.findViewById(R.id.topic_cardview);

        holder.topicName.setText(topicNames[position]);
        holder.topicLogo.setImageResource(topicLogos[position]);

        final int cd_back_color = context.getResources().getIdentifier(topicColors[position], "color", context.getPackageName());

        holder.topicCardView.setCardBackgroundColor(ContextCompat.getColor(context, cd_back_color));

        Animation fabScaleAnim = AnimationUtils.loadAnimation(context, R.anim.card_reveal);
        holder.topicCardView.startAnimation(fabScaleAnim);


        holder.topicCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                myIntent.putExtra("topicCode", topicCodes[position]);
                v.getContext().startActivity(myIntent);

                Toast.makeText(context, "You Clicked " + topicNames[position], Toast.LENGTH_LONG).show();
            }
        });

        holder.topicCardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Intent myIntent = new Intent(v.getContext(), NewsPagerActivity.class);
                myIntent.putExtra("topicCode", topicCodes[position]);
                v.getContext().startActivity(myIntent);

                Toast.makeText(context, "You Clicked " + topicNames[position], Toast.LENGTH_LONG).show();
                return true;
            }
        });

        return rowView;
    }

}
