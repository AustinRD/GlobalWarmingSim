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

import java.util.ArrayList;

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
        initEvents(DataHolder.sim);
        setContentView(R.layout.activity_play);

        int d = 0; // Difficulty
        Simulator newGame = new Simulator(0);
        Region[] rs = {
                new Region("North East", 15000000 , newGame),
                new Region("South", 50000000, newGame),
                new Region("Midwest", 50000000, newGame),
                new Region("West Coast", 100000000, newGame)
        };
        newGame.regions = rs;
        newGame.setDifficulty(0);

        ArrayList<Event> event = new ArrayList<>();
        newGame.tick(event); //Gets the new game initial values.

        String popText = "Population: " + newGame.population;
        String polText = "Pollution: " + newGame.pollution;
        String dateText = "Year: " + DataHolder.date;

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

        while(DataHolder.date < 2000)
        {
            newGame.tick(event);
            DataHolder.whichRegion = 4;
            Intent regionPopup = new Intent(PlayActivity.this,PopupWindow.class);
            startActivity(regionPopup);
        }

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

    public static Event[] initEvents(Simulator s)
    {
        Event[] events = DataHolder.events;
    /*
      INIT EVENT AT ARRAY INDEX
      ADD EFFECTS
      MAKE CHILDREN (AND THEIR EFFECTS)
      ADD CHILD TO RESULTING EVENT ARRAYLIST
     */

        events[0] = new Event(0, 1, "Choose Coal", 1980);
        events[0].badIndustry = 3;
        events[0].appliesTo = s.regions[0]; // applies to northeast
        events[0].text = "Higher energy output has been achieved, but at what cost?";
        events[0].preText = "America's people and industries need energy. "
                + "Coal will provide an immediate boost, but investing in renewables might pay off in the long run.";

        events[1] = new Event(1, 0, "Choose Renewable Energy", 1980);
        events[1].goodIndustry = 3;
        events[1].appliesTo = s.regions[0]; // applies to northeast
        events[1].text = "You have chosen the renewable option, perhaps new technology will come of our investments";
        Event newEvent = new Event(100,0,"Renewable Discovery",1983);
        newEvent.goodFactEmission = -0.0025;
        newEvent.text = "Green-tech developments have allowed cleaner manufacturing techniques.";
        events[1].resultingEvents = new ArrayList<>();
        events[1].resultingEvents.add(newEvent);

        events[2] = new Event(2, 0, "Choose Nukes", 1981);
        events[2].appliesTo = s.regions[2];
        events[2].goodIndustry = 6;
        events[2].popGrowthRate = 0.05;
        events[2].text = "We have opted to leverage our mastery of the atom. Clean energy all around";
        events[2].preText = "The country's demand for energy is higher than ever, and the government "
                + "must exercise foresight in its development. Go into nuclear or solar energy?";

        events[3] = new Event(3, 0, "Choose Solar", 1981);
        events[3].appliesTo = s.regions[2];
        events[3].goodIndustry = 3;
        events[3].text = "You have chosen the renewable option, we are now harnessing power cleaner than ever before.";

        events[4] = new Event(0, 1, "Choose Deforestation", 1982);
        events[4].popGrowthRate = 0.01;
        events[4].appliesTo = s.regions[0]; // applies to northeast
        events[4].text = "Room must be made for people to live. You had no choice...";
        events[4].preText = "There is a housing crisis, and the people need homes. Open up our forests for logging or conserve?";

        events[5] = new Event(1, 0, "Choose Conservation", 1982);
        events[5].popGrowthRate = -3;
        events[5].appliesTo = s.regions[0]; // applies to northeast
        events[5].text = "The protection of our forests is paramount to the conservation of the environment.";

        events[6] = new Event(0, 1, "Choose Plants", 1983);
        events[6].popGrowthRate = -0.075;
        events[6].goodIndustry = 3;
        events[6].animalSpecies = 3;
        events[6].appliesTo = s.regions[0]; // applies to northeast
        events[6].text = "Your beautiful meadows and fields cover the landscape";
        events[6].preText = "Food subsidies have come up for review again. You can push the legislature to heavily subsidize animal agriculture or plant-based agriculture.";

        events[7] = new Event(1, 0, "Choose Animals", 1983);
        events[7].popGrowthRate = 0.0075;
        events[7].badIndustry = 3;
        events[7].animalSpecies = -3;
        events[7].appliesTo = s.regions[0]; // applies to northeast
        events[7].text = "We must feed the people";

        events[8] = new Event(0, 1, "Choose Plastics", 1984);
        events[8].popGrowthRate = 0.01;
        events[8].animalSpecies = -3;
        events[8].appliesTo = s.regions[0]; // applies to northeast
        events[8].text = "Plastics are a critical modern technology";
        events[8].preText = "Plastics are incredibly useful along all swaths of American life and industry, but don't degrade. Stick with plastics or try to shift the country to more natural materials?";

        events[9] = new Event(1, 0, "Choose Natural", 1984);
        events[9].popGrowthRate = -0.0125;
        events[9].animalSpecies = 4;
        events[9].appliesTo = s.regions[0]; // applies to northeast
        events[9].text = "You have chosen to invest in cleaner substitutes for plastics";

        events[10] = new Event(1, 0, "Choose GMO", 1985);
        events[10].popGrowthRate = 0.0075;
        events[10].animalSpecies = 4;
        events[10].appliesTo = s.regions[1]; // applies to northeast
        events[10].text = "You crops are hardier, but is the loss of sustainability worth the reward?";
        events[10].preText = "Food is again a topic of debate among the citizenry. Choose to subsidize genetically modified organisms for higher yields or keep more conventional farming methods?";

        events[11] = new Event(1, 0, "Choose Organic", 1985);
        events[11].popGrowthRate = 0.0075;
        events[11].animalSpecies = -4;
        events[11].pollution = 5;
        events[11].appliesTo = s.regions[1]; // applies to northeast
        events[11].text = "Despite higher crop yields, organic food is more sustainable in the long run.";

        events[12] = new Event(1, 0, "Choose to Stop Poaching", 1986);
        events[12].animalSpecies = 6;
        events[12].appliesTo = s.regions[3]; // applies to northeast
        events[12].text = "Efforts are being made to stop the poaching and protect your wildlife.";
        events[12].preText = "Poachers are endangering our native fauna. Divert government resources to attempt to stop them?";

        events[13] = new Event(1, 0, "Choose to let it Continue", 1986);
        events[13].animalSpecies = -4;
        events[13].appliesTo = s.regions[3]; // applies to northeast
        events[13].text = "The poachers are allowed to continue their illegal activities. Who knows how much damage they could do?";

        events[14] = new Event(1, 0, "Choose to Ban CFCs", 1987);
        events[14].badIndustry = -6;
        events[14].pollution = -10;
        events[14].badFactEmission = -0.01;
        events[14].animalSpecies = 4;
        events[14].appliesTo = s.regions[3]; // applies to northeast
        events[14].text = "The negative effects on the ozone layer were obvious, you couldn't have allowed further damage.";
        events[14].preText = "Chlorofluorocarbons (CFCs) are amazing refrigerants and aerosols, but eat away at the ozone layer. Should the country ban CFCs and let industry suffer?";

        events[15] = new Event(1, 0, "Choose Not to Ban", 1987);
        events[15].badIndustry = 6;
        events[15].pollution = 10;
        events[15].badFactEmission = 0.01;
        events[15].animalSpecies = -4;
        events[15].appliesTo = s.regions[3]; // applies to northeast
        events[15].text = "The needs of our industries came first this time.";

        events[16] = new Event(1, 0, "Choose to Subsidize Highways", 1988);
        events[16].pollution = 10;
        events[16].badFactEmission = 0.01;
        events[16].appliesTo = s.regions[0]; // applies to northeast
        events[16].text = "The motorways roar with traffic! When the cars aren't sitting there at idle, that is...";
        events[16].preText = "The nation is at a literal crossroads: invest in more highways and improve roadways or invest more in mass transit?";

        events[17] = new Event(1, 0, "Choose to Subsidize Mass Transit", 1988);
        events[17].pollution = -10;
        events[17].badFactEmission = -0.01;
        events[17].appliesTo = s.regions[0]; // applies to northeast
        events[17].text = "Our roads seem less busy and more clean with the reduced motor traffic.";

        events[18] = new Event(1, 0, "Choose to Invest in Recycling", 1989);
        events[18].pollution = -10;
        events[18].popGrowthRate = -0.0075;
        events[18].badIndustry = - 3;
        events[18].goodIndustry = 3;
        events[18].badFactEmission = -0.01;
        events[18].animalSpecies = 4;
        events[18].appliesTo = s.regions[3]; // applies to northeast
        events[18].text = "You have chosen to invest in more efficient ways toreduce waste";
        events[18].preText= "Trash is piling up in the country's landfills. Start an initiative to recycle more and mandate use of recycled materials in manufacturing if possible?";

        events[19] = new Event(1, 0, "Choose Not to Invest in Recycling", 1989);
        events[19].pollution = 10;
        events[19].popGrowthRate = 0.0075;
        events[19].badIndustry = 3;
        events[19].goodIndustry = 3;
        events[19].badFactEmission = 0.01;
        events[19].animalSpecies = -4;
        events[19].appliesTo = s.regions[3]; // applies to northeast
        events[19].text = "It came from the earth, so it must go back. No extra work required, right?";

        events[20] = new Event(1, 0, "Choose to Ban Plastic Bags", 1990);
        events[20].pollution = -10;
        events[20].badIndustry = -3;
        events[20].goodIndustry = 3;
        events[20].badFactEmission = -0.01;
        events[20].animalSpecies = 4;
        events[20].appliesTo = s.regions[2]; // applies to northeast
        events[20].text = "Our landfills and oceans are saved from the presence of such materials.";


        events[21] = new Event(1, 0, "Choose Not to Ban Plastic Bags", 1990);
        events[21].pollution = 10;
        events[21].badIndustry = 3;
        events[21].badFactEmission = 0.01;
        events[21].animalSpecies = -4;
        events[21].appliesTo = s.regions[2]; // applies to northeast
        events[21].text = "You make sure to ask that your items are double bagged, can't have them breaking.";
        events[21].preText = "Plastic grocery bags end up in the ocean more often then not, and are ubiquitous in America's waterways. Ban them?";

        events[22] = new Event(1, 0, "Choose to implement a Carbon Tax", 1991);
        events[22].pollution = -10;
        events[22].badIndustry = -3;
        events[22].goodIndustry = 3;
        events[22].badFactEmission = -0.01;
        events[22].animalSpecies = 4;
        events[22].appliesTo = s.regions[0]; // applies to northeast
        events[22].text = "You have chosen to fix the market failure associated with the environment.";
        events[22].preText = "The carbon footprint of our industries has become a hot topic. Carbon taxes could cut carbon emissions immensely, but could also hurt industry. Enact the tax?";

        events[23] = new Event(1, 0, "Choose not to implement a Carbon Tax", 1991);
        events[23].pollution = 10;
        events[23].badFactEmission = 0.01;
        events[23].popGrowthRate = 0.0075;
        events[23].appliesTo = s.regions[0]; // applies to northeast
        events[23].text = "You have decided the market can handle the environment on its own.";

        events[24] = new Event(0, 1, "Subsidize and Ban", 1992);
        events[24].popGrowthRate = -0.0075;
        events[24].pollution = -2;
        events[24].appliesTo = s.regions[0]; // applies to northeast
        events[24].text = "Your regulations increase the adoption rate of efficient lightbulbs.";
        events[24].preText = "Food waste is a major problem facing the nation. More than a third of the food America produces is wasted. Loosen regulations on expiration dates and storage methods to attempt to get more food to people's plates and less to the trash can?";

        events[25] = new Event(1, 0, "Leave This Alone", 1992);
        events[25].badFactEmission = -0.01;
        events[25].appliesTo = s.regions[0]; // applies to northeast
        events[25].text = "You let the people decide when to switch over.";


        events[26] = new Event(0, 1, "Subsidize and Ban", 1993);
        events[26].popGrowthRate = -2;
        events[26].pollution = -2;
        events[26].appliesTo = s.regions[0]; // applies to northeast
        events[26].text = "Less food is wasted overall in the country, and people are more vigilant for bad food.";
        events[26].preText = "Food waste is a major problem facing the nation. More than a third of the food America produces is wasted. Loosen regulations on expiration dates and storage methods to attempt to get more food to people's plates and less to the trash can?";

        events[27] = new Event(1, 0, "Leave This Alone", 1993);
        events[27].badFactEmission = 0.01;
        events[27].appliesTo = s.regions[0]; // applies to northeast
        events[27].text = "You don't feel this is a significant enough issue to change laws for.";

        events[28] = new Event(0, 1, "Loosen Regulations", 1994);
        events[28].badFactEmission = 0.01;
        events[28].goodIndustry = 2;
        events[28].pollution = -2;
        events[28].appliesTo = s.regions[0]; // applies to northeast
        events[28].preText = "Food waste is a major problem facing the nation. More than a third of the food America produces is wasted. Loosen regulations on expiration dates and storage methods to attempt to get more food to people's plates and less to the trash can?";

        events[29] = new Event(1, 0, "Keep Regulations The Same ", 1994);
        events[29].pollution = 2;
        events[29].badFactEmission = 0.01;
        events[29].popGrowthRate = 2;
        events[29].appliesTo = s.regions[0]; // applies to northeast
        events[29].text = "You see an almost immediate improvement in the water bottle situation, though their prices temporarily spiked.";

        events[30] = new Event(0, 1, "Enact Tax", 1995);
        events[30].popGrowthRate = -2;
        events[30].badFactEmission = -0.01;
        events[30].badIndustry = -4;
        events[30].animalSpecies = 4;
        events[30].pollution = -4;
        events[30].appliesTo = s.regions[0]; // applies to northeast
        events[30].preText = "Plastic water bottles have again clogged our waterways, beaches, and trails. Tax companies that produce plastic water bottles to nudge them to consider better alternatives?";

        events[31] = new Event(1, 0, "Don't Enact Tax ", 1995);
        events[31].pollution = 2;
        events[31].badFactEmission = -0.01;
        events[31].popGrowthRate = 2;
        events[31].text = "The companies will eventually regulate themselves or be buried under their own trash.";

        return DataHolder.events;
    }

}


