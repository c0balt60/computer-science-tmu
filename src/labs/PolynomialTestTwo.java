package labs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.zip.CRC32;

public class PolynomialTestTwo {

    @Test
    public void testPolyEval1() {
        int[] c1 = { 5, 0, -2, 3 };
        Polynomial p1 = new Polynomial(c1);
        assertEquals(5, p1.evaluate(0));
        assertEquals(21, p1.evaluate(2));
    }

    @Test
    public void testPolyEval2() {
        int[] c2 = { 42, 99, -3, -10, -4 };
        Polynomial p2 = new Polynomial(c2);
        assertEquals(42, p2.evaluate(0));
        assertEquals(-282, p2.evaluate(3));
    }

    @Test
    public void testPolyEval3() {
        int[] c3 = { 99, 0, 0, 0, 0, 0, 0, 0 };
        Polynomial p3 = new Polynomial(c3);
        assertEquals(99, p3.evaluate(10));
        assertEquals(99, p3.evaluate(50));
    }

    @Test
    public void testPolyEval4() {
        int[] c4 = { 0, 0, 0, 0 };
        Polynomial p4 = new Polynomial(c4);
        assertEquals(0, p4.evaluate(7));
    }

    @Test
    public void testPolyEval5() {
        int[] c5 = { 1, 0, 1, 0, 1 };
        Polynomial p5 = new Polynomial(c5);
        assertEquals(3, p5.evaluate(1));
        assertEquals(21, p5.evaluate(2));
        assertEquals(91, p5.evaluate(3));
    }

    @Test
    public void testPolyEval6() {
        int[] c6 = { -1, -2, -3, -4, -5, -6 };
        Polynomial p6 = new Polynomial(c6);
        assertEquals(-654321, p6.evaluate(10));
    }

    @Test
    public void testPolyString1() {
        int[] c1 = { 5, 0, -2, 3 };
        Polynomial p1 = new Polynomial(c1);
        assertEquals("3x^3 - 2x^2 + 5", p1.toString());
    }

    @Test
    public void testPolyString2() {
        int[] c2 = { 42, 99, -3, -10, -4 };
        Polynomial p2 = new Polynomial(c2);
        assertEquals("-4x^4 - 10x^3 - 3x^2 + 99x + 42", p2.toString());
    }

    @Test
    public void testPolyString3() {
        int[] c3 = { 99, 0, 0, 0, 0, 0, 0, 0 };
        Polynomial p3 = new Polynomial(c3);
        assertEquals("99", p3.toString());
    }

    @Test
    public void testPolyString4() {
        int[] c4 = { 0, 0, 0, 0 };
        Polynomial p4 = new Polynomial(c4);
        assertEquals("0", p4.toString());
    }

    @Test
    public void testPolyString5() {
        int[] c5 = { 1, 0, 1, 0, 1 };
        Polynomial p5 = new Polynomial(c5);
        assertEquals("1x^4 + 1x^2 + 1", p5.toString());
    }

    @Test
    public void testPolyString6() {
        int[] c6 = { -1, -2, -3, -4, -5, -6 };
        Polynomial p6 = new Polynomial(c6);
        assertEquals("-6x^5 - 5x^4 - 4x^3 - 3x^2 - 2x - 1", p6.toString());
    }

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
    public void testPolyAdd1() {
        int[] t01 = { 0 };
        int[] t02 = { -42, 99, 17, 101 };
        int[] e0 = { -42, 99, 17, 101 };
        Polynomial p01 = new Polynomial(t01);
        Polynomial p02 = new Polynomial(t02);
        Polynomial r0 = p01.add(p02);
        Polynomial p0e = new Polynomial(e0);
        assertTrue(polyEq(r0, p0e, null));
    }

    @Test
    public void testPolyAdd2() {
        int[] t11 = { 5, -5, 2, -2, 4 };
        int[] t12 = { 3, 5, -2, 2, -4 };
        int[] e1 = { 8 };
        Polynomial p11 = new Polynomial(t11);
        Polynomial p12 = new Polynomial(t12);
        Polynomial r1 = p11.add(p12);
        Polynomial p1e = new Polynomial(e1);
        assertTrue(polyEq(r1, p1e, null));
    }

    @Test
    public void testPolyAdd3() {
        int[] t21 = { -3, 9, -2, 0, 0, 4 };
        int[] t22 = { 5, -7, 0, 1, 0, 0, 5 };
        int[] e2 = { 2, 2, -2, 1, 0, 4, 5 };
        Polynomial p21 = new Polynomial(t21);
        Polynomial p22 = new Polynomial(t22);
        Polynomial r2 = p21.add(p22);
        Polynomial p2e = new Polynomial(e2);
        assertTrue(polyEq(r2, p2e, null));
    }

    @Test
    public void testPolyAdd4() {
        int[] t31 = { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12345 };
        int[] t32 = { -9, 1, 2, 3, 4, 5, 6 };
        int[] e3 = { -10, 1, 2, 3, 4, 5, 6, 0, 0, 0, 12345 };
        Polynomial p31 = new Polynomial(t31);
        Polynomial p32 = new Polynomial(t32);
        Polynomial r3 = p31.add(p32);
        Polynomial p3e = new Polynomial(e3);
        assertTrue(polyEq(r3, p3e, null));
    }

    @Test
    public void testPolyAdd5() {
        // Repeats testPolyAdd3 but ensures the
        // original polynomials aren't being affected
        int[] t1 = { -3, 9, -2, 0, 0, 4 };
        int[] t2 = { 5, -7, 0, 1, 0, 0, 5 };
        Polynomial p1 = new Polynomial(t1);
        Polynomial p1copy = new Polynomial(t1);
        Polynomial p2 = new Polynomial(t2);
        Polynomial p2copy = new Polynomial(t2);
        p1.add(p2);
        assertTrue(polyEq(p1, p1copy, null));
        assertTrue(polyEq(p2, p2copy, null));
    }
}
