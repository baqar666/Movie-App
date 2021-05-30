package com.example.mid_term_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.mid_term_project.R.id.search;
import static com.example.mid_term_project.R.id.visible;

public class MainActivity extends AppCompatActivity {
    int a;
    ProgressBar spinner;
    String url = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2021/04/lucifer-s6.png";
    ImageButton search;
    ImageButton[] imageButton = new ImageButton[100];
    TextView[] textView = new TextView[100];
    LinearLayout[] linearLayout = new LinearLayout[100];
    ProgressBar[] progressBar=new ProgressBar[100];

    String URL = "https://api.themoviedb.org/3/discover/tv?api_key=a94176a9ac97d8c0fa62c3f02ec0bf93&with_networks=213";
    String imageBaseURL="https://image.tmdb.org/t/p/original";
    RotateLoading rotateLoading;
    static JSONObject result;
//    String rl = "http://my-json-feed";
com.google.android.flexbox.FlexboxLayout flexboxLayout;


// Access the RequestQueue through your singleton class.
//MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.search);
        spinner = findViewById(R.id.progressBar);
        flexboxLayout = (com.google.android.flexbox.FlexboxLayout) findViewById(R.id.show_picture);
//        rotateLoading = findViewById(R.id.rotateloading);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        spinner.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                        System.out.print("Response" +response.toString());
                        Log.d("Lucifer", response);
                        renderImageButton(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(stringRequest);

    }
    void onClickListener(ImageButton imageButton, int i, JSONObject resultData){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Good hogya g",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , Movie_detail.class);
                intent.putExtra("data", resultData.toString());
                startActivity(intent);
                result=resultData;

            }
        });
    }
    void renderImageButton (String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=new JSONArray((jsonObject.getString("results")));

            for ( int i=0; i<jsonArray.length(); i++){
                JSONObject resultData=jsonArray.getJSONObject(i);
                linearLayout[i] = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout[i].setOrientation(LinearLayout.VERTICAL);
                linearLayout[i].setLayoutParams(params);
                flexboxLayout.addView(linearLayout[i]);
                textView[i] = new TextView(this);
                imageButton[i] = new ImageButton(this);
                progressBar[i]=new ProgressBar(this);
                progressBar[i].setLayoutParams(new LinearLayout.LayoutParams(350, 500));
//                progressBar[i].setPadding(150, 150, 225, 225);


                linearLayout[i].addView(progressBar[i]);

                imageButton[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageButton[i].setVisibility(View.GONE);
//            params.leftMargin = 50;
//            params.rightMargin = 50;

//                if(i%2==0)
//            imageButton[i].setImageDrawable(d);
//                    Picasso.get().load(url).into(imageButton[i]);
//                else
//                imageButton[i].setImageResource(R.drawable.zoom);
                final int temp=i;
                    Picasso.get().load(imageBaseURL+resultData.getString("poster_path"))
                            .placeholder(R.drawable.ic_baseline_access_time_24)
                            .error(R.drawable.ic_baseline_error_outline_24)
                            .into(imageButton[i], new Callback() {
                                @Override
                                public void onSuccess() {
                                    progressBar[temp].setVisibility(View.GONE);

                                    imageButton[temp].setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });

                textView[i].append(resultData.getString("name"));
//                textView[i].setEms(5);
                textView[i].setMaxLines(1);
                textView[i].setEllipsize(TextUtils.TruncateAt.START);
                textView[i].setTextColor(Color.parseColor("#FFFFFF"));
                imageButton[i].setLayoutParams(new LinearLayout.LayoutParams(350, 500));
                textView[i].setTextSize(20);
                linearLayout[i].addView(imageButton[i]);
                linearLayout[i].addView(textView[i]);
                onClickListener(imageButton[i],i,resultData);
                if(resultData.getString("name").equals("Elite")){
                    Toast.makeText(MainActivity.this, resultData.getString("name"),Toast.LENGTH_LONG).show();
                }
//                else {
//                    Toast.makeText(MainActivity.this, "NOPE",Toast.LENGTH_LONG).show();
//                }
            }

        }
        catch (JSONException e){

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}