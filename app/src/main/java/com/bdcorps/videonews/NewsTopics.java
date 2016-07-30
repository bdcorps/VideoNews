package com.bdcorps.videonews;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

public class NewsTopics extends AppCompatActivity {

    GridView topicsGrid;
    public static String[] topicNames = {
            "Home",
            "Opinion",
            "World",
            "National",
            "Politics",
            "Upshot",
            "New York Region",
            "Business",
            "Technology",
            "Science",
            "Health",
            "Sports",
            "Arts",
            "Books",
            "Movies",
            "Theater",
            "Sunday Review",
            "Fashion",
            "T Magazine",
            "Food",
            "Travel",
            "Magazine",
            "Real Estate",
            "Automobiles",
            "Obituaries",
            "Insider"};
    public static int[] topicLogos = {
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_opinion_white_24dp,
            R.drawable.ic_world_white_24dp,
            R.drawable.ic_national_white_24dp,
            R.drawable.ic_politics_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_cast_light,
            R.drawable.ic_business_white_24dp,
            R.drawable.ic_technology_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_health_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_arts_white_24dp,
            R.drawable.ic_books_white_24dp,
            R.drawable.ic_movies_white_24dp,
            R.drawable.ic_theater_white_24dp,
            R.drawable.ic_sundayreview_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_cast_light,
            R.drawable.ic_food_white_24dp,
            R.drawable.ic_travel_white_24dp,
            R.drawable.ic_magazine_white_24dp,
            R.drawable.ic_realestate_white_24dp,
            R.drawable.ic_automobiles_white_24dp,
            R.drawable.ic_cast_light,
            R.drawable.ic_insider_white_24dp,
            R.drawable.ic_cast_light};

    public static String[] topicColors = {
            "blue_grey_500",
            "green_500",
            "blue_500",
            "red_500",
            "amber_500",
            "teal_500",
            "grey_800",
            "green_A400",
            "light_blue_600",
            "indigo_500",
            "red_800",
            "purple_500",
            "yellow_500",
            "brown_500",
            "red_500",
            "blue_grey_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "pink_500",
            "grey_500"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_topics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        topicsGrid = (GridView) findViewById(R.id.topics_gridview);
        topicsGrid.setAdapter(new TopicsAdapter(getBaseContext(), topicNames, topicLogos, topicColors));
    }

}
