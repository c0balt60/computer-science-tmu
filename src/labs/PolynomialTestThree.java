package labs;

import org.junit.Test;
import java.util.zip.CRC32;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PolynomialTestThree {
    private boolean polyEq(Polynomial p1, Polynomial p2, CRC32 check) {
        if (p1.getDegree() != p2.getDegree()) {
            return false;
        }
        for (int k = 0; k <= p1.getDegree(); k++) {
            if (check != null) {
                check.update(p1.getCoefficient(k));
            }
            if (p1.getCoefficient(k) != p2.getCoefficient(k)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testMultiply1() {
        int[] c1 = { 7, -5, 3 }; // 3x^2 - 5x + 7
        int[] c2 = { 6, 0, 0, -4 }; // -4x^3 + 6
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        // Product of two polynomials must be equal both ways.
        Polynomial p3 = p1.multiply(p2);
        Polynomial p4 = p2.multiply(p1);
        assertTrue(polyEq(p3, p4, null));
        // The expected correct result of multiplying p1 and p2.
        int[] c5 = { 42, -30, 18, -28, 20, -12 };
        Polynomial p5 = new Polynomial(c5);
        assertTrue(polyEq(p3, p5, null));
    }

    @Test
    public void testMultiply2() {
        int[] c6 = { 0, 1, 0, 0, 0, -2, 0, 0, 0, 0, 1 };
        int[] c7 = { 1, 2, -4 };
        Polynomial p6 = new Polynomial(c6);
        Polynomial p7 = new Polynomial(c7);
        Polynomial p8 = p6.multiply(p7);
        Polynomial p9 = p7.multiply(p6);
        assertTrue(polyEq(p8, p9, null));
        assertTrue(polyEq(p9, p8, null));
        int[] c10 = { 0, 1, 2, -4, 0, -2, -4, 8, 0, 0, 1, 2, -4 };
        Polynomial p10 = new Polynomial(c10);
        assertTrue(polyEq(p8, p10, null));
    }

    @Test
    public void testEquals() {
        int[] c1 = { -10, 99, 11, 12 };
        int[] c2 = { -10, -99, 11, 12 };
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        Polynomial p3 = new Polynomial(c1);
        assertEquals(p1, p1);
        assertEquals(p2, p2);
        assertEquals(p1, p3);
        assertEquals(p3, p1);
        assertNotEquals(p1, p2);
        assertNotEquals(p2, p1);
        assertNotEquals("hello world", p1);
        assertNotEquals(p1, c1);
    }

    @Test
    public void testCompareTo() {
        int[] c1 = { -6, 99, 11, 12 };
        int[] c2 = { 6, -99, 11, 12 };
        int[] c3 = { 42, 10000000 };
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        Polynomial p3 = new Polynomial(c3);
        assertEquals(+1, p1.compareTo(p2));
        assertEquals(-1, p2.compareTo(p1));
        assertEquals(+1, p1.compareTo(p3));
        assertEquals(-1, p3.compareTo(p1));
        assertEquals(+1, p2.compareTo(p3));
        assertEquals(-1, p3.compareTo(p2));
        assertEquals(0, p1.compareTo(p1));
        assertEquals(0, p2.compareTo(p2));
        assertEquals(0, p3.compareTo(p3));
    }

}
