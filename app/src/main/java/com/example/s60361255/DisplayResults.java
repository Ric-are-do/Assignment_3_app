package com.example.s60361255;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DisplayResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        String[] array = getIntent().getStringArrayExtra("key"); ;
        Log.d("arrayInfo" , String.valueOf(array));

    }
}