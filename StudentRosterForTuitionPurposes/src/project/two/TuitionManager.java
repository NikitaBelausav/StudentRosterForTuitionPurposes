/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package project.two;

import java.util.Scanner;

/**
* TuitionManager class takes inputs from console and returns the proper output, whether its tuition related or roster related.

* @author Mikita Belausau, Harpreet Randhawa
*/
public class TuitionManager {
    private static final int MINIMUM_CREDITS = 3;
    private static final int MAXIMUM_CREDITS = 24;
    private static final int MAXIMUM_FINANCIAL_AID = 10000;
    private static final int PLACEHOLDER_FOR_CREDIT_HOURS = 5;
    private static final int MINIMUM_CREDIT_FOR_FULL_TIME = 12;
    private static final int PROPER_LENGTH_FOR_AT = 5;

    /**
    * Check to see if major input is correct.
    
    * @param parse The given input string.
    * @return True if major input is a valid major, false if not a valid major.
    * @author Mikita Belausau
    */
    private boolean majorCheck(String[] parse) {
        if (parse[2].equalsIgnoreCase("CS") || parse[2].equalsIgnoreCase("IT")
                || parse[2].equalsIgnoreCase("BA") || parse[2].equalsIgnoreCase("EE") ||
                parse[2].equalsIgnoreCase("ME")) {
            return true;
        }
        System.out.println("'" + parse[2] + "' is not a valid major.");
        return false;
    }

    /**
    * Turns a user input into a major object.
    
    * @param parse The given input string.
    * @return Major object using user input.
    * @author Mikita Belausau
    */
    private Major inputToMajor(String[] parse) {
        Major major = null;
        if (parse[2].equalsIgnoreCase("CS") || parse[2].equalsIgnoreCase("IT")
                || parse[2].equalsIgnoreCase("BA") || parse[2].equalsIgnoreCase("EE") ||
                parse[2].equalsIgnoreCase("ME")) {
            major = Major.valueOf(parse[2].toUpperCase());
        }
        return major;
    }

    /**
    * Perform all financial aid commands entered by user.
    
    * @param roster1 The student roster.
    * @param parse The given input string.
    * @author Mikita Belausau
    */
    private void performCommandFourInputF(Roster roster1, String[] parse) {
        Student student = new Student(parse[1], inputToMajor(parse), PLACEHOLDER_FOR_CREDIT_HOURS);
        if (roster1.studentInRoster(student) == null) {
            System.out.println("Student not in the roster.");
        } else if (roster1.replaceFinancialAid(student) != null) {
            Resident resident = (Resident) roster1.replaceFinancialAid(student);
            if (resident.financialAid()) {
                System.out.println("Awarded once already.");
            } else if (resident.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) {
                System.out.println("Parttime student doesn't qualify for the award.");
            } else {
                resident.setResidentFinancialAid(Double.valueOf(parse[3]));
                resident.setFinancialAidRecieved(true);
                resident.setRecievedFinancialAidAmount(Double.valueOf(parse[3]));
                System.out.println("Tuition updated.");
            }
        } else {
            System.out.println("Not a resident student.");
        }
    }

    /**
    * Updates the abroad status, total credit hours, tuition due, and payment dates for International students.
    
    * @param roster1 The roster of students.
    * @param parse The given input string.
    * @author Harpreet Randhawa
    */
    private void performCommandFourInputS(Roster roster1, String[] parse) {
        Student student = new Student(parse[1], inputToMajor(parse), PLACEHOLDER_FOR_CREDIT_HOURS);
        if (roster1.studentInRoster(student) == null) {
            System.out.println("Couldn't find the international student.");
        } 
        else if (roster1.replaceStudyAbroad(student) != null) {
            International international = (International) roster1.replaceStudyAbroad(student);
                international.setStudyAbroadStatus(true);
                international.setTotalCreditHours(12);
                international.setTuitionDue(0);
                international.tuitionDue();
                international.setLastPaymentDate(null);
                System.out.println("Tuition updated.");            
        }
    }

    /**
    * Checks to see if the given input for adding a resident is correct and outputs the desired prints.
    
    * @param roster1 The roster of students.
    * @param parse The given input string.
    * @author Mikita Belausau
    */
    private void performCommandFourInputsAR(Roster roster1, String[] parse) {
        Resident resident = new Resident(parse[1], inputToMajor(parse), Integer.valueOf(parse[3]));
        if (roster1.add(resident)) {
            System.out.println("Student added.");
        } else {
            System.out.println("Student is already in the roster.");
        }
    }

    /**
    * Checks to see if the given input for adding a transfer student is correct and outputs the desired prints.
    
    * @param roster1 The roster of students.
    * @param parse The given input string.
    * @author Harpreet Randhawa
    */
    private void performCommandFourInputAT(Roster roster1, String[] parse) {

        if (parse.length != PROPER_LENGTH_FOR_AT) {
            System.out.println("Missing data in command line.");
            return;
        }
        if (!((parse[4].equalsIgnoreCase("NY")) || parse[4].equalsIgnoreCase("CT"))) {
            System.out.println("Not part of the tri-state area.");
            return;
        }
        TriState triState = new TriState(parse[1], inputToMajor(parse), Integer.valueOf(parse[3]), parse[4].toUpperCase());
        if ((roster1.add(triState))) {
            System.out.println("Student added.");
        } else {
            System.out.println("Student is already in the roster.");
        }
    }

