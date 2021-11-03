/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

import java.text.DecimalFormat;

/**
* International Student class, calculates tuitions due and creates international student objects.

* @author Mikita Belausau, Harpreet Randhawa
*/
public class International extends NonResident {
    private static final int INTERNATIONAL_TUITION_FULL_TIME = 29737;
    private static final int INTERNATIONAL_ADDITIONAL_FEE = 2650;
    private boolean studyAbroad;

    /**
     * Constructor for international student objects.
     *
     * @param name               The student's name.
     * @param major              The student's major.
     * @param TOTAL_CREDIT_HOURS The student's total credit hours.
     * @param studyAbroad        The student's study abroad status.
     * @author Mikita Belausau
     */
    public International(String name, Major major, int TOTAL_CREDIT_HOURS, boolean studyAbroad) {
        super(name, major);
        this.studyAbroad = studyAbroad;
        this.setTotalCreditHours(TOTAL_CREDIT_HOURS);
    }

    /**
     * Get the International student's study abroad status.
     *
     * @return The study abroad status.
     * @author Harpreet Randhawa
     */
    public boolean getStudyAbroadStatus() {
        return this.studyAbroad;
    }

    /**
     * Setter for the International student's study abroad status.
     *
     * @param studyAbroad The student's study abroad status.
     * @author Harpreet Randhawa
     */
    public void setStudyAbroadStatus(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    /**
     * Calculates the tuition due for an International student.
     *
     * @author Harpreet Randhawa
     */
    @Override
    public void tuitionDue() {
        if ((this.getTotalCreditHours() >= MINIMUM_CREDIT_FOR_FULL_TIME) &&
                (this.getTotalCreditHours() <= MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES)
                && (this.getStudyAbroadStatus() == false)) {
            this.setTuitionDue((INTERNATIONAL_TUITION_FULL_TIME + UNIVERSITY_FEE_FULL_TIME + INTERNATIONAL_ADDITIONAL_FEE));
            this.setWasCalculated(true);
        } else if ((this.getTotalCreditHours() >= MINIMUM_CREDIT_FOR_FULL_TIME)
                && (this.getTotalCreditHours() > MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES)
                && (this.getStudyAbroadStatus() == false)) {
            this.setTuitionDue((INTERNATIONAL_TUITION_FULL_TIME + UNIVERSITY_FEE_FULL_TIME + INTERNATIONAL_ADDITIONAL_FEE
                    + ((this.getTotalCreditHours() - MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES) * 966)));
            this.setWasCalculated(true);
        } else if ((this.getTotalCreditHours() >= MINIMUM_CREDIT_FOR_FULL_TIME)
                && (this.getStudyAbroadStatus() == true)) {
            this.setTuitionDue((UNIVERSITY_FEE_FULL_TIME + INTERNATIONAL_ADDITIONAL_FEE));
            this.setWasCalculated(true);
        }
    }

    /**
     * Creates a textual representation of an international student in proper format.
     *
     * @return Returns international student information.
     * @author Harpreet Randhawa, Mikita Belausau
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        if ((this.getStudyAbroadStatus() == true) && (this.getLastPaymentDate() != null)) {
            return super.toString() +  ":" + "international" + ":" + "study abroad";
        } else if ((this.getStudyAbroadStatus() == false) && (this.getLastPaymentDate() != null)) {
            return super.toString() + ":" + "international";
        } else if ((this.getStudyAbroadStatus() == true) && (this.getLastPaymentDate() == null)) {
            return super.toString() + ":" + "international" + ":" + "study abroad";
        } else if ((this.getStudyAbroadStatus() == false) && (this.getLastPaymentDate() == null)) {
            return super.toString() + ":" + "international";
        }
    return "";
    }
}
