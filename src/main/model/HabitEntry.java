package model;

//Represents a habit and if it has been accomplished on the day of the entry
public class HabitEntry {
    private String habit;
    private Boolean completion;

    //EFFECTS: constructs a HabitEntry, where the initial state is incomplete
    //REQUIRES: First letter of Habit to be capitalized
    public HabitEntry(String habit, Boolean isAccomplished) {
        this.habit = habit;
        this.completion = isAccomplished;
    }

    //EFFECTS: gets the habit name/title
    public String getHabit() {
        return this.habit;
    }

    //EFFECTS: gets the stat
    public boolean getCompletion() {
        return this.completion;
    }

}
