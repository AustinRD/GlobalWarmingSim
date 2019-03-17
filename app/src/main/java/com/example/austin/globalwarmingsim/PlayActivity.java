package com.example.austin.globalwarmingsim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity
{
    //Code necessary for scaling the play map. (Zooming)
    private ScaleGestureDetector mScaleGestureDetector;
    //Global scale factor.
    private float mScaleFactor = 1.0f;
    //Global map image.
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);
        mImageView = (ImageView) findViewById(R.id.imageView_playMap);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        ImageView eastCoastInfo = (ImageView) findViewById(R.id.imageView_eastCoastEye);
        eastCoastInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataHolder.whichRegion = 3;
                DataHolder.region[DataHolder.whichRegion].setDifficulty();
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
                DataHolder.region[DataHolder.whichRegion].setDifficulty();
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
                DataHolder.region[DataHolder.whichRegion].setDifficulty();
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
                DataHolder.region[DataHolder.whichRegion].setDifficulty();
                Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
                startActivity(regionPopup);
            }
        });

        //Overlay Text
        TextView populationText = (TextView) findViewById(R.id.textView_population);
        populationText.setText("Population:");
        TextView pollutionText = (TextView) findViewById(R.id.textView_pollution);
        pollutionText.setText("Pollution:");
        TextView yearText = (TextView) findViewById(R.id.textView_year);
        yearText.setText("Year:");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }
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


