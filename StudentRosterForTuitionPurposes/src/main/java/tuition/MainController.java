/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;

/**
 * The main controller for the GUI
 *
 * @author Mikita Belausau, Harpreet Randhawa
 */
public class MainController {
    private Roster roster = new Roster();
    private Major major = null;
    private String studentName = null;
    private double previousTuitionDue = 0.0;
    private double previousFinancialAid = 0.0;
    private double currentFinancialAid = 0.0;
    private final int MAX_CREDIT_HOURS = 24;
    private final int MIN_CREDIT_HOURS = 3;
    private final int MINIMUM_CREDIT_FOR_FULL_TIME = 12;

    @FXML
    private ToggleGroup MajorGroup;

    @FXML
    private ToggleGroup MajorGroup1;

    @FXML
    private ToggleGroup MajorGroup2;

    @FXML
    private ToggleGroup Residency;

    @FXML
    private RadioButton baMajor;

    @FXML
    private RadioButton baMajorThree;

    @FXML
    private RadioButton baMajorTwo;

    @FXML
    private DatePicker givenDate;

    @FXML
    private TextField creditHoursTextField;

    @FXML
    private RadioButton csMajor;

    @FXML
    private RadioButton csMajorThree;

    @FXML
    private RadioButton csMajorTwo;

    @FXML
    private RadioButton ctResidency;

    @FXML
    private RadioButton eeMajor;

    @FXML
    private RadioButton eeMajorThree;

    @FXML
    private RadioButton eeMajorTwo;

    @FXML
    private RadioButton internationalResidency;

    @FXML
    private RadioButton itMajor;

    @FXML
    private RadioButton itMajorThree;

    @FXML
    private RadioButton itMajorTwo;

    @FXML
    private RadioButton meMajor;

    @FXML
    private RadioButton meMajorThree;

    @FXML
    private RadioButton meMajorTwo;

    @FXML
    private RadioButton nyResidency;

    @FXML
    private TextArea outPutField;

    @FXML
    private TextField paymentAmountTextField;

    @FXML
    private Button performCalculateRoster;

    @FXML
    private Button performCalculateStudent;

    @FXML
    private Button performFinancialAid;

    @FXML
    private Button performUpdateFinancialAid;

    @FXML
    private Button performPayment;

    @FXML
    private RadioButton residentResidency;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private TextField studentNameTextFieldThree;

    @FXML
    private TextField studentNameTextFieldTwo;

    @FXML
    private RadioButton studyAbroadResidency;

    @FXML
    private RadioButton triStateResidency;

    /**
     * When triState in the view is selected as a form of residency it hides all other info that is not used by triState Students
     * as well as show info only used by triState students.
     *
     * @param event the event of clicking on triState residency.
     * @author Mikita Belausau
     */
    @FXML
    void triStateSelectedHide(ActionEvent event) {
        nyResidency.setDisable(false);
        ctResidency.setDisable(false);
        studyAbroadResidency.setDisable(true);
        studyAbroadResidency.setSelected(false);
    }

    /**
     * When resident is selected as a form of residency hide all info not used by resident students.
     *
     * @param event the event of selected resident as the form of residency
     * @author Mikita Belausau
     */
    @FXML
    void residentSelectedHide(ActionEvent event) {
        studyAbroadResidency.setDisable(true);
        studyAbroadResidency.setSelected(false);
        nyResidency.setDisable(true);
        nyResidency.setSelected(false);
        ctResidency.setDisable(true);
        ctResidency.setSelected(false);
    }

    /**
     * When full time yes resident option is selected, reveals the buttons needed.
     *
     * @param event the event of selected resident as the form of residency
     * @author Harpreet Randhawa
     */
    @FXML
    void fullTimeYesResidentSelectedHide(ActionEvent event) {
        performFinancialAid.setDisable(false);
        performUpdateFinancialAid.setDisable(false);
        performPayment.setDisable(false);
    }

    /**
     * When full time yes resident option is selected, hides the buttons not needed.
     *
     * @param event the event of selected resident as the form of residency
     * @author Harpreet Randhawa
     */
    @FXML
    void fullTimeNoResidentSelectedHide(ActionEvent event) {
        performFinancialAid.setDisable(true);
        performUpdateFinancialAid.setDisable(true);
        performPayment.setDisable(false);
    }

