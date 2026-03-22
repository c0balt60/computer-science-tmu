package labs;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SpySetTest {
    private SpySet<Integer> spySet;

    @Before
    public void setUp() {
        // Initialize SpySet with a duplicate limit of 3
        spySet = new SpySet<>(3);
    }

    @Test
    public void testAddElementOnce() {
        // Adding an element for the first time, should return true
        assertTrue(spySet.add(10));
    }

    @Test
    public void testAddDuplicateElementsWithinLimit() {
        // Adding duplicate elements within the limit (3 times)
        assertTrue(spySet.add(20));
        assertFalse(spySet.add(20));
        assertFalse(spySet.add(20));
    }

    @Test
    public void testAddDuplicateElementsExceedLimit() {
        // Adding duplicate elements exceeding the limit (4 times)
        assertTrue(spySet.add(30));
        assertFalse(spySet.add(30));
        assertFalse(spySet.add(30));
        assertFalse(spySet.add(30)); // Limit exceeded, set should be cleared
        assertEquals(0, spySet.size()); // The set should be empty after clearing
        assertTrue(spySet.add(30));
    }

    @Test
    public void testGetDuplicateCount() {
        // Adding duplicate elements
        spySet.add(40);
        spySet.add(40);
        spySet.add(40);

        // The duplicate count should be 2 (3 duplicates added, 1st addition is not
        // considered)
        assertEquals(2, spySet.getDuplicateCount());
    }

    @Test
    public void testGetDuplicateLimit() {
        // Duplicate limit set in the constructor is 3
        assertEquals(3, spySet.getDuplicateLimit());
    }

    @Test
    public void testAddDifferentElements() {
        // Adding different elements to the set
        assertTrue(spySet.add(50));
        assertTrue(spySet.add(60));
        assertTrue(spySet.add(70));

        // The set size should be 3 (no duplicates added)
        assertEquals(3, spySet.size());
    }

    @Test
    public void testAddNullElement() {
        // Adding null element to the set
        assertTrue(spySet.add(null));

        // Adding null element again, should return false as it's considered a duplicate
        assertFalse(spySet.add(null));
    }

    @Test
    public void testAddDuplicateElementsWithClear() {
        // Adding duplicate elements within the limit (3 times)
        assertTrue(spySet.add(80));
        assertFalse(spySet.add(80));
        assertFalse(spySet.add(80));

        // Adding a duplicate element again, exceeding the limit, the set should be
        // cleared
        assertFalse(spySet.add(80));
        assertEquals(0, spySet.size()); // The set should be empty after clearing

        // Adding new elements after clearing the set
        assertTrue(spySet.add(90));
        assertTrue(spySet.add(100));
        assertTrue(spySet.add(110));
        assertEquals(3, spySet.size()); // The set should have three new elements
    }

    @Test
    public void testRemoveElement() {
        // Adding elements to the set
        spySet.add(120);
        spySet.add(130);

        // Removing an element, should return true
        assertTrue(spySet.remove(120));
        assertEquals(1, spySet.size()); // The set should have one element after removal

        // Removing the same element again, should return false as it's no longer
        // present
        assertFalse(spySet.remove(120));
    }

    @Test
    public void testClearSet() {
        // Adding elements to the set
        spySet.add(140);
        spySet.add(150);
        spySet.add(140);
        spySet.add(150);

        // Clearing the set
        spySet.clear();
        assertEquals(0, spySet.size()); // The set should be empty after clearing
        assertEquals(0, spySet.getDuplicateCount()); // Duplicate count should be reset to 0
    }

    @Test
    public void testAddDuplicateElementsWithClearAfterDifferentElements() {
        // Adding different elements to the set
        assertTrue(spySet.add(200));
        assertTrue(spySet.add(210));
        assertTrue(spySet.add(220));

        // Adding duplicate elements within the limit (3 times)
        assertTrue(spySet.add(230));
        assertFalse(spySet.add(230));
        assertFalse(spySet.add(230));

        // Adding a duplicate element again, exceeding the limit, the set should be
        // cleared
        assertFalse(spySet.add(230));
        assertEquals(0, spySet.size()); // The set should be empty after clearing

        // Adding new elements after clearing the set
        assertTrue(spySet.add(240));
        assertTrue(spySet.add(250));
        assertTrue(spySet.add(260));
        assertEquals(3, spySet.size()); // The set should have three new elements
    }

    @Test
    public void testAddDuplicateElementsWithClearAfterRemovingElement() {
        // Adding elements to the set
        spySet.add(270);
        spySet.add(280);
        spySet.add(290);

        // Removing an element
        assertTrue(spySet.remove(270));

        // Adding duplicate elements within the limit (3 times)
        assertTrue(spySet.add(300));
        assertFalse(spySet.add(300));
        assertFalse(spySet.add(300));

        // Adding a duplicate element again, exceeding the limit, the set should be
        // cleared
        assertFalse(spySet.add(300));
        assertEquals(0, spySet.size()); // The set should be empty after clearing

        // Adding new elements after clearing the set
        assertTrue(spySet.add(310));
        assertTrue(spySet.add(320));
        assertTrue(spySet.add(330));
        assertEquals(3, spySet.size()); // The set should have three new elements
    }

    @Test
    public void testAddDuplicateElementsWithClearAfterClearingSetManually() {
        // Adding elements to the set
        spySet.add(340);
        spySet.add(350);
        spySet.add(360);

        // Clearing the set manually
        spySet.clear();

        // Adding duplicate elements within the limit (3 times)
        assertTrue(spySet.add(370));
        assertFalse(spySet.add(370));
        assertFalse(spySet.add(370));

        // Adding a duplicate element again, exceeding the limit, the set should be
        // cleared
        assertFalse(spySet.add(370));
        assertEquals(0, spySet.size()); // The set should be empty after clearing

        // Adding new elements after clearing the set
        assertTrue(spySet.add(380));
        assertTrue(spySet.add(390));
        assertTrue(spySet.add(400));
        assertEquals(3, spySet.size()); // The set should have three new elements
    }

    @Test
    public void testAddDuplicateElementsWithClearAfterChangingDuplicateLimit() {
        // Adding duplicate elements with duplicate limit set to 1
        SpySet<String> stringSpySet = new SpySet<>(1);
        assertTrue(stringSpySet.add("hello"));
        assertFalse(stringSpySet.add("hello")); // Limit exceeded, set should be cleared
        assertEquals(0, stringSpySet.size()); // The set should be empty after clearing

        // Changing the duplicate limit to 2
        stringSpySet = new SpySet<>(2);
        assertTrue(stringSpySet.add("world"));
        assertFalse(stringSpySet.add("world"));
        assertFalse(stringSpySet.add("world")); // Limit exceeded, set should be cleared
        assertEquals(0, stringSpySet.size()); // The set should be empty after clearing

        // Adding new elements after changing the duplicate limit
        assertTrue(stringSpySet.add("test"));
        assertTrue(stringSpySet.add("example"));
        assertEquals(2, stringSpySet.size()); // The set should have two new elements
    }
}
