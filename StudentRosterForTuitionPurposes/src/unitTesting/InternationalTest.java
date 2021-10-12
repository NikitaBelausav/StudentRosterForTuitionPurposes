package unitTesting;
import project.two.Major;
import org.junit.Test;
import project.two.International;

import static org.junit.Assert.*;

public class InternationalTest {

	@Test
    public void tuitionDue() {
    	// Testing for International student with 12 credits and is studying abroad
    	International international = new International("Person One", Major.CS, 12, true);
    	international.tuitionDue();
        assertEquals(international.getTuitionDue(), 5918.0, 0);
        
    	// Testing for International student with 12 credits and not studying abroad
        international = new International("Person Two", Major.IT, 12, false);
    	international.tuitionDue();
        assertEquals(international.getTuitionDue(), 35655.0, 0);
        
    	// Testing for International student with over 12 credits and not studying abroad
        international = new International("Person Three", Major.ME, 24, false);
    	international.tuitionDue();
        assertEquals(international.getTuitionDue(), 43383.0, 0);
        
    	// Testing for International student with over 12 credits and is studying abroad
        international = new International("Person Four", Major.EE, 24, true);
    	international.tuitionDue();
        assertEquals(international.getTuitionDue(), 5918.0, 0);
        
        // Testing for International student with invalid credits
        international = new International("Person Five", Major.CS, 11, true);
    	international.tuitionDue();
        assertEquals(international.getTuitionDue(), 0.0, 0);

    }
}