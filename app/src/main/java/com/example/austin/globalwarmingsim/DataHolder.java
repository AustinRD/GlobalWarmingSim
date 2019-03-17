package com.example.austin.globalwarmingsim;

//all global data goes here
public class DataHolder {
    static public int whichRegion = 0;

    static public Simulator sim = new Simulator(0);

    static public int difficulty = 0; //0 for standard, 1 for hard

    static public boolean soundFX = true;

    static public boolean music = true;

    static public Region[] region = {
            new Region(DataHolder.difficulty, "West", sim),
            new Region(DataHolder.difficulty, "Midwest", sim),
            new Region(DataHolder.difficulty, "South", sim),
            new Region(DataHolder.difficulty, "East", sim),
    };
}