    /**
    * Checks to see if the given input for adding a nonResident is correct and outputs the desired prints.
    
    * @param roster1 The roster of students.
    * @param parse The given input string.
    * @author Mikita Belausau
    */
    private void performCommandFourInputAN(Roster roster1, String[] parse) {
        NonResident nonResident = new NonResident(parse[1], inputToMajor(parse), Integer.valueOf(parse[3]));
        if (roster1.add(nonResident)) {
            System.out.println("Student added.");
        } else {
            System.out.println("Student is already in the roster.");
        }
    }

    /**
    * Checks to see if the given input for adding an international is correct and outputs the desired prints.
    
    * @param roster1 The roster of students.
    * @param parse The given input string.
    * @author Harpreet Randhawa, Mikita Belausau
    */
    private void performCommandFourInputAI(Roster roster1, String[] parse) {
        International international = new International(parse[1], inputToMajor(parse), Integer.valueOf(parse[3]), Boolean.valueOf(parse[4]));
        if ((Integer.valueOf(parse[3]) >= MINIMUM_CREDIT_FOR_FULL_TIME)) {
            if (roster1.add(international)) {
                System.out.println("Student added.");
            }
        } else {
            System.out.println("International students must enroll at least 12 credits.");
        }
    }

    /**
    * Checks to make sure the credit hours entered when adding a student is correctly inputed.
    
    * @param parse The parsed string.
    * @return returns true if credit hours was inputed correctly, false if not.
    * @author Mikita Belausau
    */
    private boolean creditHourCheck(String[] parse) {
        if (!(parse[3].matches("-?\\d+"))) {
            System.out.println("Invalid credit hours.");
            return false;
        } else if (((Integer.valueOf(parse[3]) < 0))) {
            System.out.println("Credit hours cannot be negative.");
            return false;
        } else if (((Integer.valueOf(parse[3]) < MINIMUM_CREDITS))) {
            System.out.println("Minimum credit hours is 3.");
            return false;
        } else if (((Integer.valueOf(parse[3]) > MAXIMUM_CREDITS))) {
            System.out.println("Credit hours exceed the maximum 24.");
            return false;
        }
        return true;
    }

    /**
    * Performs all commands that have to do with deleting a student from a roster.
    
    * @param roster1 The student roster.
    * @param parse The given input string.
    * @return true if the student was removed, and false if the student was not removed.
    * @author Mikita Belausau
    */
    private boolean performCommandThreeInputDelete(Roster roster1, String[] parse) {
        Student student = new Student(parse[1], inputToMajor(parse), PLACEHOLDER_FOR_CREDIT_HOURS);
        if (roster1.remove(student)) {
            System.out.println("Student removed from the roster.");
            return true;
        } else {
            System.out.println("Student is not in the roster.");
            return false;
        }
    }

    /**
    * Checks to see if the input by user is correctly formatted for adding a student to the roster.
    
    * @param parse The given input string.
    * @return true if the user input is formatted correctly for adding a student to roster, false if not.
    * @author Mikita Belausau
    */
    private boolean validCheckRosterAdd(String[] parse) {
        if (parse.length == 1) {
            System.out.println("Missing data in command line.");
            return false;
        } else if (parse.length == 2) {
            System.out.println("Missing data in command line.");
            return false;
        } else if (parse.length == 3) {
            System.out.println("Credit hours missing.");
            return false;
        } else if (!(majorCheck(parse))) {
            return false;
        } else if (!(creditHourCheck(parse))) {
            return false;
        }
        return true;
    }

    /**
    * All user inputs that are only one line are checked to make sure they're valid inputs.
    
    * @param parse The given input string.
    * @return true if the user input is supported by the program, false if not.
    * @author Mikita Belausau
    */
    private boolean validityCheckOne(String[] parse) {
        if (!(parse[0].equals("C") || parse[0].equals("P") || parse[0].equals("PN") || parse[0].equals("PT")
                || parse[0].equals("Q"))) {
            System.out.println("Command '" + parse[0] + "' not supported!");
            return false;
        }
        return true;
    }

    /**
    * Calculates the tuition due for all students in the roster if they arent already.
    
    * @param roster1 The student roster.
    * @author Mikkita Belausau
    */
    private void cInput(Roster roster1) {
        roster1.calculateTuition();
        System.out.println("Calculation completed.");
    }

