package model;

import java.util.ArrayList;

//Represents all entries to the habit tacker
public class HabitTracker {
    private ArrayList<Entry> entries;

    public HabitTracker() {
        entries = new ArrayList<>();
    }

    private HabitLog defaultHabits;

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    //EFFECT: set the habits that each new entry will include
    public void setDefaultHabitLog(HabitLog defaultHabits) {
        this.defaultHabits = defaultHabits;
    }

    //EFFECT: gets the default habits
    public HabitLog getDefaultHabitLog() {
        return defaultHabits;
    }

}
