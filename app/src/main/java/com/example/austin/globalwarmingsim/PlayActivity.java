package com.example.austin.globalwarmingsim;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity
{
    //Code necessary for scaling the play map. (Zooming)
    private ScaleGestureDetector mScaleGestureDetector;
    //Global scale factor.
    private float mScaleFactor = 1.0f;
    //Global map image.
    private ImageView mImageView;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //initEvents(DataHolder.sim);
        DataHolder.events = new Event[1000];
        setContentView(R.layout.activity_play);

        // Assign regions to start simulation
        DataHolder.sim.regions = DataHolder.regions;
        Simulator newGame = DataHolder.sim;
        newGame.setDifficulty(0);
        startSimulation();

        ArrayList<Event> event = new ArrayList<>();
        //newGame.tick(event); //Gets the new game initial values.

        String popText = "Population: " + newGame.population;
        String polText = "Pollution: " + newGame.pollution;
        String dateText = "Year: " + DataHolder.sim.date;

        TextView populationText = (TextView) findViewById(R.id.textView_population);
        populationText.setText(popText);
        TextView pollutionText = (TextView) findViewById(R.id.textView_pollution);
        pollutionText.setText(polText);
        TextView yearText = (TextView) findViewById(R.id.textView_year);
        yearText.setText(dateText);


        mImageView = (ImageView) findViewById(R.id.imageView_playMap);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        ImageView eastCoastInfo = (ImageView) findViewById(R.id.imageView_eastCoastEye);
        eastCoastInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataHolder.whichRegion = 3;
                DataHolder.regions[DataHolder.whichRegion].setDifficulty();
                Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
                startActivity(regionPopup);
            }
        });
        ImageView westCoastInfo = (ImageView) findViewById(R.id.imageView_westCoastEye);
        westCoastInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataHolder.whichRegion = 0;
                DataHolder.regions[DataHolder.whichRegion].setDifficulty();
                Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
                startActivity(regionPopup);
            }
        });
        ImageView southernInfo = (ImageView) findViewById(R.id.imageView_southernEye);
        southernInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataHolder.whichRegion = 2;
                DataHolder.regions[DataHolder.whichRegion].setDifficulty();
                Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
                startActivity(regionPopup);
            }
        });
        ImageView midWestInfo = (ImageView) findViewById(R.id.imageView_midWestEye);
        midWestInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataHolder.whichRegion = 1;
                DataHolder.regions[DataHolder.whichRegion].setDifficulty();
                Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
                startActivity(regionPopup);
            }
        });

        DataHolder.whichRegion = 4;
        Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
        startActivity(regionPopup);

//        while(DataHolder.date < 2000)
//        {
//            newGame.tick(event);
//            DataHolder.whichRegion = 4;
//            Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
//            startActivity(regionPopup);
//        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    // Simulation control methods-------------------------------------------------------------------
    private void startSimulation() {
        timer = new Timer();
        timer.schedule(new AdvanceSim(), 0 , 1000);
    }

    public class AdvanceSim extends TimerTask {
        public void run() {
            System.out.println("Advancing sim Date: " + DataHolder.sim.date + " and tick # :): " + DataHolder.sim.ticks);
            DataHolder.sim.tick(new ArrayList<Event>());
            updateInfoBar(); // maybe make this on it's own schedule/timer for when sim is paused?
        }
    }
    //----------------------------------------------------------------------------------------------

    private void updateInfoBar() {
        String popText = "Population: " + DataHolder.sim.population;
        String polText = "Pollution: " + DataHolder.sim.pollution;
        String dateText = "Year: " + DataHolder.sim.date;

        TextView populationText = (TextView) findViewById(R.id.textView_population);
        populationText.setText(popText);
        TextView pollutionText = (TextView) findViewById(R.id.textView_pollution);
        pollutionText.setText(polText);
        TextView yearText = (TextView) findViewById(R.id.textView_year);
        yearText.setText(dateText);
    }

    /**
     * A class to
     */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        ImageView eastCoastInfo = (ImageView) findViewById(R.id.imageView_eastCoastEye);
        ImageView westCoastInfo = (ImageView) findViewById(R.id.imageView_westCoastEye);
        ImageView southernInfo = (ImageView) findViewById(R.id.imageView_southernEye);
        ImageView midWestInfo = (ImageView) findViewById(R.id.imageView_midWestEye);
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector)
        {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,Math.min(mScaleFactor, 10.0f));

            //Logic for restraining the scaling to 1x to 2x.
            if(mScaleFactor > 1 && mScaleFactor <= 2)
            {
                mImageView.setScaleX(mScaleFactor);
                mImageView.setScaleY(mScaleFactor);
                eastCoastInfo.setVisibility(View.GONE);
                westCoastInfo.setVisibility(View.GONE);
                southernInfo.setVisibility(View.GONE);
                midWestInfo.setVisibility(View.GONE);
            }
            else if(mScaleFactor <= 1)
            {
                mScaleFactor = 1.0f;
                eastCoastInfo.setVisibility(View.VISIBLE);
                westCoastInfo.setVisibility(View.VISIBLE);
                southernInfo.setVisibility(View.VISIBLE);
                midWestInfo.setVisibility(View.VISIBLE);
            }
            else if(mScaleFactor > 2)
            {
                mScaleFactor = 2.0f;
            }
            Log.d("LogTag", "mScaleFactor: " + mScaleFactor);

            return true;
        }
    }

}


