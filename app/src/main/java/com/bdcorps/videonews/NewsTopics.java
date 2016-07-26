package com.bdcorps.videonews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

public class NewsTopics extends AppCompatActivity {

    GridView topicsGrid;
    public static String[] topicNames = {
            "home",
            "opinion",
            "world",
            "national",
            "politics",
            "upshot",
            "nyregion",
            "business",
            "technology",
            "science",
            "health",
            "sports",
            "arts",
            "books",
            "movies",
            "theater",
            "sundayreview",
            "fashion",
            "tmagazine",
            "food",
            "travel",
            "magazine",
            "realestate",
            "automobiles",
            "obituaries",
            "insider"};
    public static int[] topicLogos = {
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_forum_white_24dp,
            R.drawable.ic_public_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_assessment_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_cast_light,
            R.drawable.ic_cast_light,
            R.drawable.ic_cast_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_topics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        topicsGrid = (GridView) findViewById(R.id.topics_gridview);
        topicsGrid.setAdapter(new TopicsAdapter(getBaseContext(), topicNames,topicLogos));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
