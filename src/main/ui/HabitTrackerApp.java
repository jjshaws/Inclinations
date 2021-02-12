package ui;

import com.sun.demo.jvmti.hprof.Tracker;
import model.Entry;
import model.HabitEntry;
import model.HabitLog;
import model.HabitTracker;
import java.util.Scanner;

//Habit Tracker Application
public class HabitTrackerApp {
    private Scanner input;
    private HabitTracker tracker = new HabitTracker();

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
        System.out.println("What would you like to do?");
        System.out.println("1 - Delete an entry");
        System.out.println("2 - Modify the note of a previous entry");
        System.out.println("3 - Modify the completion of a previous entry");
        System.out.println("4 - Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMainTracker() {
        boolean keepGoing = true;
        String command = null;

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
    // EFFECTS: processes user input
    private void runHabitsTracker() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

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
        if (command.equals("1")) {
            addEntry();
        } else if (command.equals("2")) {
            viewHabits();
        } else if (command.equals("3")) {
            processEntriesMenu();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: takes a user command for the Habits Menu
    private void processHabitsCommand(String command) {
        if (command.equals("1")) {
            addHabit();
        } else if (command.equals("2")) {
            deleteHabit();
        } else if (command.equals("3")) {
            runMainTracker();
        } else {
            System.out.println("Selection not valid...");
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
            System.out.println("Selection not valid... Must be between 0 and " + tracker.getDefaultHabitLogLength());
        } else if (intCommand <= tracker.getDefaultHabitsMaxSize() && intCommand > 0) {
            tracker.removeDefaultHabitEntryWithIndex(intCommand);
        } else {
            System.out.println("Selection not valid...");
        }
    }

//    // MODIFIES: this
//    // EFFECTS: takes a user command for the Previous Entries Menu
//    private void processPreviousEntriesCommand(String command) {
//        if (command.equals("1")) {
//            deleteEntry();
//        } else if (command.equals("2")) {
//            modifyNoteEntry();
//        } else if (command.equals("3")) {
//            ModifyCompletionEntry();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }



    // MODIFIES: this
    // EFFECTS: Shows the user the active habits and prompts for habits
    private void viewHabits() {
        System.out.println("Here are all active habits.");
        System.out.println("These will be included in your new entries:");
        //HabitLog habits = tracker.getDefaultHabitLog();

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

    public void processHabitsMenu() {
        boolean keepGoing = true;
        String command = null;

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

    public void processDeleteHabitMenu() {
        boolean keepGoing = true;
        String command = null;

        if (tracker.getDefaultHabitLogLength() == 0) {
            System.out.println("You have no habits in your default list! Please add some");
        } else {
            displayDeleteHabitsMenu();
            command = input.next();
            processDeleteHabitsCommand(command);
        }


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

    public void deleteHabit() {
        processDeleteHabitMenu();
    }

    public void processEntriesMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayEntriesMenu();
            command = input.next();

            if (command.equals("3")) {
                keepGoing = false;
            } else {
                processHabitsCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: ensures that the entered string is in YYYY-MM-DD format
    public String getValidDate() {
        String s = input.next();

        while (!s.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            System.out.println("Invalid format");
            System.out.println("Enter the date in YYYY-MM-DD format:");
            s = input.next();
        }
        return s;
    }

    // MODIFIES: tracker
    // EFFECTS: constructs an Entry
    public void addEntry() {
        Entry entryToAdd;

        System.out.println("Enter the date in YYYY-MM-DD format:");
        String date = getValidDate();
        System.out.println("Enter your entry for the day:");
        String entry = input.next();
        HabitLog habits = checkHabits();

        entryToAdd = new Entry(date, entry, habits);

        tracker.addEntry(entryToAdd);

    }

    // EFFECTS:
    private HabitLog checkHabits() {
        HabitLog habits = tracker.getDefaultHabitLog();

        for (int i = 1; i <= tracker.getDefaultHabitLogLength(); i++) {
            Boolean completion;

            HabitEntry habit = habits.getNextHabitEntry();

            System.out.println("Did you accomplish task \"" + habit.getHabit() + "\"?");

            completion = selectYesOrNo();

            HabitEntry updatedHabit = new HabitEntry(habit.getHabit(), completion);

            habits.addHabitEntry(updatedHabit);

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
