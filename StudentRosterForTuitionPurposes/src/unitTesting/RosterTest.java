package unitTesting;

import project.two.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void add() {
        // Test to add 1 student
        Student studentOne = new Student("Person one", Major.CS, 12);
        Roster roster = new Roster();
        assertTrue(roster.add(studentOne)); //test to see if add works for student

        //Test to see if it can grow/add students of different classes

        International studentTwo = new International("Person two", Major.IT, 15, false);
        assertTrue(roster.add(studentTwo)); //test to see if add works for international
        Resident studentThree = new Resident("Person three", Major.EE, 16);
        assertTrue(roster.add(studentThree)); //test to see if add works for resident
        NonResident studentFour = new NonResident("Person four", Major.ME, 18);
        assertTrue(roster.add(studentFour)); //test to see if add works for nonResident
        TriState studentFive = new TriState("Person five", Major.CS, 9, "NY");
        assertTrue(roster.add(studentFive)); //test to see if grow works and add for triState works

        // Test to make sure it does not add duplicate students
        assertFalse(roster.add(studentFive));

    }

    @Test
    public void remove() {
        //initiializing a new roster
        Student studentOne = new Student("Person one", Major.CS, 12);
        Roster roster = new Roster();
        roster.add(studentOne);
        International studentTwo = new International("Person two", Major.IT, 15, false);
        roster.add(studentTwo);
        Resident studentThree = new Resident("Person three", Major.EE, 16);
        roster.add(studentThree);
        NonResident studentFour = new NonResident("Person four", Major.ME, 18);
        roster.add(studentFour);
        TriState studentFive = new TriState("Person five", Major.CS, 9, "NY");
        roster.add(studentFive);
        Student studentSix = new Student("Person six", Major.BA, 7);
        roster.add(studentSix);
        Student studentEight = new Student("Person seven", Major.CS, 12);

        // Test to remove a student, resident, nonresident,tristate,international
        assertTrue(roster.remove(studentOne));
        assertTrue(roster.remove(studentTwo));
        assertTrue(roster.remove(studentThree));
        assertTrue(roster.remove(studentFour));
        assertTrue(roster.remove(studentFive));

        // Test to remove an already removed student
        assertFalse(roster.remove(studentOne));

        // Test to remove a student not within the same roster
        Roster rosterTwo = new Roster();
        Student studentSeven = new Student("Person seven", Major.EE, 12);
        rosterTwo.add(studentSeven);
        assertFalse(roster.remove(studentSeven));

        //Test to remove a student that was never added to the roster
        assertFalse(roster.remove(studentEight));
        //Test to remove a student that was already removed.
        assertFalse(roster.remove(studentOne));

    }
}