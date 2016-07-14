//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license.
//
// Microsoft Cognitive Services (formerly Project Oxford): https://www.microsoft.com/cognitive-services
//
// Microsoft Cognitive Services (formerly Project Oxford) GitHub:
// https://github.com/Microsoft/ProjectOxford-ClientSDK
//
// Copyright (c) Microsoft Corporation
// All rights reserved.
//
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED ""AS IS"", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package com.bdcorps.videonews;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.speech.tts.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity implements DownloadImageTask.AsyncResponse {
    // Note: Sign up at http://www.projectoxford.ai for the client credentials.
    public Synthesizer m_syn;
public int article = 0;

    public TextView text;
    public ImageView img;
    public ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textview);
       img = (ImageView) findViewById(R.id.imageview);
        img2 = (ImageView) findViewById(R.id.imageview2);

        if (getString(R.string.subscription_key).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        } else {

            if (m_syn == null) {
                // Create Text To Speech Synthesizer.
                m_syn = new Synthesizer("clientid", getString(R.string.subscription_key));
            }

            Toast.makeText(this, "If the wave is not played, please see the log for more information.", Toast.LENGTH_LONG).show();

            m_syn.SetServiceStrategy(Synthesizer.ServiceStrategy.AlwaysService);

            Voice v = new Voice("en-US", "Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)", Voice.Gender.Female, true);
            //Voice v = new Voice("zh-CN", "Microsoft Server Speech Text to Speech Voice (zh-CN, HuihuiRUS)", Voice.Gender.Female, true);
            m_syn.SetVoice(v, null);

            // Use a string for speech.
//            m_syn.SpeakToAudio(getString(R.string.tts_text));

            // Use SSML for speech.
            String textm = "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xml:lang=\"en-US\"><voice name=\"Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)\">You can also use SSML markup for text to speech.</voice></speak>";
            m_syn.SpeakSSMLToAudio(textm);
        }


        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               grabnews();
            }


        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(text.getText().toString());
            }
        });

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
article++;
                Toast.makeText(getBaseContext(), "This is "+ article,
                        Toast.LENGTH_LONG).show();
                grabnews();
            }
        });
    }

    public void grabnews(){
       int pos = article;
        try {

            ArrayList<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("api-key", "b71e715aa69145fd9d770f5df8b129c3"));
            ServerGETRequest sr = new ServerGETRequest();
            JSONObject json = sr.getJSON("https://api.nytimes.com/svc/topstories/v2/home.json", params);
            JSONArray a = json.getJSONArray("results");

            //  for (int i = 0; i<json.getInt("num_results");i++)
            {
                JSONObject b = (JSONObject) a.get(article);
                JSONArray c = b.getJSONArray("multimedia");
                if (c.length()>4){
                JSONObject d = (JSONObject) c.get(4);
                String url = d.getString("url");
final String title = b.getString("title");
                    try {
                        new DownloadImageTask(new DownloadImageTask.AsyncResponse(){

                            @Override
                            public void processFinish(Bitmap result) {

                                Paint myRectPaint = new Paint();
                                int x1 = 0;
                                int y1 = 0;
                                int x2 = 500;
                                int y2 = 500;

//Create a new image bitmap and attach a brand new canvas to it
                                Bitmap tempBitmap = Bitmap.createBitmap(result.getWidth(), result.getHeight(), Bitmap.Config.RGB_565);
                                Canvas tempCanvas = new Canvas(tempBitmap);

//Draw the image bitmap into the cavas
                                tempCanvas.drawBitmap(result, 0, 0, null);

//Draw everything else you want into the canvas, in this example a rectangle with rounded edges
                                tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);

                                Paint paint = new Paint();
                                paint.setColor(Color.WHITE);
                                paint.setTextSize(100);
                                        tempCanvas.drawText(title, 50, 150, paint);

                                img.setImageBitmap(tempBitmap);
                            }
                        },img)
                                .execute(url).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    text.setText(b.getString("abstract"));}else {
                    Toast.makeText(getBaseContext(), "multimedia does not exist",
                            Toast.LENGTH_LONG).show();
                    article++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void speak(String s) {
        Log.d("SSS", "to speak: " + s);
        m_syn.SpeakToAudio(s);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void processFinish(Bitmap output) {

    }
}
