package model;

//Represents a day's journal entry with a date, written entry and habit log
public class Entry {
    private String date;          //the date in YYYY-MM-DD format
    private String note;          //the written note attached to the entry
    private HabitLog habits;      //the Log of all active habits and if they were accomplished today

    //REQUIRES: date in YYYY-MM-DD format
    //EFFECTS: constructs an Entry
    public Entry(String date, String note, HabitLog habits) {
        this.date = date;
        this.note = note;
        this.habits = habits;
    }

    //EFFECTS Returns the written note of the entry
    public String getNote() {
        return note;
    }

    //EFFECTS Returns the date of the entry
    public String getEntryDate() {
        return date;
    }

    //EFFECTS: Checks if the date of the entry matches the string
    public boolean dateEquals(String s) {
        return date.equals(s);
    }

    //EFFECTS: Changes the note of the entry
    public boolean setEntryDate(String date) {
        this.date = date;
        return true;
    }

    //EFFECTS Returns the associated HabitLog
    public HabitLog getHabits() {
        return habits;
    }
}

