package com.example.austin.globalwarmingsim;

//all global data goes here
public class DataHolder {
    static public int whichRegion = 0;

    static public Simulator sim = new Simulator(0);

    static public Region[] region = {
            new Region(0, "West", sim),
            new Region(0, "Midwest", sim),
            new Region(0, "South", sim),
            new Region(0, "East", sim),
    };
}
