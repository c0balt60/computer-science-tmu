import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigOTest 
{
    @Test public void testBigOString1() {
        int[] c1 = {5, 0, 2, 3};
        BigO b1 = new BigO(c1);
        assertTrue(b1 instanceof Polynomial);
        assertEquals("O(n^3)", b1.toString());
    }

    @Test public void testBigOString2() {
        int[] c2 = {42, 99, 3, 10, 4};
        BigO b2 = new BigO(c2);
        assertTrue(b2 instanceof Polynomial);
        assertEquals("O(n^4)", b2.toString());
    }

    @Test public void testBigOString3() {
        int[] c3 = {99, 0, 0, 0, 0, 0, 0, 0};
        BigO b3 = new BigO(c3);
        assertTrue(b3 instanceof Polynomial);
        assertEquals("O(1)", b3.toString());
    }

    @Test public void testBigOString4() {
        int[] c4 = {0, 55, 0, 0};
        BigO b4 = new BigO(c4);
        assertTrue(b4 instanceof Polynomial);
        assertEquals("O(n)", b4.toString());
    }

    @Test public void testBigOString5() {
        int[] c5 = {1, 0, 1};
        BigO b5 = new BigO(c5);
        assertTrue(b5 instanceof Polynomial);
        assertEquals("O(n^2)", b5.toString());
    }

    @Test public void testBigOString6() {
        int[] c6 = {1, 2, 3, 4, 5, 6};
        BigO b6 = new BigO(c6);
        assertTrue(b6 instanceof Polynomial);
        assertEquals("O(n^5)", b6.toString());
    }

    @Test public void testBigOCategory1() {
        int[] c1 = {5, 0, 2, 3};
        BigO b1 = new BigO(c1);
        assertTrue(b1 instanceof Polynomial);
        assertEquals("cubic", b1.getCategory());
    }

    @Test public void testBigOCategory2() {
        int[] c2 = {42, 99, 3, 10, 4};
        BigO b2 = new BigO(c2);
        assertTrue(b2 instanceof Polynomial);
        assertEquals("polynomial", b2.getCategory());
    }

    @Test public void testBigOCategory3() {
        int[] c3 = {99, 0, 0, 0, 0, 0, 0, 0};
        BigO b3 = new BigO(c3);
        assertTrue(b3 instanceof Polynomial);
        assertEquals("constant", b3.getCategory());
    }

    @Test public void testBigOCategory4() {
        int[] c4 = {0, 55, 0, 0};
        BigO b4 = new BigO(c4);
        assertTrue(b4 instanceof Polynomial);
        assertEquals("linear", b4.getCategory());
    }

    @Test public void testBigOCategory5() {
        int[] c5 = {1, 0, 1};
        BigO b5 = new BigO(c5);
        assertTrue(b5 instanceof Polynomial);
        assertEquals("quadratic", b5.getCategory());
    }

    @Test public void testBigOCategory6() {
        int[] c6 = {1, 2, 3, 4, 5, 6};
        BigO b6 = new BigO(c6);
        assertTrue(b6 instanceof Polynomial);
        assertEquals("polynomial", b6.getCategory());
    }

}


