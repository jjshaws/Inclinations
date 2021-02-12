package model;


import java.util.LinkedList;
import java.util.List;
import java.io.*;

//Represents a list of HabitEntries for a particular day
public class HabitLog {
    public static final int MAX_SIZE = 10;

    private LinkedList<HabitEntry> habits;

    //EFFECTS: Constructs a log of Habits for an entry with the name of the habit and (T/F) for its completion
    public HabitLog() {
        habits = new LinkedList<>();
    }

    //MODIFIES:this
    //EFFECTS: Adds the HabitEntry to the HabitLog if it is not full and returns true is it can , false if full
    public boolean addHabitEntry(HabitEntry h) {
        if (habits.size() >= MAX_SIZE) {
            return false;
        } else {
            habits.add(h);
            return true;
        }
    }

    //EFFECTS: Produces all names of habits, numbered
    public void listHabitNames() {
        int i = 1;
        for (HabitEntry h: habits) {
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


//    //EFFECTS: Returns a list of all of the habits for this log
//    public List<String> getLogsHabits() {
//        List<String> list = new LinkedList<String>();
//        for (HabitEntry entry : habits) {
//            list.add(entry.getHabit());
//        }
//        return list;
//    }

    //EFFECTS: Returns the length of the HabitLog
    public int length() {
        return habits.size();
    }

    // EFFECTS: Prints the habits and if they were completed
    public void printHabits() {
        for (HabitEntry h: habits) {
            System.out.println(h.getHabit());
            if (h.getCompletion()) {
                System.out.println("Complete");
            } else {
                System.out.println("Incomplete");
            }
            System.out.println();
        }
    }

    //EFFECTS: Returns the number of completed habits for a given HabitLog
    public int getNumCompletions() {
        int completions = 0;

        for (HabitEntry entry: habits) {
            if (entry.getCompletion()) {
                completions++;
            }
        }
        return completions;
    }

    //EFFECTS: Returns the constant MAX_SIZE
    public int getMaxSize() {
        return MAX_SIZE;
    }

    //EFFECTS: Returns the number of incomplete habits for a given HabitLog
    public int getNumIncomplete() {
        return length() - getNumCompletions();
    }
}
