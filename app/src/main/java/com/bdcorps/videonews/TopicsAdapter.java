package com.bdcorps.videonews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gdesi on 2016-07-26.
 */
public class TopicsAdapter extends BaseAdapter{

        String [] topicNames;
        Context context;
        int [] logoIDs;
        private static LayoutInflater inflater=null;
        public TopicsAdapter(Context context, String[] topicNames, int[] topicLogos) {
            // TODO Auto-generated constructor stub
            this.topicNames =topicNames;
            this.context=context;
            logoIDs =topicLogos;
            inflater = (LayoutInflater)context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return topicNames.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView tv;
            ImageView img;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder=new Holder();
            View rowView;

            rowView = inflater.inflate(R.layout.topic_item_frag, null);
            holder.tv=(TextView) rowView.findViewById(R.id.textView1);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

            holder.tv.setText(topicNames[position]);
            holder.img.setImageResource(logoIDs[position]);

            rowView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked "+ topicNames[position], Toast.LENGTH_LONG).show();
                }
            });

            return rowView;
        }

    }
