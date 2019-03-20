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

        if (Settings.difficulty == 1)
            difficultyButton.toggle();
        if (Settings.soundFX == false)
            soundFxButton .toggle();
        if (Settings.music == false)
            musicButton.toggle();

        difficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.difficulty == 0)
                    Settings.difficulty = 1;
                else
                    Settings.difficulty = 0;
            }
        });
        soundFxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.soundFX == true)
                    Settings.soundFX = false;
                else
                    Settings.soundFX = true;
            }
        });
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.music == true)
                    Settings.music = false;
                else
                    Settings.music = true;
            }
        });
    }
}
