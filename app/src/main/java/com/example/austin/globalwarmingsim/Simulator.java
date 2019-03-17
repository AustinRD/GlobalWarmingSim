package com.example.austin.globalwarmingsim;

import java.util.ArrayList;

public class Simulator {
    public Region[] regions; // 4 regions
    public ArrayList<Event> events;
    public ArrayList<Event> results;
    int development; // 1-10 scale. 1 good 10 bad
    double cO2; // In parts/million
    double dcO2;
    double avgTemp; // In celsius
    double davgTemp;
    double seaLevel; // In ft increase from game start
    double dseaLevel;
    int population; // nation
    int dPopulation;
    int date; // in 1 yr increments ENDS IN 2030
    int animalSpecies;

    public Simulator(int diff) {
        events = new ArrayList();
        switch (diff) {
            default:
            case(0):
                development = 5;
                cO2 = 335.0; // ppm
                avgTemp = 57.5; // fahrenheit
                seaLevel = 0; // ft from starting
                date = 1980;
        }
    }

    /**
     * Increment the simulation by one year.
     * @param es ArrayList of events that happened during this turn.
     */
    public void tick(ArrayList<Event> es) {
        ArrayList<Event>[] stagedEvents = divvyEvents(es);
        events.addAll(stagedEvents[4]);
        applyEvents(); // Apply global events.
        ArrayList<Event> resultingEvents;
        resultingEvents = tickRegions(stagedEvents);
        updateGlobals();
        resultingEvents.addAll(resultingEvents());
        resultingEvents = filterDates(resultingEvents);
        results = resultingEvents;
        date++;
    }

    /**
     * Set the difficulty of the simulation. (ONLY CALL ONCE)
     * @param d Difficulty: 0 = NORMAL, 1 = HARD.
     */
    public void setDifficulty() {
        for (Region region : regions) {
            region.setDifficulty();
        }
    }

    private void updateGlobals() {
        // Update ones straight from regions
        int newPop = 0;
        int newDpop = 0;
        int newDcO2 = 0;
        for(Region r : regions) {
            newPop += r.population;
            newDpop += r.dPopulation;
            newDcO2 += r.dcO2;
        }
        population = newPop;
        dPopulation = newDpop;
        dcO2 = newDcO2;
        cO2 += dcO2;
        updateTemp();
        updateSeaLevel();
        updateAnimals();
    }

    private ArrayList<Event>[] divvyEvents(ArrayList<Event> es) {
        // Divvy events to corresponding index in array of staged arraylists to send to regions.
        ArrayList<Event>[] stagedEvents = new ArrayList[5];
        for (int i = 0; i < stagedEvents.length; i++) {
            stagedEvents[i] = new ArrayList();
        }
        for (int i = 0; i < regions.length; i++) {
            for (Event event : es) {
                if (event.appliesTo == regions[i]) {
                    stagedEvents[i].add(event);
                }
                if (event.appliesTo == null) {
                    stagedEvents[4].add(event);
                }
            }
        }
        return stagedEvents;
    }

    private ArrayList<Event> tickRegions(ArrayList<Event>[] stagedEvents) {
        ArrayList<Event> happened = new ArrayList();
        int i = 0;
        for (Region region : regions) {
            region.tick(stagedEvents[i]);
            happened.addAll(region.results);
            i++;
        }
        return happened;
    }

    private void applyEvents() {
        for(Event event : events) { // apply events whose time has come.
            if (event.triggerTime <= date) {
                avgTemp = event.avgTemp;
                seaLevel = event.seaLevel;
            }
        }
    }

    private void updateTemp() {
        davgTemp = (dcO2 * (cO2 / avgTemp) * 0.005);
        avgTemp += davgTemp;
    }

    private void updateSeaLevel() {
        dseaLevel = ((avgTemp % 57.5) * 0.01); // Just kinda made up.
        seaLevel = (int)((avgTemp % 57.5) * 3); // this works nice.
    }

    private void updateAnimals() {
        int newAnimals = 0;
        for(Region r : regions) {
            newAnimals += r.animalSpecies;
        }
        animalSpecies = newAnimals;
    }

    private ArrayList<Event> resultingEvents() {
        // TODO ungodly if else statements to determine what fires.
        ArrayList<Event> happened = new ArrayList();
        return happened;
    }

    private ArrayList<Event> filterDates(ArrayList<Event> es) {
        ArrayList<Event> filtered = new ArrayList<>();
        for (Event event : es) {
            if (event.triggerTime == date) {
                filtered.add(event);
            }
        }
        return filtered;
    }

}
