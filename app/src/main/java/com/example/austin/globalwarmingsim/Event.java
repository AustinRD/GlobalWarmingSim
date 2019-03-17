package com.example.austin.globalwarmingsim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Event {
    public int id;
    public int type;
    public String name;
    public Region appliesTo = null;
    public int triggerTime;
    public String text;

    // Parameters that modify gamedata.
    public int badIndustry;
    public int goodIndustry;
    public int badEnergy;
    public int goodEnergy;
    public double popGrowthRate;
    public int population;
    public int animalSpecies;
    public double badFactEmission;
    public double goodFactEmission;
    public double avgTemp;
    public double seaLevel;

    /**
     * An event in the game. Modifies game parameters on tick.
     * @param i Event id
     * @param t Event type
     * @param n Event name
     */
    public Event(int i, int t, String n, int trig) {
        id = i;
        type = t;
        name = n;
        triggerTime = trig;
    }
}

