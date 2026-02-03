package labs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolynomialTestOne {

    @Test(timeout = 5000)
    public void testPolynomial1() {
        // Explicit test cases
        int[] c1 = { 5, 0, -2, 3 };
        Polynomial p1 = new Polynomial(c1);
        assertEquals(3, p1.getDegree());
        assertEquals(-2, p1.getCoefficient(2));
        c1[3] = 5555;
        assertEquals(3, p1.getCoefficient(3)); // still 3, not 5555
    }

    @Test(timeout = 5000)
    public void testPolynomial2() {
        // Another explicit polynomial.
        int[] c2 = { 42, 99, -3, -10, -4 };
        Polynomial p2 = new Polynomial(c2);
        assertEquals(4, p2.getDegree());
        assertEquals(99, p2.getCoefficient(1));
        assertEquals(-4, p2.getCoefficient(4));
        assertEquals(0, p2.getCoefficient(5));
        assertEquals(0, p2.getCoefficient(12345));
    }

    @Test(timeout = 5000)
    public void testPolynomial3() {
        int[] c3 = { 99, 0, 0, 0, 0, 0, 0, 0 };
        Polynomial p3 = new Polynomial(c3);
        assertEquals(0, p3.getDegree());
        assertEquals(99, p3.getCoefficient(0));
    }

    @Test(timeout = 5000)
    public void testPolynomial4() {
        int[] c4 = { 0, 0, 0, 0 };
        Polynomial p4 = new Polynomial(c4);
        assertEquals(0, p4.getDegree());
        assertEquals(0, p4.getCoefficient(0));
        assertEquals(0, p4.getCoefficient(1000));
    }

    @Test(timeout = 5000)
    public void testPolynomial5() {
        int[] c5 = { 1, 0, 1, 0, 1 };
        Polynomial p5 = new Polynomial(c5);
        assertEquals(4, p5.getDegree());
        assertEquals(1, p5.getCoefficient(0));
        assertEquals(0, p5.getCoefficient(1));
        assertEquals(1, p5.getCoefficient(2));
        assertEquals(0, p5.getCoefficient(3));
        assertEquals(1, p5.getCoefficient(4));
    }

    @Test(timeout = 5000)
    public void testPolynomial6() {
        int[] c6 = { -1, -2, -3, -4, -5, -6 };
        Polynomial p6 = new Polynomial(c6);
        assertEquals(5, p6.getDegree());
        assertEquals(-1, p6.getCoefficient(0));
        assertEquals(-2, p6.getCoefficient(1));
        assertEquals(-3, p6.getCoefficient(2));
        assertEquals(-4, p6.getCoefficient(3));
        assertEquals(-5, p6.getCoefficient(4));
        assertEquals(-6, p6.getCoefficient(5));
    }
}
