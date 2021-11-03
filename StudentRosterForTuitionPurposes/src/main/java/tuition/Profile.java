/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

/**
 * Profile object for students, contains name and major.
 *
 * @author Mikita Belausau, Harpreet Randhawa
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-character each; CS, IT, BA, EE, ME

    /**
     * Profile constructor for students.
     *
     * @param name  The student's name.
     * @param major The student's major.
     * @author Harpreet Randhawa
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Get the student's name.
     *
     * @return The student's name.
     * @author Harpreet Randhawa
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the student's name.
     *
     * @param name Student's name
     * @author Harpreet Randhawa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the student's major.
     *
     * @return The student's major.
     * @author Harpreet Randhawa
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Setter for the student's major.
     *
     * @param major Student's major
     * @author Harpreet Randhawa
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Determines if two given profiles have the same name and major.
     *
     * @param obj The profile object being compared.
     * @return True if the two profiles have the same name and major. Returns false otherwise.
     * @author Harpreet Randhawa
     */
    @Override
    public boolean equals(Object obj) {
        Profile profileObject = Profile.class.cast(obj);
        if (this.name.equals(profileObject.name) && this.major.equals(profileObject.major)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a textual representation of a profile in proper format.
     *
     * @return Profile information.
     * @author Harpreet Randhawa
     */
    @Override
    public String toString() {
        return this.name + ":" + this.major;
    }

}
