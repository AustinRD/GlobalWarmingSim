package com.example.austin.globalwarmingsim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Event {
    public int id;
    public int type;
    public String name;
    public Region appliesTo = null;
    public int triggerTime;
    public String text;
    public String preText;
    public ArrayList<Event> resultingEvents = null;

    // Parameters that modify gamedata.
    public int badIndustry = 0;
    public int goodIndustry = 0;
    public int badEnergy = 0;
    public int goodEnergy = 0;
    public double popGrowthRate = 0;
    public int population = 0;
    public int animalSpecies = 0;
    public double badFactEmission = 0;
    public double goodFactEmission = 0;
    public double avgTemp = 0;
    public double seaLevel = 0;
    public int pollution = 0;

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
