package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class HabitTrackerTest {
    HabitTracker app;

    @BeforeEach
    public void setup() {
        app = new HabitTracker();
        ArrayList<Entry> entries;

    }

    //checks if entries can be added correctly
    @Test
    public void testGetEntries() {
        Entry entry;
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        entry = new Entry("2020-12-28", "had a good day", habits);

        app.addEntry(entry);
    }

    //checks if entries can be added
    @Test
    public void testAddEntry() {
        Entry entry;
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        entry = new Entry("2020-12-28", "had a good day", habits);

        app.addEntry(entry);
    }


    //Checks that adding Habit entries gives the correct length
    @Test
    public void testAddDefaultHabitEntry() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        app.addDefaultHabitEntry(entry1);
        app.addDefaultHabitEntry(entry2);

        HabitLog test = new HabitLog();
        test.addHabitEntry(entry1);
        test.addHabitEntry(entry2);

       assertEquals(app.getDefaultHabitLogLength(), 2);
    }

    //Checks that adding Habit entries gives the correct length
    @Test
    public void testGetDefaultHabitLog() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        app.addDefaultHabitEntry(entry1);
        app.addDefaultHabitEntry(entry2);


        HabitLog test = new HabitLog();
        test.addHabitEntry(entry1);
        test.addHabitEntry(entry2);

        ArrayList<Entry> test2 = new ArrayList<Entry>();
        test2 = app.getEntries();

        HabitLog testCase = app.getDefaultHabitLog();
        assertEquals(testCase.length(), 2);
        assertEquals(test2.size(), 0);
    }

    //Tests that removing default Habit Entries yields the correct lengths
    @Test
    public void testRemoveDefaultHabitEntryWithIndex() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        app.addDefaultHabitEntry(entry1);
        app.addDefaultHabitEntry(entry2);

        HabitLog test = new HabitLog();
        test.addHabitEntry(entry1);
        test.addHabitEntry(entry2);

        app.removeDefaultHabitEntryWithIndex(1);

        assertEquals(app.getDefaultHabitLogLength(), 1);
    }

    //Checks that multiple entries stack to sum of to the correct total length
    @Test
    public void testGetNumEntries() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);
        HabitEntry entry3 = new HabitEntry("Cool", true);


        app.addDefaultHabitEntry(entry1);
        app.addDefaultHabitEntry(entry2);
        app.addDefaultHabitEntry(entry3);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);
        habits.addHabitEntry(entry3);

        Entry journEntry1 = new Entry("2020-12-28", "had a good day", habits);
        Entry journEntry2 = new Entry("2020-12-30", "had a bad day", habits);
        Entry journEntry3 = new Entry("2020-12-28", "had a mediocre day", habits);

        app.addEntry(journEntry1);
        app.addEntry(journEntry2);
        app.addEntry(journEntry3);

        assertEquals(app.getNumEntries(), 3);
    }


    //Checks that the length is read correctly
    @Test
    public void testGetDefaultHabitLogLength() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);
        HabitEntry entry3 = new HabitEntry("Cool", true);
        HabitEntry entry4 = new HabitEntry("Bake", false);

        app.addDefaultHabitEntry(entry1);
        app.addDefaultHabitEntry(entry2);
        app.addDefaultHabitEntry(entry3);
        app.addDefaultHabitEntry(entry4);

        assertEquals(app.getDefaultHabitLogLength(), 4);
    }

    //Checks that the max length of habits is read correctly
    @Test
    public void testGetDefaultHabitsMaxSize(){
        assertEquals(app.getDefaultHabitsMaxSize(), 10);
    }

}
