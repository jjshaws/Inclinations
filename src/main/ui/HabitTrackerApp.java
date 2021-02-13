package ui;

import model.Entry;
import model.HabitEntry;
import model.HabitLog;
import model.HabitTracker;

import java.util.ArrayList;
import java.util.Scanner;

//Habit Tracker Application
public class HabitTrackerApp {
    private Scanner input;
    private final HabitTracker tracker = new HabitTracker();

    //EFFECTS: Runs the tracker application
    public HabitTrackerApp() {
        runMainTracker();
    }

    // EFFECTS: Welcomes user
    private void welcome() {
        System.out.println();
        System.out.println("Welcome to Inclination, your daily habit tracker");
        System.out.println();
    }

    // EFFECTS: Displays main menu
    private void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - Add an Entry");
        System.out.println("2 - View and modify your currently tracked habits");
        System.out.println("3 - View past entries");
        System.out.println("4 - Quit");

    }

    // EFFECTS: Displays habits menu
    private void displayHabitsMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - Add a habit");
        System.out.println("2 - Delete a habit");
        System.out.println("3 - Return to the main menu");

    }

    // EFFECTS: Displays previous entries menu
    private void displayEntriesMenu() {
        System.out.println();
        System.out.println("Enter the date of the entry that you would you like to view, or r to return to main menu");
        listEntryDates();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for the main menu
    private void runMainTracker() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            if (command.equals("4")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for the main menu
    private void processMainCommand(String command) {
        switch (command) {
            case "1":
                addEntry();
                break;
            case "2":
                viewHabits();
                break;
            case "3":
                processEntriesMenu();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for the Habits Menu
    private void processHabitsCommand(String command) {
        switch (command) {
            case "1":
                addHabit();
                break;
            case "2":
                deleteHabit();
                break;
            case "3":
                runMainTracker();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for the Delete Habits Menu
    private void processDeleteHabitsCommand(String command) {
        while (!command.matches("[0-9]+")) {
            System.out.println("Invalid entry. Please enter a number");
            command = input.next();
        }
        int intCommand = Integer.parseInt(command);
        if (intCommand > tracker.getDefaultHabitLogLength() || intCommand < 1) {
            System.out.println("Selection not valid... Must be between 1 and " + tracker.getDefaultHabitLogLength());
        } else if (intCommand <= tracker.getDefaultHabitsMaxSize()) {
            tracker.removeDefaultHabitEntryWithIndex(intCommand);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for Habit Processing menu
    public void processHabitsMenu() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayHabitsMenu();
            command = input.next();

            if (command.equals("3")) {
                keepGoing = false;
            } else {
                processHabitsCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for delete Habit Processing menu
    public void processDeleteHabitMenu() {
        String command;
        if (tracker.getDefaultHabitLogLength() == 0) {
            System.out.println("You have no habits in your default list! Please add some");
        } else {
            displayDeleteHabitsMenu();
            command = input.next();
            processDeleteHabitsCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: Shows the user the active habits and prompts for habits
    private void viewHabits() {
        System.out.println("Here are all active habits.");
        System.out.println("These will be included in your new entries:");
        if (tracker.getDefaultHabitLogLength() == 0) {
            System.out.println("There are no active habits!");
            System.out.println("Would you like to add one?");

            if (selectYesOrNo()) {
                addHabit();
            } else {
                displayMainMenu();
            }
        } else {
            tracker.listDefaultHabits();
        }
        processHabitsMenu();
    }

    public void displayDeleteHabitsMenu() {
        System.out.println("What habit would you like to delete?");
        tracker.listDefaultHabits();
        System.out.println("Enter the number corresponding to your habit:");
    }

    // MODIFIES: Tracker's default habit log
    // EFFECTS: Shows the user the active habits and prompts for habits
    // REQUIRES: No redundant habits
    public void addHabit() {
        String newHabitName;

        System.out.println("What is the name of your habit?");
        newHabitName = input.next();

        HabitEntry nextHabit = new HabitEntry(newHabitName, false);
        tracker.addDefaultHabitEntry(nextHabit);

        System.out.println("Habit " + newHabitName + " successfully added to the daily default habits.");
        System.out.println("Returning to the habits menu");
    }

    // MODIFIES: this
    // EFFECTS: Deletes a habitEntry from this
    public void deleteHabit() {
        processDeleteHabitMenu();
    }

    //EFFECT: gets the dates of all entries in added
    public void listEntryDates() {
        ArrayList<Entry> entries = tracker.getEntries();
        for (Entry e : entries) {
            System.out.println(e.getEntryDate());
        }
    }

    // EFFECTS: Checks if an entry of a given date string exists within this
    public boolean doesEntryExist(String date) {
        ArrayList<Entry> entries = tracker.getEntries();
        for (Entry e : entries) {
            if (e.dateEquals(date)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: processes the user's input for viewing past entries and provides a way back to main menu
    public void processEntriesMenu() {
        boolean keepGoing = true;
        String command;

        if (tracker.getNumEntries() == 0) {
            System.out.println();
            System.out.println("You have no Entries in your default list! Please add some");
            keepGoing = false;
        }

        while (keepGoing) {
            displayEntriesMenu();
            command = getValidEntriesCommand();

            if (command.equals("r")) {
                keepGoing = false;
            } else {
                processDateCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input when searching for entries by a date string
    public void processDateCommand(String command) {
        boolean exists = doesEntryExist(command);

        if (exists) {
            viewEntry(command);
        } else {
            System.out.println("There is no entry for the given date");
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks whether the user inputs either return to return to main menu or date in YYYY-MM-DD
    public String getValidEntriesCommand() {
        String s = input.next();

        while (!s.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$") && !s.matches("r")) {
            System.out.println("Invalid format");
            System.out.println("Enter the date in YYYY-MM-DD format, or \"r\" to return");
            s = input.next();
        }
        return s;
    }

    // EFFECTS: prints entries found during the previous entry search
    public void viewEntry(String date) {
        System.out.println("Entry found");
        System.out.println();
        printEntryContents(date);
    }

    // MODIFIES: this
    // EFFECTS: given the date of the entry, prints out all of an entry's information
    public void printEntryContents(String date) {
        ArrayList<Entry> entries = tracker.getEntries();
        for (Entry e : entries) {
            if (e.dateEquals(date)) {

                System.out.println("Date: " + e.getEntryDate());
                System.out.println();
                System.out.println("Note:");
                System.out.println(e.getNote());
                System.out.println();
                System.out.println("Habits:");
                printHabits(e);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Prints the habits and if they were completed
    public void printHabits(Entry e) {
        HabitLog habits = e.getHabits();
        int length = habits.length();

        for (int i = 1; i <= length; i++) {
            HabitEntry h = habits.getNextHabitEntry();

            System.out.println(h.getHabit());
            if (h.getCompletion()) {
                System.out.println("Complete");
            } else {
                System.out.println("Incomplete");
            }
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: ensures that the entered string is in YYYY-MM-DD format or YYYY-M-D
    public String getValidDate() {
        String s = input.next();

        while (!s.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            System.out.println("Invalid format");
            System.out.println("Enter the date in YYYY-MM-DD format:");
            s = input.next();
        }
        return s;
    }

    // MODIFIES: tracker and this
    // EFFECTS: constructs an Entry
    public void addEntry() {
        Entry entryToAdd;

        System.out.println("Enter the date in YYYY-MM-DD format:");
        String date = getValidDate();
        input.nextLine();

        System.out.println("Enter your entry for the day:");
        String entry = input.nextLine();

        HabitLog habits = checkHabits();

        entryToAdd = new Entry(date, entry, habits);

        tracker.addEntry(entryToAdd);

    }

    // MODIFIES: tracker
    // EFFECTS: prompts the user for all tasks listed on the default task list
    private HabitLog checkHabits()  {
        HabitLog habits;
        habits = tracker.getDefaultHabitLog();

        for (int i = 1; i <= tracker.getDefaultHabitLogLength(); i++) {
            Boolean completion;
            HabitEntry habit = habits.getNextHabitEntry();
            System.out.println("Did you accomplish task \"" + habit.getHabit() + "\"?");
            completion = selectYesOrNo();
            habits.addHabitEntry(new HabitEntry(habit.getHabit(), completion));
        }
        System.out.println("All habits entered.");
        System.out.println("Returning to the main menu");
        return habits;
    }

    // MODIFIES: this
    // EFFECTS: Welcomes user, initializes the HabitTracker and Scanner
    private void init() {
        welcome();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
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
        return selection.equals("y");
    }
}
