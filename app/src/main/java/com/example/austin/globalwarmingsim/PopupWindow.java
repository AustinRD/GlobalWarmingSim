package com.example.austin.globalwarmingsim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class PopupWindow extends AppCompatActivity
{
    int ii = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);
        int choice = 0;
        String postText1 = "";
        String postText2 = "";
        if(DataHolder.whichRegion >= 0 && DataHolder.whichRegion <= 3)
        {
            TextView choiceOne = (TextView) findViewById(R.id.textView_choiceOne);
            choiceOne.setVisibility(View.GONE);
            TextView choiceTwo = (TextView) findViewById(R.id.textView_choiceTwo);
            choiceTwo.setVisibility(View.GONE);
            TextView regionInfo = (TextView)findViewById(R.id.regionPopupTextview);
            regionInfo.setText(populateRegionInfo(DataHolder.whichRegion));
        }
        else
        {
            TextView choicePretext = (TextView) findViewById(R.id.regionPopupTextview);
            choicePretext.setText(DataHolder.events[DataHolder.I].preText);
            choicePretext.setVisibility(View.VISIBLE);
            TextView choiceOne = (TextView) findViewById(R.id.textView_choiceOne);
            choiceOne.setVisibility(View.VISIBLE);
            choiceOne.setText(DataHolder.events[DataHolder.I].name);
            TextView choiceTwo = (TextView) findViewById(R.id.textView_choiceTwo);
            choiceTwo.setVisibility(View.VISIBLE);
            //postText1 = DataHolder.events[DataHolder.I].text;
            ii = DataHolder.I;
            DataHolder.I++;
            choiceTwo.setText(DataHolder.events[DataHolder.I].name);
            //postText2 = DataHolder.events[DataHolder.I].text;
            choiceOne.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    TextView choiceOne = (TextView) findViewById(R.id.textView_choiceOne);
                    choiceOne.setVisibility(View.GONE);
                    TextView choiceTwo = (TextView) findViewById(R.id.textView_choiceTwo);
                    choiceTwo.setVisibility(View.GONE);
                    TextView postText = (TextView) findViewById(R.id.regionPopupTextview);
                    postText.setText(DataHolder.events[ii].text);

                }
            });
            choiceTwo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    TextView choiceOne = (TextView) findViewById(R.id.textView_choiceOne);
                    choiceOne.setVisibility(View.GONE);
                    TextView choiceTwo = (TextView) findViewById(R.id.textView_choiceTwo);
                    choiceTwo.setVisibility(View.GONE);
                    TextView postText = (TextView) findViewById(R.id.regionPopupTextview);
                    postText.setText(DataHolder.events[DataHolder.I].text);

                }
            });
            DataHolder.I++;
        }

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