    /**
     * When international residency is selected hide all info not used by international students and display options used only by them.
     *
     * @param event the event of selecting international residency
     * @author Mikita Belausau
     */
    @FXML
    void internationalSelectedHide(ActionEvent event) {
        studyAbroadResidency.setDisable(false);
        nyResidency.setDisable(true);
        nyResidency.setSelected(false);
        ctResidency.setDisable(true);
        ctResidency.setSelected(false);
    }

    /**
     * When one student is selected it hides all unusuable information and activates all usuable information
     *
     * @param event pressing the radio button one student
     * @author Mikita Belausau
     */
    @FXML
    void oneStudentSelectedHide(ActionEvent event) {
        performCalculateRoster.setDisable(true);
        performCalculateStudent.setDisable(false);
        csMajorThree.setDisable(false);
        baMajorThree.setDisable(false);
        itMajorThree.setDisable(false);
        eeMajorThree.setDisable(false);
        meMajorThree.setDisable(false);
        studentNameTextFieldThree.setDisable(false);
    }

    /**
     * When entire roster is selected this hides all unsuable information and activates all usuable information
     *
     * @param event pressing the radio button entire roster
     * @author Mikita Belausau
     */
    @FXML
    void entireRosterSelectedHide(ActionEvent event) {
        studentNameTextFieldThree.clear();
        studentNameTextFieldThree.setDisable(true);
        csMajorThree.setSelected(false);
        csMajorThree.setDisable(true);
        baMajorThree.setSelected(false);
        baMajorThree.setDisable(true);
        itMajorThree.setSelected(false);
        itMajorThree.setDisable(true);
        eeMajorThree.setSelected(false);
        eeMajorThree.setDisable(true);
        meMajorThree.setSelected(false);
        meMajorThree.setDisable(true);
        performCalculateStudent.setDisable(true);
        performCalculateRoster.setDisable(false);
    }

    /**
     * Checks if the GUI is being used correctly to perform a delete or set operation.
     *
     * @param event the action of clicking a perform button.
     * @return true if the name and major are entered correctly, false otherwise.
     * @author Mikita Belausau
     */
    @FXML
    public boolean checkIfValidStudentInputForDelete(ActionEvent event) {
        if (studentName.isEmpty()) {
            outPutField.appendText("Enter a name for the student.\n");
            return false;
        } else if (!(studentName.isEmpty())) {
            char[] checkIfStudentNameOnlyHasLetters = studentName.toCharArray();
            for (char c : checkIfStudentNameOnlyHasLetters) {
                if (!Character.isLetter(c) && !(Character.isSpaceChar(c))) {
                    outPutField.appendText("Please use only characters for student name.\n");
                    return false;
                }
            }
        }
        if (major == null) {
            outPutField.appendText("Please select a major.\n");
            return false;
        }
        return true;
    }