    /**
    * Checks to see if the user input for making a payment is correctly formatted.
    
    * @param roster1 The student roster.
    * @param parse The given input string.
    * @return true if the payment info is valid, false if not.
    * @author Mikita Belausau
    */
    private boolean paymentValidityCheck(Roster roster1, String[] parse) {
        if (parse.length == 3) {
            System.out.println("Payment amount missing.");
            return false;
        } else if ((Double.valueOf(parse[3]) == 0) || (Double.valueOf(parse[3]) < 0)) {
            System.out.println("Invalid amount.");
            return false;
        }
        Date paymentDate = new Date(parse[4]);
        Student student = new Student(parse[1], inputToMajor(parse), PLACEHOLDER_FOR_CREDIT_HOURS);
        double tuitionDues = roster1.studentInRoster(student).getTuitionDue();
        if (tuitionDues < Double.valueOf(parse[3])) {
            System.out.println("Amount is greater than amount due.");
            return false;
        } else if (!(paymentDate.isValid(paymentDate))) {
            System.out.println("Payment date invalid.");
            return false;
        }
        return true;
    }

    /**
    * Performs all commands that pay for a students tuition.
    
    * @param roster1 The student roster.
    * @param parse The given input string.
    * @author Mikita Belausau
    */
    private void paymentInput(Roster roster1, String[] parse) {
        Date paymentDate = new Date(parse[4]);
        Student student = new Student(parse[1], inputToMajor(parse), PLACEHOLDER_FOR_CREDIT_HOURS);
        Double tuitionDues = roster1.studentInRoster(student).getTuitionDue();
        tuitionDues -= Double.valueOf(parse[3]);
        roster1.studentInRoster(student).setTuitionDue(tuitionDues);
        roster1.studentInRoster(student).setLastPaymentDate(paymentDate);
        roster1.studentInRoster(student).setLastPayment(Double.valueOf(parse[3]));
        System.out.println("Payment applied.");
    }

    /**
    * Checks to see if a users input is formatted correctly for students receiving financial aid.
    
    * @param parse The given input string.
    * @return true if the user input is correctly formatted, false if not.
    * @author Mikita Belausau
    */
    private boolean validityCheckF(String[] parse) {
        if (parse.length == 3) {
            System.out.println("Missing the amount.");
            return false;
        } else if (((parse[0].equals("F")) &&
                (((((Double.valueOf(parse[3])) > MAXIMUM_FINANCIAL_AID)) || (Double.valueOf(parse[3]) < 0))))) {
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    /**
    * Perform all commands by calling the necessary methods in the program based on the users input.
    
    * @param line The given line.
    * @param roster1 The student roster.
    * @author Mikita Belausau
    */
    private void performCommands(String line, Roster roster1) {
        String[] parse = line.split(",");
        if ((parse[0].equals("AR"))) {
            performCommandFourInputsAR(roster1, parse);
        } else if ((parse[0].equals("AN"))) {
            performCommandFourInputAN(roster1, parse);
        } else if ((parse[0].equals("AI"))) {
            performCommandFourInputAI(roster1, parse);
        } else if ((parse[0].equals("AT"))) {
            performCommandFourInputAT(roster1, parse);
        } else if ((parse[0].equals("F"))) {
            performCommandFourInputF(roster1, parse);
        } else if ((parse[0].equals("P"))) {
            roster1.print();
        } else if ((parse[0].equals("R"))) {
            performCommandThreeInputDelete(roster1, parse);
        } else if (parse[0].equals("C")) {
            cInput(roster1);
        } else if (parse[0].equals("T")) {
            paymentInput(roster1, parse);
        } else if (parse[0].equals("S")) {
            performCommandFourInputS(roster1, parse);
        } else if (parse[0].equals("PN")) {
            roster1.printByName();
        } else if (parse[0].equals("PT")) {
            roster1.printByDate();
        } else if (parse[0].equals("Q")) {
            System.out.println("\nTuition Manager terminated.");
            System.exit(0);
        }

    }

    /**
    * Checks users input to make sure it is valid.
    
    * @param roster1 The student roster.
    * @param line The given line.
    * @return True if the user input is valid formatting, false if not.
    * @author Mikita Belausau
    */
    private boolean isValidInput(Roster roster1, String line) {
        String[] parse = line.split(",");
        if ((parse[0].equals("F"))) {
            return validityCheckF(parse);
        } else if (parse[0].equals("AR") || parse[0].equals("AN") || parse[0].equals("AI") || parse[0].equals("AT")) {
            return validCheckRosterAdd(parse);
        } else if (parse.length == 1) {
            return validityCheckOne(parse);
        } else if (parse[0].equals("T")) {
            return paymentValidityCheck(roster1, parse);
        }
        return true;
    }

    /**
    * Runs Tuition Manager.
   
    * @author Mikita Belausau
    */
    public void run() {
        System.out.println("Tuition Manager starts running.");
        Roster rosters1 = new Roster();
        Scanner scanner = new Scanner(System.in);
        while (!(scanner.equals("Q"))) {
            String line = scanner.nextLine();
            if ((line.equals("\n")) || (line.equals(""))) {
                System.out.println("");
            } else if (isValidInput(rosters1, line)) {
                performCommands(line, rosters1);
            }
        }
    }

}
