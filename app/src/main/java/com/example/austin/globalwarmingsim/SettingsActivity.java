package com.example.austin.globalwarmingsim;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        ToggleButton difficultyButton = findViewById(R.id.button_difficulty);
        ToggleButton soundFxButton = findViewById(R.id.button_sound_fx);
        ToggleButton musicButton = findViewById(R.id.button_music);

        if (DataHolder.difficulty == 1)
            difficultyButton.toggle();
        if (DataHolder.soundFX == false)
            soundFxButton .toggle();
        if (DataHolder.music == false)
            musicButton.toggle();

        difficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.difficulty == 0)
                    DataHolder.difficulty = 1;
                else
                    DataHolder.difficulty = 0;
            }
        });
        soundFxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.soundFX == true)
                    DataHolder.soundFX = false;
                else
                    DataHolder.soundFX = true;
            }
        });
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.music == true)
                    DataHolder.music = false;
                else
                    DataHolder.music = true;
            }
        });
    }
}
