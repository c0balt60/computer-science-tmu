package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccessCountArrayListTest {

    private AccessCountArrayList<String> list;

    @Before
    public void setUp() {
        list = new AccessCountArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Test
    public void testGetIncrementsAccessCount() {
        int initialCount = list.getAccessCount();
        String value = list.get(1);

        assertEquals("B", value); // Verify correct value is returned
        assertEquals(initialCount + 1, list.getAccessCount()); // Verify access count increments
    }

    @Test
    public void testSetIncrementsAccessCount() {
        int initialCount = list.getAccessCount();
        String oldValue = list.set(1, "X");

        assertEquals("B", oldValue); // Verify old value is returned
        assertEquals("X", list.get(1)); // Verify new value is set (this also increments count)
        assertEquals(initialCount + 2, list.getAccessCount()); // Verify access count increments from set
    }

    @Test
    public void testGetAccessCountInitialValue() {
        AccessCountArrayList<Integer> newList = new AccessCountArrayList<>();
        assertEquals(0, newList.getAccessCount()); // Verify initial count is 0
    }

    @Test
    public void testResetCount() {
        list.get(0); // Increment count
        list.set(2, "Z"); // Increment count again
        assertTrue(list.getAccessCount() > 0); // Verify count is non-zero

        list.resetCount();
        assertEquals(0, list.getAccessCount()); // Verify count is reset to 0
    }

    @Test
    public void testMultipleAccesses() {
        int initialCount = list.getAccessCount();
        list.get(0); // +1
        list.get(2); // +1
        list.set(1, "Y"); // +1

        assertEquals(initialCount + 3, list.getAccessCount()); // Verify multiple accesses are counted
        assertEquals("Y", list.get(1)); // Verify set worked
    }
}
