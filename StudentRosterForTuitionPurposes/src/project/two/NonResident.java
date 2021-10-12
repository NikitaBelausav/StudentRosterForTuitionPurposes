/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package project.two;

import java.text.DecimalFormat;

/**
* NonResident Student class, calculates tuitions due and creates nonResident student objects.

* @author Mikita Belausau, Harpreet Randhawa
*/
public class NonResident extends Student {
    private static final int NON_RESIDENT_TUITION_FULL_TIME = 29737;
    private int NON_RESIDENT_TUITION_PART_TIME = 966 * getTotalCreditHours();

    /**
    * Constructor for nonResident students.
   
    * @param name The student's name.
    * @param major The student's major.
    * @param TOTAL_CREDIT_HOURS The student's total credit hours.
    * @author Mikita Belausau
    */
    public NonResident(String name, Major major, int TOTAL_CREDIT_HOURS) {
        super(name, major, TOTAL_CREDIT_HOURS);
    }

    /**
    * Calculate TuitionDue for nonResidents.
    
    * @author Mikita Belausau
    */
    @Override
    public void tuitionDue() {
        if (this.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) {
            this.setTuitionDue((NON_RESIDENT_TUITION_PART_TIME + UNIVERSITY_FEE_PART_TIME));
            this.setWasCalculated(true);
        } else if ((!(this.getTotalCreditHours() > MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES))) {
            this.setTuitionDue((NON_RESIDENT_TUITION_FULL_TIME + UNIVERSITY_FEE_FULL_TIME));
            this.setWasCalculated(true);
        } else {
            this.setTuitionDue((NON_RESIDENT_TUITION_FULL_TIME +
                    ((this.getTotalCreditHours() - MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES) * 966)
                    + UNIVERSITY_FEE_FULL_TIME));
            this.setWasCalculated(true);
        }
    }

    /**
    * Creates a textual representation of a non-resident in proper format.
    
    * @return Non-resident information.
    * @author Harpreet Randhawa
    */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        
        if (this.getLastPaymentDate() == null) {
            return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
                    + "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
                    decimalFormat.format(this.getLastPayment()) +
            		":" + "last payment date:" + " --/--/--" + ":" + "non-resident";
        } else {
            return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
                    + "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
                    decimalFormat.format(this.getLastPayment()) +
                    ":" + "last payment date: " + this.getLastPaymentDate().toString() + ":" + "non-resident";
        }
    }
}
