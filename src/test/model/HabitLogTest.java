package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//For testing all of the HabitLog methods
public class HabitLogTest {
    HabitLog log;

    @BeforeEach
    public void setup(){
        log = new HabitLog();
    }

    //Check adding to an empty Habit log
    @Test
    public void testAddHabitEntryEmpty() {
        HabitEntry entry1 = new HabitEntry("Cook", true);

        assertTrue(log.addHabitEntry(entry1));
    }

    //Check adding to a non-empty, non full Habit Log
    @Test
    public void testAddHabitEntryWithOne() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        log.addHabitEntry(entry1);

        assertTrue(log.addHabitEntry(entry2));
    }

    //Check that attempting to add another HabitEntry to the HabitLog returns false
    @Test
    public void testAddHabitEntryWithMax() {
        for (int i = 1; i <= log.getMaxSize(); i++) {
            log.addHabitEntry(new HabitEntry("Bake", false));
        }

        assertFalse(log.addHabitEntry(new HabitEntry("Bake", false)));
    }

    //Check that HabitLogs of length 0 are registered
    @Test
    public void testLengthEmpty() {
        assertEquals(log.length(), 0);
    }

    //Check that HabitLogs of nonzero length are registered
    @Test
    public void testLengthTwo() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        log.addHabitEntry(entry1);
        log.addHabitEntry(entry2);

        assertEquals(log.length(), 2);
    }

    //Check if getting a HabitEntry returns the correct habit and removes it from the end
    @Test
    public void testGetNextHabitEntryOne() {
        HabitEntry entry1 = new HabitEntry("Cook", true);

        log.addHabitEntry(entry1);

        assertEquals(log.getNextHabitEntry(), entry1);
        assertEquals(log.length(), 0);
    }

    //Check if getting a HabitEntry returns the correct habit with other elements in Log and removes it from the end
    @Test
    public void testGetNextHabitEntry() {
        HabitEntry entry1 = new HabitEntry("Cook", true);
        HabitEntry entry2 = new HabitEntry("Clean", false);

        log.addHabitEntry(entry1);
        log.addHabitEntry(entry2);

        assertEquals(log.getNextHabitEntry(), entry1);
        assertEquals(log.length(), 1);
    }

    //Checking to see if remaining elements are as expected when removing center
    @Test
    public void testRemoveHabitAtPosition() {
        HabitEntry entry_1 = new HabitEntry("Playing Guitar", false);
        HabitEntry entry_2 = new HabitEntry("Playing flute", false);
        HabitEntry entry_3 = new HabitEntry("Playing recorder", true);

        log.addHabitEntry(entry_1);
        log.addHabitEntry(entry_2);
        log.addHabitEntry(entry_3);

        log.removeHabitAtPosition(2);

        assertEquals(log.getNextHabitEntry(), entry_1);
        assertEquals(log.getNextHabitEntry(), entry_3);
    }

}
