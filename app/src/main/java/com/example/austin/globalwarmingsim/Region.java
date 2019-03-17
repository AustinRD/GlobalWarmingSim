package com.example.austin.globalwarmingsim;
import java.util.ArrayList;

public class Region {
    public Simulator sim;
    // Unique to region.
    public String name;
    public ArrayList<Event> events;
    public ArrayList<Event> results;
    //public Region[] neighbors; //  TODO use this.
    public int badIndustry;
    public int goodIndustry;
    public int badEnergy;
    public int goodEnergy;
    public double popGrowthRate;


    // Global corresponding
    public double dcO2;
    public int population;
    public int dPopulation;
    public int animalSpecies;
    public int development;

    // Game Tuning
    private double badFactEmission = 1.0;
    private double goodFactEmission = 0.1;

    /**
     * Creates new region object.
     * @param diff Difficulty ranging from 0 to 2.
     * @param n Name of region.
     * ns Neighboring regions.
     */
    public Region(int diff, String n, Simulator s) {
        sim = s;
        name = n;
        events = new ArrayList();
        //neighbors = ns;
        switch (diff) {

            case(1):
                badIndustry = 10;
                goodIndustry = 0;
                badEnergy = 10;
                goodEnergy = 0;
                population = 60000;
                animalSpecies = 50;
                development = 6;
                updateDeltacO2();
                updatePopGrowth();
                updateDeltaPopulation();

            case(0):
            default:
                badIndustry = 10;
                goodIndustry = 2;
                badEnergy = 10;
                goodEnergy = 2;
                population = 50000;
                animalSpecies = 100;
                development = 5;
                updateDeltacO2();
                updatePopGrowth();
                updateDeltaPopulation();
        }
    }

    public void tick(ArrayList<Event> newEvents) {
        events.addAll(newEvents); // Update current event list.
        applyEvents();
        updateDeltaPopulation();
        updateDeltacO2();
        updatePopGrowth();
        updateDeltaPopulation();
        updateLocals();
        resultingEvents();
    }

    private void applyEvents() {
        for(Event event : events) { // apply events whose time has come.
            if (event.triggerTime == sim.date) {
                goodIndustry += event.goodIndustry;
                badIndustry += event.goodIndustry;
                badEnergy += event.badEnergy;
                goodEnergy += event.goodEnergy;
                population += event.population;
                popGrowthRate += event.popGrowthRate;
                animalSpecies += event.animalSpecies;;
            }
        }
    }

    private void updateLocals() {
        population += dPopulation; // Is that it?

    }

    private void updateDeltacO2() {
        dcO2 = (badFactEmission * badIndustry) + (goodFactEmission * goodIndustry);
    }

    private void updateDeltaPopulation() {
        dPopulation = (int)(population * popGrowthRate);
    }

    private void updatePopGrowth() {
        popGrowthRate = 0.05; // TODO Better formula for growth rate (based on env factors)
    }

    private void resultingEvents() {
        // TODO ungodly if else statements to determine what fires.
        ArrayList<Event> happened = new ArrayList<>();
        results = happened;
    }
}

