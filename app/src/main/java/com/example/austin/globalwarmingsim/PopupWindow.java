package com.example.austin.globalwarmingsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopupWindow extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        TextView popupTextOverride = (TextView)findViewById(R.id.regionPopupTextview);
        popupTextOverride.setText(populateRegionInfo(DataHolder.whichRegion));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8),(int)(height*0.6));
    }

    //button click stores int into whichRegion, whichRegion is index into Region array for getting info
    //Function call in OnClickListener for each button?
    //assumes our region array is global and named "region"

    public String populateRegionInfo(int whichRegion)
    {
        String textViewField = "" + DataHolder.region[whichRegion].name + "\n Population: " + DataHolder.region[whichRegion].population + "\nDevelopment: " + DataHolder.region[whichRegion].development + "\nAnimal Species: " + DataHolder.region[whichRegion].animalSpecies + "\nPopulation Growth Rate: " + DataHolder.region[whichRegion].dPopulation + "";
        return textViewField;
    }
}
