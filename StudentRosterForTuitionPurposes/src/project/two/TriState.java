/**
@author Mikita Belausau, Harpreet Randhawa
*/
package project.two;

import java.text.DecimalFormat;

/**
* TriState Student class, calculates tuitions due and creates TriState student objects.

* @author Mikita Belausau, Harpreet Randhawa
*/
public class TriState extends NonResident {
    private static final String NY = "NY";
    private static final String CT = "CT";
	private static final int NYC_DISCOUNT = 4000;
	private static final int CT_DISCOUNT = 5000;
    private int TRI_STATE_TUITION_FULL_TIME = 29737;
    private int TRI_STATE_TUITION_PART_TIME = 966 * getTotalCreditHours();
    private String triState;

    /**
    * Constructor for TriState students.
    
    * @param name The student's name.
    * @param major The student's major.
    * @param TOTAL_CREDIT_HOURS The student's total credit hours.
    * @param triState The tri-state location
    * @author Mikita Belausau
    */
    public TriState(String name, Major major, int TOTAL_CREDIT_HOURS, String triState){
        super(name, major, TOTAL_CREDIT_HOURS);
        this.triState = triState;
    }

    /**
    Calculates the tuition due for a tri-state student.
    
    @author Harpreet Randhawa
    */
    @Override
    public void tuitionDue() {        
        if(this.triState == null){
            ;
        }
        else if ((this.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) && (this.triState.equals(NY))) {
            this.setTuitionDue(((TRI_STATE_TUITION_PART_TIME + UNIVERSITY_FEE_PART_TIME)));
            this.setWasCalculated(true);
        }
        else if ((this.getTotalCreditHours() < MINIMUM_CREDIT_FOR_FULL_TIME) && (this.triState.equals(CT))) {
            this.setTuitionDue(((TRI_STATE_TUITION_PART_TIME + UNIVERSITY_FEE_PART_TIME)));
            this.setWasCalculated(true);
        }
        else if (((!(this.getTotalCreditHours() > MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES))) && (this.triState.equals(NY))) {
            this.setTuitionDue((((TRI_STATE_TUITION_FULL_TIME + UNIVERSITY_FEE_FULL_TIME) - NYC_DISCOUNT)));
            this.setWasCalculated(true);
        }
        else if (((!(this.getTotalCreditHours() > MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES))) && (this.triState.equals(CT))) {
            this.setTuitionDue((((TRI_STATE_TUITION_FULL_TIME + UNIVERSITY_FEE_FULL_TIME) - CT_DISCOUNT)));
            this.setWasCalculated(true);
        }
        else {
        	if (this.triState.equals(NY)) {
                this.setTuitionDue(((((TRI_STATE_TUITION_FULL_TIME +
                        ((this.getTotalCreditHours() - MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES) * 966))
                        + UNIVERSITY_FEE_FULL_TIME) - NYC_DISCOUNT)));
                this.setWasCalculated(true);
        	}
        	else if (this.triState.equals(CT)) {
                this.setTuitionDue(((((TRI_STATE_TUITION_FULL_TIME +
                        ((this.getTotalCreditHours() - MAXIMUM_CREDIT_FOR_FULL_TIME_WITH_NO_ADDITIONAL_FEES) * 966))
                        + UNIVERSITY_FEE_FULL_TIME) - CT_DISCOUNT)));
                this.setWasCalculated(true);
        	}
        }
    }


    
    /**
    Creates a textual representation of tri-state in proper format.
    
    @return Returns tri-state information.
    @author Harpreet Randhawa
    */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        
        if((this.triState.equals(NY)) && (this.getLastPaymentDate() == null)) {
        	return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
        			+ "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
        			decimalFormat.format(this.getLastPayment()) +
        			":" + "last payment date:" + " --/--/--" + ":" + "non-resident(tri-state)" + 
        			":" + "NY";
        }
        else if((this.triState.equals(CT)) && (this.getLastPaymentDate() == null)) {
        	return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
        			+ "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
        			decimalFormat.format(this.getLastPayment()) +
        			":" + "last payment date:" + " --/--/--" + ":" + "non-resident(tri-state)" + 
        			":" + "CT";
        }
        else if((this.triState.equals(NY)) && (this.getLastPaymentDate() != null)) {
        	return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
        			+ "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
        			decimalFormat.format(this.getLastPayment()) +
        			":" + "last payment date: " + this.getLastPaymentDate().toString() + ":" + "non-resident(tri-state)" + 
        			":" + "NY";
        }
        else if((this.triState.equals(CT)) && (this.getLastPaymentDate() != null)) {
        	return this.getProfile().getName() + ":" + this.getProfile().getMajor() + ":" + this.getTotalCreditHours() + " credit hours:"
        			+ "tuition due:" + decimalFormat.format(this.getTuitionDue()) + ":" + "total payment:" + 
        			decimalFormat.format(this.getLastPayment()) +
        			":" + "last payment date: " + this.getLastPaymentDate().toString() + ":" + "non-resident(tri-state)" + 
        			":" + "CT";
        }
        return "";
    }
    
}
