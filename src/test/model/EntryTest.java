package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EntryTest {
    Entry entry;

    @BeforeEach
    public void setup() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        entry = new Entry("2020-12-28", "had a good day", habits);
    }

    //General testing of basic getters and setters
    @Test
    public void testGettersAndSetters() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        assertEquals(entry.getNote(), "had a good day");

        assertEquals(entry.getEntryDate(), "2020-12-28");

        Entry result = new Entry("2020-12-25", "had a good day", habits);
        assertTrue(entry.setEntryDate("2020-12-25"));

        assertTrue(entry.dateEquals("2020-12-25"));
        assertFalse(entry.dateEquals("2020-11-25"));
    }

    //Test to make sure that the Habit:og is being correctly included
    @Test
    public void testGetHabits(){
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        entry = new Entry("2020-12-28", "had a good day", habits);

        assertEquals(entry.getHabits(), habits);
    }

}
