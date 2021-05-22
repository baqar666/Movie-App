package com.example.mid_term_project;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class header extends androidx.appcompat.widget.AppCompatTextView{
    private String title;
    private  Boolean style;
    public header(@NonNull Context context) {
        super(context);
        setMyTextTitle();
    }
    public header (Context context , @Nullable AttributeSet attrs){
        super(context ,attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs , R.styleable.Header);
        int count = typedArray.getIndexCount();

        try{
            for(int i=0; i<count ; i++){
                int attr = typedArray.getIndex(i);
                if(attr == R.styleable.Header_style){
                    title = typedArray.getString(attr);
                    setMyTextTitle();
                }else if(attr == R.styleable.Header_style){
                    style = typedArray.getBoolean(attr,false);
                    setMyTextStyle();
                }
            }
        } finally {
            typedArray.recycle();
        }
    }
    public header (Context context , @Nullable AttributeSet attrs , int defStyleAttr){
        super(context ,attrs , defStyleAttr);
    }
    private void setMyTextTitle(){
        if(this.title != null){
            setText(this.title);
        }
    }
    private void setMyTextStyle(){
        if(this.style){
            setTextColor(Color.GREEN);
            setTextSize(20);
        }
    }
}
