package model;

import java.util.LinkedList;

//Represents a list of HabitEntries for a particular day
public class HabitLog implements Cloneable {
    public static final int MAX_SIZE = 10;
    private final LinkedList<HabitEntry> habits;

    //EFFECTS: Constructs a log of Habits for an entry with the name of the habit and (T/F) for its completion
    public HabitLog() {
        habits = new LinkedList<>();
    }

    //MODIFIES:this
    //EFFECTS: Adds the HabitEntry to the HabitLog if it is not full and returns true is it can , false if full
    public boolean addHabitEntry(HabitEntry h) {
        if (habits.size() >= MAX_SIZE) {
            System.out.println();
            System.out.println("Max habits reached. Delete a habit to make more space");
            return false;
        } else {
            habits.add(h);
            return true;
        }
    }

    //EFFECTS: Produces all names of habits, numbered
    public void listHabitNames() {
        int i = 1;
        for (HabitEntry h : habits) {
            System.out.println(i + "- " + h.getHabit());
            i++;
        }
    }

    //REQUIRES: this isn't empty
    //MODIFIES: this
    //EFFECTS: removes the incident at the front of the list
    public HabitEntry getNextHabitEntry() {
        return habits.poll();
    }

    //MODIFIES: this
    //EFFECTS: removes the HabitEntry from the HabitLog, returns true. Returns false if not present
    public void removeHabitAtPosition(int i) {
        habits.remove(i - 1);
    }

    //EFFECTS: Returns the length of the HabitLog
    public int length() {
        return habits.size();
    }


    //EFFECTS: Returns the constant MAX_SIZE
    public int getMaxSize() {
        return MAX_SIZE;
    }
}
