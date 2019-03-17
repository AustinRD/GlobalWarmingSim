package com.example.austin.globalwarmingsim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.button_play);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(playIntent);
            }
        });
        Button howToButton = (Button) findViewById(R.id.button_howTo);
        howToButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent playIntent = new Intent(MainActivity.this, HowToActivity.class);
                startActivity(playIntent);
            }
        });
        Button infoButton = (Button)findViewById(R.id.button_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swap = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(swap);
            }
        });
        Button settingsButton = (Button)findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swap = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(swap);
            }
        });
    }
}