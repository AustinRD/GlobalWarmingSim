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
    private double badFactEmission = 0.03;
    private double goodFactEmission = 0.005;

    /**
     * Create a new region.
     * @param n Display name of region.
     * @param pop Initial population of region.
     * @param s Pointer to simulator.
     */
    public Region(String n, int pop, Simulator s) {
        sim = s;
        name = n;
        population = pop;
        events = new ArrayList();
        results = new ArrayList<>();
        //neighbors = ns;
    }

    /**
     * Set the difficulty of the simulation. (ONLY CALL ONCE)
     * //@param d Difficulty: 0 = NORMAL, 1 = HARD.
     */
    public void setDifficulty() {
        switch (DataHolder.difficulty) {
            default:
            case(0): // NORMAL DIFFICULTY, THIS IS A REAL AS I COULD GET IT.
                badIndustry = 10;
                goodIndustry = 2;
                badEnergy = 10;
                goodEnergy = 2;
                animalSpecies = 100;
                development = 5;
                sim.pollution = 20;
                break;
            case(1): // HARD (Untested results.)
                badIndustry = 20;
                goodIndustry = 0;
                badEnergy = 12;
                goodEnergy = 0;
                animalSpecies = 80;
                development = 5;
                badFactEmission *= 2;
                sim.pollution = 30;
                break;
        }
        updateDeltacO2();
        updatePopGrowth();
        updateDeltaPopulation();
    }

    public void tick(ArrayList<Event> newEvents) {
        //results = new ArrayList(); // make clean list for results.
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
        ArrayList<Event> triggeredEventsNow = new ArrayList<>();
        for(Event event : events) {
            if (event.triggerTime == sim.date) { // apply events whose time has come.
                applyEvent(event);
                results.add(event);
                if (event.resultingEvents != null) {
                    for (Event ev : event.resultingEvents) {
                        if (ev.triggerTime == sim.date) {
                            applyEvent(ev);
                            results.add(ev);
                        }
                        else if (ev.triggerTime > sim.date) {
                            triggeredEventsNow.add(ev);
                        }
                        else {}// Event has passed due date and can be ignored.
                    }
                }
            }
        }
        events.addAll(triggeredEventsNow);
    }

    private void applyEvent(Event event) {
        goodIndustry += event.goodIndustry;
        badIndustry += event.badIndustry;
        badEnergy += event.badEnergy;
        goodEnergy += event.goodEnergy;
        population += event.population;
        popGrowthRate += event.popGrowthRate;
        animalSpecies += event.animalSpecies;
    }

    private void updateLocals() {
        population += dPopulation; // Is that it?

    }

    private void updateDeltacO2() {
        dcO2 = (badFactEmission * badIndustry) +
                (goodFactEmission * goodIndustry) +
                (population * 0.000000005);
    }

    private void updateDeltaPopulation() {
        dPopulation = (int)(population * popGrowthRate);
    }

    private void updatePopGrowth() {
        popGrowthRate = 0.015; // TODO Better formula for growth rate (based on env factors)
    }

    private void resultingEvents() {
        // TODO ungodly if else statements to determine what fires.
        ArrayList<Event> happened = new ArrayList<>();
        results.addAll(happened);
    }
}
