package com.bdcorps.videonews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsItemFragment extends Fragment {

    Palette palette;
    int default_code=0x000000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_item_fragment, container, false);

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.new_york);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            palette = Palette.from(myBitmap).generate();
        }

        int vibrant = palette.getVibrantColor( default_code);
        int vibrantLight = palette.getLightVibrantColor( default_code);
        int vibrantDark = palette.getDarkVibrantColor( default_code);
        int muted = palette.getMutedColor( default_code);
        int mutedLight = palette.getLightMutedColor( default_code);
        int mutedDark = palette.getDarkMutedColor( default_code);

        ImageView newsImageView = (ImageView) view.findViewById(R.id.news_imageView);
        TextView newsHeader = (TextView) view.findViewById(R.id.news_header);
        TextView newsByline = (TextView) view.findViewById(R.id.news_byline);

        newsHeader.setBackgroundColor(mutedDark);

        return view;
    }
}