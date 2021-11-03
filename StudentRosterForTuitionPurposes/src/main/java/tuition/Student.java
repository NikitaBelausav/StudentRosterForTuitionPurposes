/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

import java.text.DecimalFormat;


/**
 * Student class, calculates tuitions due and creates Student objects.
 *
 * @author Mikita Belausau, Harpreet Randhawa
 */
public class Student {
    private int TOTAL_CREDIT_HOURS;
    public static final int UNIVERSITY_FEE_FULL_TIME = 3268;
    public static final double UNIVERSITY_FEE_PART_TIME = 0.80 * UNIVERSITY_FEE_FULL_TIME;
    private double tuitionDue;
    private Profile profile;
    private Date lastPaymentDate = null;
    private double lastPayment = 0.00;
    private boolean wasCalculated = false;
    public static final int MINIMUM_CREDIT_FOR_FULL_TIME = 12;
    public static final int MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES = 16;

    /**
     * Student constructor
     *
     * @param name               The student's name.
     * @param major              The student's major.
     * @author Mikita Belausau
     */
    public Student(String name, Major major) {
        this.profile = new Profile(name, major);
    }

    /**
     * Getter for profile of a student
     *
     * @return returns a profile of the student which includes name and major
     * @author Mikita Belausau
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Calculates tuitionDue for students.
     *
     * @author Harpreet Randhawa
     */
    public void tuitionDue() {

    }

    /**
     * Returns if the tuition was already calculated for a student or not.
     *
     * @return True if the tuition has been calculated, false if not.
     * @author Mikita Belausau
     */
    public boolean wasCalculated() {
        return this.wasCalculated;
    }

    /**
     * Set a boolean value to be true or false depending on if the students tuition was calculated.
     *
     * @param wasCalculated The previous calculation.
     * @author Mikita Belausau
     */
    public void setWasCalculated(boolean wasCalculated) {
        this.wasCalculated = wasCalculated;
    }

    /**
     * Getter for student's last payment.
     *
     * @return The last payment.
     * @author Harpreet Randhawa
     */
    public double getLastPayment() {
        return this.lastPayment;
    }

    /**
     * Getter for student's last payment date.
     *
     * @return The last payment date.
     * @author Harpreet Randhawa
     */
    public Date getLastPaymentDate() {
        return this.lastPaymentDate;
    }

    /**
     * Getter for student's tuition due.
     *
     * @return The tuition due.
     * @author Harpreet Randhawa
     */
    public double getTuitionDue() {
        return this.tuitionDue;
    }

    /**
     * Setter for student's tuition due.
     *
     * @param tuitionDue Student's tuition due.
     * @author Mikita Belausau
     */
    public void setTuitionDue(double tuitionDue) {
        this.tuitionDue = tuitionDue;
    }

    /**
     * Setter for student's last payment date.
     *
     * @param lastPaymentDate Student's last payment date.
     * @author Mikita Belausau
     */
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * Setter for student's total payment amount.
     *
     * @param payment Student's payment.
     * @author Mikita Belausau
     */
    public void setLastPayment(double payment) {
        this.lastPayment += payment;
    }


    /**
     * Get the hours of the part time student.
     *
     * @return The student's total credit hours.
     * @author Harpreet Randhawa
     */
    public int getTotalCreditHours() {
        return this.TOTAL_CREDIT_HOURS;
    }

    /**
     * Set the hours of the part time student.
     *
     * @param partTimeHours The student's part time hours.
     * @author Harpreet Randhawa
     */
    public void setTotalCreditHours(int partTimeHours) {
        this.TOTAL_CREDIT_HOURS = partTimeHours;
    }

    /**
     * Creates a textual representation of a student in proper format.
     *
     * @return Student information
     * @author Harpreet Randhawa
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);


        if (this.getLastPaymentDate() == null) {
            return this.profile.getName() + ":" + this.profile.getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
                    + "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" +
                    decimalFormat.format(this.getLastPayment()) + ":" + "last payment date:" + " --/--/--";
        } else {
            return this.profile.getName() + ":" + this.profile.getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
                    + "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" +
                    decimalFormat.format(this.getLastPayment()) + ":" + "last payment date: "
                    + this.getLastPaymentDate().toString();
        }
    }

    /**
     * Checks to see if two students are equal based on their profiles.
     *
     * @param obj The given object.
     * @return true if the students have the same name and major, false if not
     * @author Mikita Belausau
     */
    @Override
    public boolean equals(Object obj) {
        Student profileObject = Student.class.cast(obj);
        if (this.profile.equals(profileObject.profile)) {
            return true;
        } else {
            return false;
        }
    }


}
