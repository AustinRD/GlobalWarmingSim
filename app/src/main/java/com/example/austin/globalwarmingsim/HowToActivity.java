package com.example.austin.globalwarmingsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HowToActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activtiy_play);
    }
}
