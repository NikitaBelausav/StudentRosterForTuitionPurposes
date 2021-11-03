/**
 * @author Mikita Belausau, Harpreet Randhawa
 */
package tuition;

/**
 * Roster class, initializes a student roster and performs operations on the roster.
 *
 * @author Harpreet Randhawa, Mikita Belausau
 */
public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    private static final int GROWTH_FACTOR = 4;
    private static final int NOT_FOUND = -1;

    /**
     * Constructor for roster
     *
     * @author Harpreet Randhawa
     */
    public Roster() {
        this.roster = new Student[4];
        this.size = 0;
    }

    /**
     * Get the size of the roster.
     * @return the roster size
     * @author Mikita Belausau
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Iterates through roster to find the given student's index.
     * If index is not found, returns -1.
     *
     * @param student The given student to be searched.
     * @return The album's index. Returns NOT_FOUND if index is not found.
     * @author Harpreet Randhawa
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Automatically grow the array list when it reaches full capacity.
     *
     * @author Harpreet Randhawa
     */
    private void grow() {
        Roster increasedRoster = new Roster();
        increasedRoster.roster = new Student[this.roster.length + GROWTH_FACTOR];
        for (int i = 0; i < size; i++) {
            increasedRoster.roster[i] = this.roster[i];
        }
        this.roster = increasedRoster.roster;
    }

    /**
     * Adds the given student to the array list.
     *
     * @param student The given student to be added.
     * @return True if the student was added successfully and false otherwise.
     * @author Mikita Belausau
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND) {
            return false;
        }
        if ((this.size % GROWTH_FACTOR == 0) && (size != 0)) {
            this.grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    /**
     * Removes the given student from the array list while maintaining the relative sequence of the objects.
     *
     * @param student The given student to be removed.
     * @return True if the student was removed successfully and false otherwise.
     * @author Mikita Belausau
     */
    public boolean remove(Student student) {
        boolean found = false;
        int i = 0;
        if (find(student) != -1) {
            found = true;
            i = find(student);
            size--;
        }
        for (; i < size; i++) {
            if (found) {
                if (size == i) {
                    roster[i] = null;
                } else {
                    roster[i] = roster[i + 1];
                }
            }
        }
        if (found) {
            return true;
        }
        return false;
    }

    /**
     * Check to see if the students in the roster for usage in TuitionManager.
     *
     * @param student The given student.
     * @return The student or null if the student is not in the roster.
     * @author Mikita Belausau
     */
    public Student studentInRoster(Student student) {
        int i = find(student);
        if (i == NOT_FOUND) {
            return null;
        }
        return roster[i];
    }

    /**
     * Checks if the student is a resident.
     *
     * @param student The given student.
     * @return The student or null if the student is not in the roster.
     * @author Mikita Belausau
     */
    public Student replaceFinancialAid(Student student) {
        int i = find(student);
        if (i != NOT_FOUND) {
            if (roster[i] instanceof Resident) {
                return roster[i];
            }
        }
        return null;
    }

    /**
     * Checks if the given student is an international student or not.
     *
     * @param student The given student.
     * @return The student or null if the student is not in the roster.
     * @author Harpreet Randhawa
     */
    public Student replaceStudyAbroad(Student student) {
        int i = find(student);
        if (i != NOT_FOUND) {
            if (roster[i] instanceof International) {
                return roster[i];
            }
        }
        return null;
    }

    /**
     * Helper method to print the entire roster
     *
     * @return a string of all students in the roster.
     * @author Mikita Belausau, Harpreet Randhawa
     */

    public String print() {
        StringBuilder print = new StringBuilder();
        if (size == 0) {
            return "";
        } else {
            for (int i = 0; i < size; i++) {
                print.append(roster[i].toString() + "\n");
            }
            return print.toString();
        }
    }

    /**
     * Displays the roster sorted by student names.
     *
     * @return a string of the roster printed by name order
     * @author Harpreet Randhawa, Mikita Belausau
     */
    public String printByName() {
        StringBuilder print = new StringBuilder();
        if (size == 0) {
            return "";
        } else {
            Student[] temp = new Student[1];
            for (int i = 0; i < this.size; i++) {
                for (int j = i + 1; j < this.size; j++) {
                    if (roster[i].getProfile().getName().compareTo(roster[j].getProfile().getName()) > 0) {
                        temp[0] = roster[i];
                        roster[i] = roster[j];
                        roster[j] = temp[0];
                    }
                }
            }
            for (int k = 0; k < this.size; k++) {
                print.append(roster[k].toString() + "\n");
            }
            return print.toString();
        }
    }

    /**
     * Creates a temporary roster of students who have made payments sorted by when they paid, and displays it.
     *
     * @return a string of the temporary roster created that holds only students that have paid, and in order of payments.
     * @author Mikita Belausau
     */
    public String printByDate() {
        StringBuilder print = new StringBuilder();
        if (size == 0) {
            return "";
        } else {
            int tempRosterLength = 0;
            for (int q = 0; q < size; q++) {
                if (roster[q].getLastPaymentDate() != null) {
                    tempRosterLength++;
                }
            }
            Student[] tempRoster = new Student[tempRosterLength];
            Student[] temp = new Student[1];
            for (int i = 0; i < tempRosterLength; i++) {
                for (int j = 0; j < size; j++) {
                    if (roster[j].getLastPaymentDate() != null) {
                        tempRoster[i] = roster[j];
                        i++;
                    }
                }
            }
            for (int k = 0; k < tempRosterLength; k++) {
                for (int l = 0; l < tempRosterLength; l++) {
                    if ((k != l) &&
                            (((tempRoster[k].getLastPaymentDate()).compareTo((tempRoster[l].getLastPaymentDate()))) < 0)) {
                        temp[0] = tempRoster[k];
                        tempRoster[k] = tempRoster[l];
                        tempRoster[l] = temp[0];
                    }
                }
            }
            for (int p = 0; p < tempRosterLength; p++) {
                print.append(tempRoster[p].toString() + "\n");
            }
            return print.toString();
        }
    }

    /**
     * Calculates tuitionDue For all students in roster, unless it was already calculated prior.
     *
     * @author Mikita Belausau
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            if (!(roster[i].wasCalculated())) {
                roster[i].tuitionDue();
            }
        }
    }

    /**
     * calculates tuitionDue for a single student
     *
     * @param student the student whos tuition you wish to calculate
     * @return a string that explains what operation was performed
     * @author Mikita Belausau
     */

    public String calculateOnePersonsTuition(Student student) {
        int i = find(student);
        if (size == 0) {
            return "Empty roster, nothing to calculate.\n";
        } else if (find(student) < 0) {
            return "Student does not exist in the roster.\n";
        } else if (roster[i].wasCalculated()) {
            return "Students tuition already calculated.\n";
        } else {
            roster[i].tuitionDue();
            return "Student's tuition was calculated successfully.\n";
        }
    }
}
