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


    @Test
    public void testGettersAndSetters() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        HabitLog habits = new HabitLog();
        habits.addHabitEntry(entry1);
        habits.addHabitEntry(entry2);

        assertEquals(entry.getNote(), "had a good day");

        assertEquals(entry.getEntryDate(), "2020-12-28");
    }

}
