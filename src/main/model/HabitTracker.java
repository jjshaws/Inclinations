package model;

import com.sun.demo.jvmti.hprof.Tracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//Represents all entries to the habit tacker
public class HabitTracker {
    private ArrayList<Entry> entries;
    private HabitLog defaultHabits = new HabitLog();


    public HabitTracker() {
        entries = new ArrayList<>();
    }


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

    //EFFECT: gets the default habits
    public void listDefaultHabits() {
        defaultHabits.listHabitNames();
    }

    //EFFECT: gets the dates of all entries in added
    public void listEntryDates() {

        for (Entry e : entries) {
            System.out.println(e.getEntryDate());
        }
    }

    public int getDefaultHabitsMaxSize() {
        return defaultHabits.getMaxSize();
    }


    public boolean doesEntryExist(String date) {
        for (Entry e : entries) {
            if (e.dateEquals(date)) {
                return true;
            }
        }
        return false;
    }

    public boolean printEntryContents(String date) {
        for (Entry e : entries) {
            if (e.dateEquals(date)) {
                e.printEntry();
            }
        }
        return false;
    }
}