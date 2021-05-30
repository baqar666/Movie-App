package com.example.mid_term_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie_detail extends AppCompatActivity {
    boolean checked = true;
    JSONObject mJsonObject;
    LinearLayout linearLayout;
    ImageView cover_photo,movie_photo;
    String imageBaseURL="https://image.tmdb.org/t/p/original";
    TextView movie_name,movie_title,movie_year,movie_rating,movie_detail,movie_director,movie_studio,movie_gener,movie_video;
    Button more_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movie_name = findViewById(R.id.movie_name);
        movie_title = findViewById(R.id.movie_title);
        movie_year = findViewById(R.id.release_year);
        movie_rating = findViewById(R.id.movie_rating);
        movie_detail = findViewById(R.id.movie_detail);
        movie_director = findViewById(R.id.movie_director_name);
        movie_studio = findViewById(R.id.movie_studio);
        movie_gener = findViewById(R.id.movie_genre);
        movie_video = findViewById(R.id.movie_video);
        cover_photo = findViewById(R.id.coverphoto);
        movie_photo = findViewById(R.id.movie_picture);
        more_text = findViewById(R.id.detail_more_btn);
        linearLayout = findViewById(R.id.textView_moreButton);
        more_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                    movie_detail.setMaxLines(100);
                    movie_detail.startAnimation(animation);
                    linearLayout.startAnimation(animation);
                    checked=false;
                    more_text.setText("Close");
                }
                else if(movie_detail.getMaxLines()==3){

                }
                else{
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                    movie_detail.setMaxLines(3);
                    movie_detail.startAnimation(animation);
                    linearLayout.startAnimation(animation);
                    checked=true;
                    more_text.setText("Open");
                }

            }
        });
        if(getIntent().hasExtra("data")) {
            try {
                mJsonObject = new JSONObject(getIntent().getStringExtra("data"));
//                mJsonObject.getString("backdrop_path");
//                Toast.makeText(Movie_detail.this, mJsonObject.getString("backdrop_path"), Toast.LENGTH_LONG).show();
                Picasso.get().load(imageBaseURL+mJsonObject.getString("backdrop_path")).into(cover_photo);
                Picasso.get().load(imageBaseURL+mJsonObject.getString("poster_path")).into(movie_photo);
                movie_name.setText(mJsonObject.getString("name"));
                movie_year.setText(mJsonObject.getString("first_air_date"));
                movie_rating.setText(mJsonObject.getString("vote_average"));
                movie_title.setText(mJsonObject.getString("name"));
                movie_detail.setText(mJsonObject.getString("overview"));

//                movie_director.setText(mJsonObject.getString(""));
            }
            catch (JSONException e){

            }
        }
    }
}