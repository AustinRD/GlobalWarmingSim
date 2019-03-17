package com.example.austin.globalwarmingsim;

//all global data goes here
public class DataHolder {
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
}
