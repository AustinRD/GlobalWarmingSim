package com.example.austin.globalwarmingsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

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
        setContentView(R.layout.activtiy_play);
        mImageView = (ImageView) findViewById(R.id.imageView_playMap);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleGestureDetector.onTouchEvent(event);


        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
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
            }
            else if(mScaleFactor < 1)
            {
                mScaleFactor = 1.0f;
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


