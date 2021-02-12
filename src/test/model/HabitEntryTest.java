package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//For testing all of the HabitEntry Methods
public class HabitEntryTest {
    HabitEntry habit;

    @BeforeEach
    public void setup() {
        habit = new HabitEntry("Reading", true);
    }

    //Check that the correct habit string is returned
    @Test
    public void testGetHabit() {
        assertEquals(habit.getHabit(), "Reading");
    }

    //Check that the completion field is correctly stored and returned
    @Test
    public void testGetCompletion() {
        assertTrue(habit.getCompletion());
    }
}
