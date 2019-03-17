package com.example.austin.globalwarmingsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HowToActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_howto);

        TextView howToText = (TextView) findViewById(R.id.textView_howToPlayInfo);
        howToText.setText("Save the world by 2030!\n\n You will be making decisions that delay " +
                "or reverse the effects of climate change before the Earth warms above 1.5°C " +
                "\"the point of no return\" for our climate. Every decision you make will have " +
                "consequences. \n\nCan you save our civilization?");
    }
}
