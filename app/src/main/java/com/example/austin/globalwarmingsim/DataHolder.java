package com.example.austin.globalwarmingsim;

import java.util.ArrayList;

//all global data goes here
public class DataHolder
{
    static public int whichRegion = 5;

    static public Simulator sim = new Simulator(0);

    static public int difficulty = 0; //0 for standard, 1 for hard

    static public boolean soundFX = true;

    static public boolean music = true;

    static public Region[] region = {
            new Region("West", 15000000, sim),
            new Region("Midwest", 50000000, sim),
            new Region("South", 50000000, sim),
            new Region("East", 100000000, sim),
    };

    static public int date = 1980;

    static public Event[] events = new Event[50];

    public static Event[] initEvents(Simulator s)
    {

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
        events[10].text = "You have chosen to invest in cleaner substitutes for plastics";
        events[10].preText = "Food is again a topic of debate among the citizenry. Choose to subsidize genetically modified organisms for higher yields or keep more conventional farming methods?";

        events[11] = new Event(1, 0, "Choose Organic", 1985);
        events[11].popGrowthRate = 0.0075;
        events[11].animalSpecies = -4;
        events[11].pollution = 5;
        events[11].appliesTo = s.regions[1]; // applies to northeast
        events[11].text = "You have chosen to invest in cleaner substitutes for plastics";

        events[12] = new Event(1, 0, "Choose to Stop Poaching", 1986);
        events[12].animalSpecies = 6;
        events[12].appliesTo = s.regions[3]; // applies to northeast
        events[12].text = "You have chosen to invest in cleaner substitutes for plastics";
        events[12].preText = "Poachers are endangering our native fauna. Divert government resources to attempt to stop them?";

        events[13] = new Event(1, 0, "Choose to let it Continue", 1986);
        events[13].animalSpecies = -4;
        events[13].appliesTo = s.regions[3]; // applies to northeast
        events[13].text = "You have chosen to invest in cleaner substitutes for plastics";

        events[14] = new Event(1, 0, "Choose to Ban CFCs", 1987);
        events[14].badIndustry = -6;
        events[14].pollution = -10;
        events[14].badFactEmission = -0.01;
        events[14].animalSpecies = 4;
        events[14].appliesTo = s.regions[3]; // applies to northeast
        events[14].text = "You have chosen to invest in cleaner substitutes for plastics";
        events[14].preText = "Chlorofluorocarbons (CFCs) are amazing refrigerants and aerosols, but eat away at the ozone layer. Should the country ban CFCs and let industry suffer?";

        events[15] = new Event(1, 0, "Choose Not to Ban", 1987);
        events[15].badIndustry = 6;
        events[15].pollution = 10;
        events[15].badFactEmission = 0.01;
        events[15].animalSpecies = -4;
        events[15].appliesTo = s.regions[3]; // applies to northeast
        events[15].text = "You have chosen to invest in cleaner substitutes for plastics";

        events[16] = new Event(1, 0, "Choose to Subsidize Highways", 1988);
        events[16].pollution = 10;
        events[16].badFactEmission = 0.01;
        events[16].appliesTo = s.regions[0]; // applies to northeast
        events[16].text = "You have chosen to invest in cleaner substitutes for plastics";
        events[16].preText = "The nation is at a literal crossroads: invest in more highways and improve roadways or invest more in mass transit?";

        events[17] = new Event(1, 0, "Choose to Subsidize Mass Transit", 1988);
        events[17].pollution = -10;
        events[17].badFactEmission = -0.01;
        events[17].appliesTo = s.regions[0]; // applies to northeast
        events[17].text = "You have chosen to invest in cleaner substitutes for plastics";

        events[18] = new Event(1, 0, "Choose to Invest in Recycling", 1989);
        events[18].pollution = -10;
        events[18].popGrowthRate = -0.0075;
        events[18].badIndustry = - 3;
        events[18].goodIndustry = 3;
        events[18].badFactEmission = -0.01;
        events[18].animalSpecies = 4;
        events[18].appliesTo = s.regions[3]; // applies to northeast
        events[18].text = "You have chosen to invest in cleaner substitutes for plastics";
        events[18].preText= "Trash is piling up in the country's landfills. Start an initiative to recycle more and mandate use of recycled materials in manufacturing if possible?";

        events[19] = new Event(1, 0, "Choose Not to Invest in Recycling", 1989);
        events[19].pollution = 10;
        events[19].popGrowthRate = 0.0075;
        events[19].badIndustry = 3;
        events[19].goodIndustry = 3;
        events[19].badFactEmission = 0.01;
        events[19].animalSpecies = -4;
        events[19].appliesTo = s.regions[3]; // applies to northeast
        events[19].text = "You have chosen to invest in cleaner substitutes for plastics";

        return events;
    }
}