    /**
     * Checks if the input into the GUI is correct for adding a student
     *
     * @param event the event of clicking on a perform operation.
     * @return true if the name, major, credit hours, form of residency are all inputed correctly, false otherwise.
     * @author Mikita Belausau
     */
    @FXML
    public boolean checkIfValidStudentInput(ActionEvent event) {
        try {
            if (studentName.isEmpty()) {
                outPutField.appendText("Enter a name for the student.\n");
                return false;
            } else if (!(studentName.isEmpty())) {
                char[] checkIfStudentNameOnlyHasLetters = studentName.toCharArray();
                for (char c : checkIfStudentNameOnlyHasLetters) {
                    if (!Character.isLetter(c) && !(Character.isSpaceChar(c))) {
                        outPutField.appendText("Please use only characters for student name.\n");
                        return false;
                    }
                }
            }
            if (major == null) {
                outPutField.appendText("Please select a major.\n");
                return false;
            }
            if (Residency.getSelectedToggle() == null) {
                outPutField.appendText("Please select a form of residency.\n");
                return false;
            }
            if (Integer.parseInt(creditHoursTextField.getText()) < MIN_CREDIT_HOURS) {
                outPutField.appendText("Please use only credit hours greater than 2.\n");
                return false;
            } else if (Integer.parseInt(creditHoursTextField.getText()) > MAX_CREDIT_HOURS) {
                outPutField.appendText("Please use only credit hours less than 25.\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            outPutField.appendText("You must use only numbers ranging from 2-24 for credit amount.\n");
            return false;
        }
    }

    /**
     * Sets the student name and trims it for any extra white space.
     *
     * @param event the event of the student name
     * @author Mikita Belausau
     */
    @FXML
    public void setStudentName(ActionEvent event) {
        studentName = studentNameTextField.getText().trim();
    }

    /**
     * sets the student name and trims it for any extra white space.
     *
     * @param event The event of the student name
     * @author Harpreet Randhawa
     */
    @FXML
    public void setStudentNameTwo(ActionEvent event) {
        studentName = studentNameTextFieldTwo.getText().trim();
    }

    /**
     * Sets the student name and trims it for any extra white space.
     *
     * @param event The event of the student name
     * @author Harpreet Randhawa
     */
    @FXML
    public void setStudentNameThree(ActionEvent event) {
        studentName = studentNameTextFieldThree.getText().trim();
    }

    /**
     * Sets the major selected from the given major radio button selected
     *
     * @param event The event of the major selection
     * @author Mikita Belausau
     */
    @FXML
    public void setMajorGroup(ActionEvent event){
        if(MajorGroup.getSelectedToggle() == csMajor){
            major = Major.CS;
        }
        else if(MajorGroup.getSelectedToggle() == baMajor){
            major = Major.BA;
        }
        else if(MajorGroup.getSelectedToggle() == itMajor){
            major = Major.IT;
        }
        else if(MajorGroup.getSelectedToggle() == meMajor){
            major = Major.ME;
        }
        else if(MajorGroup.getSelectedToggle() == eeMajor){
            major = Major.EE;
        }
        else{
            major = null;
        }
    }

    /**
     * Sets the major selected from the given major radio button selected
     *
     * @param event The event of the major selection
     * @author Harpreet Randhawa
     */
    @FXML
    public void setMajorGroupTwo(ActionEvent event){
        if(MajorGroup1.getSelectedToggle() == csMajorTwo){
            major = Major.CS;
        }
        else if(MajorGroup1.getSelectedToggle() == baMajorTwo){
            major = Major.BA;
        }
        else if(MajorGroup1.getSelectedToggle() == itMajorTwo){
            major = Major.IT;
        }
        else if(MajorGroup1.getSelectedToggle() == meMajorTwo){
            major = Major.ME;
        }
        else if(MajorGroup1.getSelectedToggle() == eeMajorTwo){
            major = Major.EE;
        }
        else{
            major = null;
        }
    }

    /**
     * Sets the major selected from the given major radio button selected
     *
     * @param event The event of the major selection
     * @author Harpreet Randhawa
     */
    @FXML
    public void setMajorGroupThree(ActionEvent event){
        if(MajorGroup2.getSelectedToggle() == csMajorThree){
            major = Major.CS;
        }
        else if(MajorGroup2.getSelectedToggle() == baMajorThree){
            major = Major.BA;
        }
        else if(MajorGroup2.getSelectedToggle() == itMajorThree){
            major = Major.IT;
        }
        else if(MajorGroup2.getSelectedToggle() == meMajorThree){
            major = Major.ME;
        }
        else if(MajorGroup2.getSelectedToggle() == eeMajorThree){
            major = Major.EE;
        }
        else{
            major = null;
        }
    }

    /**
     * Helper method for adding resident students.
     *
     * @param event the event of selecting a perform operation
     * @author Mikita Belausau
     */
    @FXML
    public void addResident(ActionEvent event) {
        Resident resident = new Resident(studentNameTextField.getText(), major,
                Integer.parseInt(creditHoursTextField.getText()));
        if (roster.add(resident)) {
            outPutField.appendText(studentNameTextField.getText() + " was added to roster.\n");
        } else {
            outPutField.appendText(studentNameTextField.getText() + " student is already in roster.\n");
        }
    }

    /**
     * Helper method for adding international students.
     *
     * @param event the event of selecting a perform operation
     * @author Mikita Belausau
     */
    @FXML
    public void addInternationalStudent(ActionEvent event) {
        if (!(studyAbroadResidency.isSelected())) {
            International international = new International(studentNameTextField.getText(), major,
                    Integer.parseInt(creditHoursTextField.getText()), false);
            if (Integer.parseInt(creditHoursTextField.getText()) < 12) {
                outPutField.appendText("International students credits cannot be less than 12\n");
            } else if (roster.add(international)) {
                outPutField.appendText(studentNameTextField.getText() + " was added to roster.\n");
            } else {
                outPutField.appendText(studentNameTextField.getText() + " student is already in roster.\n");
            }
        } else {
            International international = new International(studentNameTextField.getText(), major,
                    Integer.parseInt(creditHoursTextField.getText()), true);
            if (Integer.parseInt(creditHoursTextField.getText()) != 12) {
                outPutField.appendText("International students studying abroad cannot have more than or less than 12 credits\n");
            } else if (roster.add(international)) {
                outPutField.appendText(studentNameTextField.getText() + " was added to roster.\n");
            } else {
                outPutField.appendText(studentNameTextField.getText() + " student is already in roster.\n");
            }
        }
    }

    /**
     * Helper method for adding triState NY students.
     *
     * @param event the event of selecting a perform operation
     * @author Mikita Belausau
     */
    @FXML
    public void addTriStateStudentNY(ActionEvent event) {
        TriState tristate = new TriState(studentNameTextField.getText(), major,
                Integer.parseInt(creditHoursTextField.getText()), "NY");
        if (roster.add(tristate)) {
            outPutField.appendText(studentNameTextField.getText() + " was added to roster.\n");
        } else {
            outPutField.appendText(studentNameTextField.getText() + " student is already in roster.\n");
        }
    }

    /**
     * Helper method for adding triState CT students.
     *
     * @param event the event of selecting a perform operation
     * @author Mikita Belausau
     */
    @FXML
    public void addTriStateStudentCT(ActionEvent event) {
        TriState tristate = new TriState(studentNameTextField.getText(), major,
                Integer.parseInt(creditHoursTextField.getText()), "CT");
        if (roster.add(tristate)) {
            outPutField.appendText(studentNameTextField.getText() + " was added to roster.\n");
        } else {
            outPutField.appendText(studentNameTextField.getText() + " student is already in roster.\n");
        }
    }

    /**
     * Adds a student to the roster based off of their GUI usage.
     *
     * @param event pressing the add student button
     * @author Mikita Belausau
     */
    @FXML
    void addStudentToRoster(ActionEvent event) {
        setStudentName(event);
        setMajorGroup(event);
        if (checkIfValidStudentInput(event)) {
            if (residentResidency.isSelected()) {
                addResident(event);
            } else if (internationalResidency.isSelected()) {
                addInternationalStudent(event);
            } else if (triStateResidency.isSelected()) {
                if (nyResidency.isSelected()) {
                    addTriStateStudentNY(event);
                } else if (ctResidency.isSelected()) {
                    addTriStateStudentCT(event);
                } else {
                    outPutField.appendText("Please select either CT or NY if adding a tristate student.\n");
                }
            }
        }
    }


    /**
     * Deletes a student from the roster.
     *
     * @param event the event of clicking on the delete student button
     * @author Mikita Belausau
     */
    @FXML
    void deleteStudentFromRoster(ActionEvent event) {
        setStudentName(event);
        setMajorGroup(event);
        if (checkIfValidStudentInputForDelete(event)) {
            Student student = new Student(studentNameTextField.getText(), major);
            if (roster.remove(student)) {
                outPutField.appendText(studentNameTextField.getText() + " was removed.\n");
            } else {
                outPutField.appendText(studentNameTextField.getText() + " is not in the roster.\n");
            }
        }
    }

    /**
     * Displays the roster in no particular order
     *
     * @param event the event of pressing on the Display Roster button
     * @author Mikita Belausau
     */
    @FXML
    void displayRoster(ActionEvent event) {
        if (roster.getSize() == 0) {
            outPutField.appendText("Student roster is empty!\n");
        } else {
            outPutField.appendText("* list of students **\n");
            outPutField.appendText(roster.print() + "\n");
            outPutField.appendText("* end of roster **\n");
        }
    }

    /**
     * Displays the roster in name order
     *
     * @param event the event of pressing the Display Roster By Name button
     * @author Mikita Belausau
     */
    @FXML
    void displayRosterByName(ActionEvent event) {
        if (roster.getSize() == 0) {
            outPutField.appendText("Student roster is empty!\n");
        } else {
            outPutField.appendText("* list of students ordered by name **\n");
            outPutField.appendText(roster.printByName() + "\n");
            outPutField.appendText("* end of roster **\n");
        }
    }

    /**
     * Display the roster with only students who have made payments in order of payment date.
     *
     * @param event the event of pressing the Display Roster By Payment Date button
     * @author Mikita Belausau
     */
    @FXML
    void displayRosterByPaymentDate(ActionEvent event) {
        if (roster.getSize() == 0) {
            outPutField.appendText("Student roster is empty!\n");
        } else {
            outPutField.appendText("* list of students ordered by Date **\n");
            outPutField.appendText(roster.printByDate() + "\n");
            outPutField.appendText("* end of roster **\n");
        }
    }

    /**
     * Sets the international student that is not studying abroad to be studying abroad and performs all operations necessary.
     *
     * @param event the event of pressing the Set StudyAbroad button
     * @author Mikita Belausau
     */
    @FXML
    void setStudyAbroadStatus(ActionEvent event) {
        setStudentName(event);
        setMajorGroup(event);
        if (checkIfValidStudentInputForDelete(event)) {
            Student student = new Student(studentNameTextField.getText(), major);
            if (roster.studentInRoster(student) == null) {
                outPutField.appendText("International student not found.\n");

            } else if (roster.replaceStudyAbroad(student) != null) {
                International international = (International) roster.replaceStudyAbroad(student);
                if (international.getStudyAbroadStatus() != true) {
                    international.setStudyAbroadStatus(true);
                    international.setTotalCreditHours(12);
                    international.setTuitionDue(0);
                    international.tuitionDue();
                    international.setLastPaymentDate(null);
                    outPutField.appendText(studentNameTextField.getText() + " is all set to study abroad.\n");
                } else {
                    outPutField.appendText("Student is already studying abroad.\n");
                }
            } else {
                outPutField.appendText("Student is not an international student.\n");
            }

        }
    }

    /**
     * Calculates the financial aid for the given resident student.
     *
     * @param event The event of the financial aid button being clicked
     * @author Harpreet Randhawa
     */
    @FXML
    void financialAid(ActionEvent event) {
        try {
            setStudentNameTwo(event);
            setMajorGroupTwo(event);
            if (checkIfValidStudentInputForDelete(event)) {
                Student student = new Student(studentNameTextFieldTwo.getText(), major);
                if (roster.studentInRoster(student) == null) {
                    outPutField.appendText("Student not found.\n");
                }
                else if (roster.studentInRoster(student).wasCalculated() == false){
                    outPutField.appendText("Student's tuition must be calculated first.\n");
                    return;
                }
                else if (paymentAmountTextField.getText() == null) {
                    outPutField.appendText("Payment amount not found.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) < 0) {
                    outPutField.appendText("Please enter a non-negative payment amount.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) > 10000) {
                    outPutField.appendText("Financial aid cannot exceed 10,000 dollars.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) == 0) {
                    outPutField.appendText("Please enter an amount greater than zero.\n");
                }
                else if((roster.studentInRoster(student).getTuitionDue() - Double.parseDouble(paymentAmountTextField.getText()) < 0) && (roster.studentInRoster(student).wasCalculated() == true)){
                    outPutField.appendText("Cannot have a financial aid amount greater than the tuition due.\n");
                }
                else if ((roster.replaceFinancialAid(student)) != null && (Double.parseDouble(paymentAmountTextField.getText()) >= 0) && (roster.studentInRoster(student).wasCalculated() == true) && ((Double.parseDouble(paymentAmountTextField.getText()) <= 10000))) {
                    Resident resident = (Resident) roster.replaceFinancialAid(student);
                    if (resident.financialAid()) {
                        outPutField.appendText("Awarded once already.\n");
                    } else if (resident.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) {
                        outPutField.appendText("Parttime student doesn't qualify for the award.\n");
                    } else {
                        resident.setResidentFinancialAid(Double.parseDouble(paymentAmountTextField.getText()));
                        resident.setFinancialAidRecieved(true);
                        resident.setRecievedFinancialAidAmount(Double.parseDouble(paymentAmountTextField.getText()));
                        previousTuitionDue = resident.getTuitionDue();
                        outPutField.appendText("Tuition updated.\n");
                    }
                } else {
                    outPutField.appendText("Not a resident student.\n");
                }
            }
        }
        catch (NumberFormatException e) {
            outPutField.appendText("Please only use numbers in the payment amount text field\n");
        }
    }

    /**
     * Updates the financial aid for the given resident student.
     *
     * @param event The event of the update financial aid button being clicked
     * @author Harpreet Randhawa
     */
    @FXML
    void updateFinancialAid(ActionEvent event){
        try {
            setStudentNameTwo(event);
            setMajorGroupTwo(event);
            if (checkIfValidStudentInputForDelete(event)) {
                Student student = new Student(studentNameTextFieldTwo.getText(), major);
                if (roster.studentInRoster(student) == null) {
                    outPutField.appendText("Student not found.\n");
                }
                else if (roster.studentInRoster(student).wasCalculated() == false){
                    outPutField.appendText("Student's tuition must be calculated first.\n");
                    return;
                }
                else if (paymentAmountTextField.getText() == null) {
                    outPutField.appendText("Payment amount not found.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) < 0) {
                    outPutField.appendText("Please enter a non-negative payment amount.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) > 10000) {
                    outPutField.appendText("Financial aid cannot exceed 10,000 dollars.\n");
                }
                else if ((roster.replaceFinancialAid(student)) != null && (Double.parseDouble(paymentAmountTextField.getText()) >= 0) && (roster.studentInRoster(student).wasCalculated() == true) && (Double.parseDouble(paymentAmountTextField.getText()) <= 10000)) {
                    Resident resident = (Resident) roster.replaceFinancialAid(student);
                    if (resident.financialAid() == false) {
                        outPutField.appendText("Must have financial aid done first in order to update financial aid.\n");
                    } else if (resident.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) {
                        outPutField.appendText("Parttime student doesn't qualify for the award.\n");
                    } else {
                        resident.setResidentFinancialAid(Double.parseDouble(paymentAmountTextField.getText()));
                        resident.setFinancialAidRecieved(true);
                        previousFinancialAid = resident.getResidentFinancialAidAmount();
                        currentFinancialAid = Double.parseDouble(paymentAmountTextField.getText());
                        resident.setTuitionDue(previousTuitionDue);
                        if (previousFinancialAid > currentFinancialAid){
                            double difference = previousFinancialAid - currentFinancialAid;
                            resident.setTuitionDue(resident.getTuitionDue() + difference);
                            resident.setResidentFinancialAidAmount(currentFinancialAid);
                            previousTuitionDue = resident.getTuitionDue();
                            outPutField.appendText("Tuition updated.\n");
                        }
                        else if(previousFinancialAid < currentFinancialAid){
                            double difference = currentFinancialAid - previousFinancialAid;
                            resident.setTuitionDue(resident.getTuitionDue() - difference);
                            resident.setResidentFinancialAidAmount(currentFinancialAid);
                            previousTuitionDue = resident.getTuitionDue();
                            outPutField.appendText("Tuition updated.\n");
                        }
                        else if(previousFinancialAid == currentFinancialAid){
                            outPutField.appendText("Financial aid amount is the same as previous. Nothing to update.\n");
                        }
                    }
                }
                else {
                    outPutField.appendText("Not a resident student.\n");
                }
            }
        }
        catch (NumberFormatException e) {
            outPutField.appendText("Please only use numbers in the payment amount text field\n");
        }
    }

    /**
     * Calculates the given payment for the student.
     *
     * @param event The event of the payment button being clicked
     * @author Harpreet Randhawa
     */
    @FXML
    void calculatePayment(ActionEvent event) {
        try {
            setStudentNameTwo(event);
            setMajorGroupTwo(event);
            if (checkIfValidStudentInputForDelete(event)) {
                Student student = new Student(studentNameTextFieldTwo.getText(), major);
                if (roster.studentInRoster(student) == null) {
                    outPutField.appendText("Student not found.\n");
                }
                else if (roster.studentInRoster(student).wasCalculated() == false){
                    outPutField.appendText("Student's tuition must be calculated first.\n");
                    return;
                }
                else if (paymentAmountTextField.getText() == null) {
                    outPutField.appendText("Payment amount not found.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) == 0) {
                    outPutField.appendText("Please enter payment amount greater than zero.\n");
                }
                else if (Double.parseDouble(paymentAmountTextField.getText()) < 0) {
                    outPutField.appendText("Please enter a non-negative payment amount.\n");
                }
                else if((roster.studentInRoster(student).getTuitionDue() - Double.parseDouble(paymentAmountTextField.getText()) < 0) && (roster.studentInRoster(student).wasCalculated() == true)){
                    outPutField.appendText("Cannot make a payment greater than the tuition due.\n");
                }
                else if(givenDate.getValue() == null){
                    outPutField.appendText("Must choose a payment date.\n");
                }
                else if((Double.parseDouble(paymentAmountTextField.getText()) > 0) && (roster.studentInRoster(student).wasCalculated() == true) && (roster.studentInRoster(student).getTuitionDue() - Double.parseDouble(paymentAmountTextField.getText()) >= 0) && (givenDate.getValue() != null)){
                    String date = givenDate.getValue().toString();
                    Date theGivenDate = new Date(date);
                    if(theGivenDate.isValid(theGivenDate) == true){
                        roster.studentInRoster(student).setTuitionDue(roster.studentInRoster(student).getTuitionDue() - Double.parseDouble(paymentAmountTextField.getText()));
                        roster.studentInRoster(student).setLastPaymentDate(theGivenDate);
                        roster.studentInRoster(student).setLastPayment((Double.parseDouble(paymentAmountTextField.getText())));
                        outPutField.appendText("Tuition updated.\n");
                    }
                    else if(theGivenDate.isValid(theGivenDate) == false){
                        outPutField.appendText("Given date is not a valid date.\n");
                    }
                }
                else{
                    outPutField.appendText("Not a student.\n");
                }
            }
        }
        catch (NumberFormatException e) {
            outPutField.appendText("Please only use numbers in the payment amount text field\n");
        }
    }



    /**
     * Calculates the tuition for a single student
     *
     * @param event the event of pressing the calculate student button
     * @author Mikita Belausau
     */
    @FXML
    void calculateOneStudentTuition(ActionEvent event) {
        setStudentNameThree(event);
        setMajorGroupThree(event);
        if (checkIfValidStudentInputForDelete(event)) {
                Student student = new Student(studentNameTextFieldThree.getText(), major);
                outPutField.appendText(roster.calculateOnePersonsTuition(student));
        }
    }

    /**
     * Calculates the entire rosters tuition
     *
     * @param event the event of pressing the entire roster button
     * @author Mikita Belausau
     */
    @FXML
    void calculateEntireRosterTuition(ActionEvent event) {
        if (roster.getSize() == 0) {
            outPutField.appendText("Roster is empty nothing to calculate.\n");
        } else {
            roster.calculateTuition();
            outPutField.appendText("Roster tuition calculated\n");
        }
    }

    /**
     * Sets an international student who is studying abroad to not studying abroad anymore.
     *
     * @param event the event of pressing the setNotStudyAbroad button
     * @author Mikita Belausau
     */
    @FXML
    void performNotStudyAbroad(ActionEvent event) {
        setStudentName(event);
        setMajorGroup(event);
        if (checkIfValidStudentInputForDelete(event)) {
            Student student = new Student(studentNameTextField.getText(), major);
            if (roster.studentInRoster(student) == null) {
                outPutField.appendText("International student not found.\n");

            } else if (roster.replaceStudyAbroad(student) != null) {
                International international = (International) roster.replaceStudyAbroad(student);
                if (international.getStudyAbroadStatus() == true) {
                    international.setStudyAbroadStatus(false);
                    outPutField.appendText(studentNameTextField.getText() + " is now not studying abroad. \n");
                    international.tuitionDue();
                    roster.studentInRoster(student).setTuitionDue(((roster.studentInRoster(student).getTuitionDue()) - roster.studentInRoster(student).getLastPayment()));
                } else {
                    outPutField.appendText("Student is not studying abroad.\n");
                }
            } else {
                outPutField.appendText("Student is not an international student.\n");
            }

        }
    }
}
