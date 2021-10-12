package unitTesting;

import project.two.Date;
import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void isValid() {
        Date date = new Date("21/1/2021"); //Month invalid
        assertFalse(date.isValid(date));
        date = new Date("2/29/2021"); //Not a leap year so feb max dates is 28
        assertFalse(date.isValid(date));
        date = new Date("4/31/2021"); //April only has 30 days
        assertFalse(date.isValid(date));
        date = new Date("12/1/2021"); //not before todays date
        assertFalse(date.isValid(date));
        date = new Date("11/1/2020"); //any day before 2021 is bad
        assertFalse(date.isValid(date));
        date = new Date("7/32/2021"); //there arent 32 days in july
        assertFalse(date.isValid(date));
        date = new Date("-1/10/2021"); //negative month test
        assertFalse(date.isValid(date));
        date = new Date("4/-23/2021"); //negative day test
        assertFalse(date.isValid(date));
        date = new Date("4/23/-2021"); //negative yr test
        assertFalse(date.isValid(date));
        date = new Date("9/22/2021"); // A day that you can pay tuition on since its mm/dd/yr is valid + its before tday 10/7/2021
        assertTrue(date.isValid(date));
        date = new Date("0/23/2021"); //0 isnt a valid month
        assertFalse(date.isValid(date));
        date = new Date("9/0/2021"); //0 isnt a valid date
        assertFalse(date.isValid(date));
        date = new Date("9/23/0"); //0 isnt a valid yr
        assertFalse(date.isValid(date));
    }
}