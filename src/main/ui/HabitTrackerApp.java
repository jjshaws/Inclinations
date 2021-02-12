package ui;

import model.Entry;
import model.HabitEntry;
import model.HabitLog;
import model.HabitTracker;
import java.util.Scanner;

//Habit Tracker Application
public class HabitTrackerApp {
    private HabitTracker tracker;
    private Scanner input;

    //EFFECTS: Runs the tracker application
    public HabitTrackerApp() {
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

    }

    //REQUIRES: date in YYYY-MM-DD format
    //EFFECTS: constructs an Entry
    public void addEntry() {
        Entry entryToAdd;

        System.out.println("Enter the date in YYYY-MM-DD format:");
        String date = input.nextLine();
        System.out.println("Enter your entry for the day:");
        String entry = input.nextLine();
        HabitLog habits = checkHabits();

        entryToAdd = new Entry(date, entry, habits);

        tracker.addEntry(entryToAdd);
    }

    // EFFECTS:
    private HabitLog checkHabits() {
        HabitLog habits = tracker.getDefaultHabitLog();
        HabitLog updatedHabits = new HabitLog();

        for (int i = 1; i <= habits.length(); i++) {
            Boolean completion = false;

            HabitEntry habit  = habits.getNextHabitEntry();

            System.out.println("Did you " + habit.getHabit() + "?");

            completion = selectYesOrNo();

            HabitEntry updatedHabit = new HabitEntry(habit.getHabit(), completion);

            updatedHabits.addHabitEntry(updatedHabit);

        }

        System.out.println("All habits entered. Returning to entry.");

        return updatedHabits;
    }

    private void init() {
        tracker = new HabitTracker();
        input = new Scanner(System.in);
    }

    // EFFECTS: prompts user to choose from yes or no
    private Boolean selectYesOrNo() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("Choose an action:");
            System.out.println("y - yes");
            System.out.println("n - no");
            selection = input.next();
            selection = selection.toLowerCase();
        }


        if (selection.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
