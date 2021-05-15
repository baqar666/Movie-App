package com.example.mid_term_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    int a;

    String url = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2021/04/lucifer-s6.png";


    ImageButton[] imageButton = new ImageButton[100];
    TextView[] textView = new TextView[100];
    LinearLayout[] linearLayout = new LinearLayout[100];

    public MainActivity() throws IOException {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.google.android.flexbox.FlexboxLayout flexboxLayout = (com.google.android.flexbox.FlexboxLayout) findViewById(R.id.show_picture);
        for (int i=0; i<20; i++){
            linearLayout[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout[i].setOrientation(LinearLayout.VERTICAL);
            linearLayout[i].setLayoutParams(params);
            flexboxLayout.addView(linearLayout[i]);
            textView[i] = new TextView(this);
            imageButton[i] = new ImageButton(this);
            imageButton[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
//            params.leftMargin = 50;
//            params.rightMargin = 50;
            if(i%2==0)
//            imageButton[i].setImageDrawable(d);
                Picasso.get().load(url).into(imageButton[i]);
            else
//                imageButton[i].setImageResource(R.drawable.zoom);
            Picasso.get().load("https://media.wired.com/photos/599dd02bf2c93452115817e9/master/pass/joker-FA.jpg").into(imageButton[i]);
            textView[i].append("Lucifer");
            textView[i].setTextColor(Color.parseColor("#FFFFFF"));
            imageButton[i].setLayoutParams(new LinearLayout.LayoutParams(350, 500));
            textView[i].setTextSize(20);
            linearLayout[i].addView(imageButton[i]);
            linearLayout[i].addView(textView[i]);


        }
    }
}