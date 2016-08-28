/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bdcorps.videonews;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";
public ArrayList<String> newsTitles;
    public ArrayList<String> newsAbstracts;
    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;
    JSONArray a;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);

        int ind = mPageNumber;
        a = Config.initNewsRequest("home");
        newsTitles = new ArrayList<String>();
        newsAbstracts = new ArrayList<String>();

        int mult = 0;
        JSONObject b = null;


        for (int i=0; i < 5; i++)
        {
            ind += 1;
            try {
                b = (JSONObject) a.get(ind);
                mult = b.getJSONArray("multimedia").length();
            } catch (JSONException e) {
                e.printStackTrace();
            }

           while (mult<5)
        {
            ind += 1;
            try {
               b = (JSONObject) a.get(ind);
                mult = b.getJSONArray("multimedia").length();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            try {
                newsTitles.add(b.getString("title"));
                newsAbstracts.add(b.getString("abstract"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);



        TextView tv_title = (TextView) rootView.findViewById(R.id.text1);
       TextView tv_abstract = (TextView) rootView.findViewById(R.id.abstract1);

        tv_title.setText(newsTitles.get(mPageNumber));
        tv_abstract.setText(newsAbstracts.get(mPageNumber));
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
