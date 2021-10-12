/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package project.two;

import java.util.Calendar;

/**
Date class creates date objects, and also checks if they are valid dates as well. It also accepts
date objects to see if they are valid.

@author Mikita Belausau, Harpreet Randhawa
*/
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int THE_TWENTY_TWENTY_ONE = 2021;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int LEAP_YEAR_MAX_FEB_DATES = 29;
    private static final int MINIMUM_DAYS_IN_A_MONTH = 1;
    private static final int NON_LEAP_YEAR_MAX_FEB_DATES = 28;
    private static final int THIRTY_DAY_MONTHS = 30;
    private static final int THIRTY_ONE_DAY_MONTHS = 31;
    private static final int MAX_MONTHS = 12;
    private static final int LEFT_SIDE_GREATER = 1;
    private static final int RIGHT_SIDE_GREATER = -1;


    /**
     Getter for year

     @return year
     @author Harpreet Randhawa
     */
    public int getYear() {
        return this.year;
    }

    /**
     Getter for month

     @return month
     @author Harpreet Randhawa
     */
    public int getMonth() {
        return this.month;
    }

    /**
     Getter for day

     @return day
     @author Harpreet Randhawa
     */
    public int getDay() {
        return this.day;
    }


    /**
     Properly split a date of mm/dd/yy into a date object.

     @param date the album's date
     @author Mikita Belausau
     */
    public Date(String date) {
        String[] correctDate = date.split("/");
        this.year = Integer.parseInt(correctDate[2]);
        this.month = Integer.parseInt(correctDate[0]);
        this.day = Integer.parseInt(correctDate[1]);
    }

    /**
     Create an object with today's date.

     @author Mikita Belausau
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        Date today = Date.this;
        today.year = calendar.get(Calendar.YEAR);
        today.month = calendar.get(Calendar.MONTH) + 1;
        today.day = calendar.get(Calendar.DATE);
    }

    /**
     Checks if a leap-year-date is valid.

     @param date the album's date
     @return true if the year is a leap year and the overall date is valid, false otherwise.
     @author Mikita Belausau
     */
    public boolean checkIfLeapYearDateIsValid(Date date) {
        if ((date.month == FEBRUARY) && (date.day > LEAP_YEAR_MAX_FEB_DATES
                || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        } else if ((date.month == APRIL || date.month == JUNE || date.month == SEPTEMBER
                || date.month == NOVEMBER) &&
                (date.day > THIRTY_DAY_MONTHS || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        } else if ((date.month == JANUARY || date.month == MARCH || date.month == MAY
                || date.month == JULY || date.month == AUGUST
                || date.month == OCTOBER || date.month == DECEMBER)
                && (date.day > THIRTY_ONE_DAY_MONTHS || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        }
        return true;
    }

    /**
     Check if a non-leap-year-date is a valid date.

     @param date the album's date
     @return true if a non-leap year date is valid.
     @author Mikita Belausau
     */
    public boolean checkIfNonLeapYearDayIsValid(Date date) {
        if ((date.month == FEBRUARY) && (date.day > NON_LEAP_YEAR_MAX_FEB_DATES
                || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        } else if ((date.month == APRIL || date.month == JUNE || date.month == SEPTEMBER
                || date.month == NOVEMBER) && (date.day > THIRTY_DAY_MONTHS
                || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        } else if ((date.month == JANUARY || date.month == MARCH || date.month == MAY
                || date.month == JULY || date.month == AUGUST
                || date.month == OCTOBER || date.month == DECEMBER)
                && (date.day > THIRTY_ONE_DAY_MONTHS || date.day < MINIMUM_DAYS_IN_A_MONTH)) {
            return false;
        }
        return true;
    }

    /**
     Checks if the date object entered is a valid date, even if its a leap year.

     @param date the album's date
     @return true if the given date is a valid date and returns false if the given date is not valid.
     @author Mikita Belausau
     */
    public boolean isValid(Date date) {
        boolean leapYear = false;
        Date today = new Date();
        if (date.month > MAX_MONTHS || date.month <= 0 || date.year <= 0 || date.day <= 0) {
            return false;
        }
        if (date.year > today.year) {
            return false;
        } else if ((date.year == today.year) && (date.month > today.month)) {
            return false;
        } else if ((date.year == today.year) && (date.month == today.month) && (date.day > today.day)) {
            return false;
        }
        if (date.year < THE_TWENTY_TWENTY_ONE) {
            return false;
        }
        if (date.year % QUADRENNIAL == 0 && date.year % CENTENNIAL != 0) {
            leapYear = true;
        } else if ((date.year % QUADRENNIAL == 0) && (date.year % CENTENNIAL != 0)
                && (date.year % QUATERCENTENNIAL == 0)) {
            leapYear = true;
        }
        if (leapYear) {
            return checkIfLeapYearDateIsValid(date);
        } else {
            return checkIfNonLeapYearDayIsValid(date);
        }
    }


    /**
     Compares the two given date objects.

     @param date the album's date
     @return 1 if the left side is greater, and -1 if the right side is greater.
     @author Mikita Belausau
     */
    @Override
    public int compareTo(Date date) {
        if (this.year != date.year) {
            return this.year > date.year ? LEFT_SIDE_GREATER : this.year < date.year ? RIGHT_SIDE_GREATER : 0;
        } else {
            if (this.month != date.month) {
                return this.month > date.month ? LEFT_SIDE_GREATER : this.month < date.month ? RIGHT_SIDE_GREATER : 0;
            } else {
                return this.day > date.day ? LEFT_SIDE_GREATER : this.day < date.day ? RIGHT_SIDE_GREATER : 0;
            }
        }
    }

    /**
     * Returns date object as a string with --/--/-- formatting
     * @return date object with --/--/-- string formatting
     * @author Mikita Belausau
     */
    @Override
    public String toString() {
        return this.getMonth() + "/" + this.getDay() + "/" + this.getYear();
    }

    /**
     Testbed main for the date class.

     @param args standard syntax
     @author Mikita Belausau
     */
    public static void main(String[] args) {
        //test case #1 day before 2021 should be bad
        Date date = new Date("3/31/1800");
        boolean expectedResult = false;
        boolean result = date.isValid(date);
        System.out.println("Test Case #1: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #2 invalid month
        date = new Date("31/2/2021");
        result = date.isValid(date);
        System.out.println("Test Case #2: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #3 correct day
        expectedResult = true;
        date = new Date("7/23/2021");
        result = date.isValid(date);
        System.out.println("Test Case #3: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #4 compareTo test to see if 11/16/2015 > 11/15/2015
        expectedResult = true;
        date = new Date("11/20/2015");
        Date secondDate = new Date("11/15/2015");
        int result2 = date.compareTo(secondDate);
        System.out.println("Test case #4: ");
        if (result2 == 1) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #5 checks to make sure the day isnt after current date
        expectedResult = false;
        date = new Date("10/30/2022");
        result = date.isValid(date);
        System.out.println("Test case #5: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #6 testing leap year
        expectedResult = true;
        date = new Date("02/29/2020");
        result = date.isValid(date);
        System.out.println("Test case #6: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
        //test case #7 testing leap day with non-leap year
        expectedResult = false;
        date = new Date("02/29/2009");
        result = date.isValid(date);
        System.out.println("Test case #7: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        } else {
            System.out.println("Fail.");
        }
    }
}
