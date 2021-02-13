package model;

import java.util.ArrayList;

//Represents all entries to the habit tacker
public class HabitTracker {
    private ArrayList<Entry> entries;
    private HabitLog defaultHabits = new HabitLog();

    public HabitTracker() {
        entries = new ArrayList<>();
    }

    // EFFECT: gets the entries
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    // MODIFIES: entries
    // EFFECT: adds an entry to this HabitTracker
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    // MODIFIES: This
    //EFFECT: set the habits that each new entry will include
    public void setDefaultHabitLog(HabitLog defaultHabits) {
        this.defaultHabits = defaultHabits;
    }

    // MODIFIES: This
    //EFFECT: add a HabitEntry that each new entry will include
    public void addDefaultHabitEntry(HabitEntry defaultHabit) {
        defaultHabits.addHabitEntry(defaultHabit);
    }

    public void removeDefaultHabitEntryWithIndex(int i) {
        defaultHabits.removeHabitAtPosition(i);
    }

    // MODIFIES: This
    //EFFECT: gets the default habits
    public int getNumEntries() {
        return entries.size();
    }

    // MODIFIES: This
    //EFFECT: gets the default habits
    public HabitLog getDefaultHabitLog() {
        return defaultHabits;
    }

    // MODIFIES: This
    //EFFECT: gets the default habits
    public int getDefaultHabitLogLength() {
        return defaultHabits.length();
    }


    public int getDefaultHabitsMaxSize() {
        return defaultHabits.getMaxSize();
    }

}