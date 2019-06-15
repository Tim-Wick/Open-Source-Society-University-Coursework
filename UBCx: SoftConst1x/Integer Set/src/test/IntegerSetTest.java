
package test;

import model.IntegerSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerSetTest {
    IntegerSet testSet;

    @Before
    public void setup(){
        testSet = new IntegerSet();
    }

    @Test
    public void testInsertNotThere(){
        //check that the number is not already in the set
        checkSetEmptyDoesntContain(3);

        //insert the number into the set
        testSet.insert(3);

        //check that the number is in the set once
        checkSetContainsOnce(3);
    }

    @Test
    public void testInsertAlreadyThere(){
        //check that the number is not already in the set
        checkSetEmptyDoesntContain(3);

        //insert the number into the set
        testSet.insert(3);

        //check that the number is in the set once
        checkSetContainsOnce(3);

        //insert the number again
        testSet.insert(3);

        //check that the number is in the set once
        checkSetContainsOnce(3);
    }

    private void checkSetContainsOnce(int num) {
        assertEquals(testSet.size(), 1);
        assertTrue(testSet.contains(num));
    }

    private void checkSetEmptyDoesntContain(int num) {
        assertEquals(testSet.size(), 0);
        assertFalse(testSet.contains(num));
    }

}
