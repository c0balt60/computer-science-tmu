package labs;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LabOneTest {

    @Test(timeout = 5000)
    public void testFallingPower1() {
        assertEquals(720, LabOne.fallingPower(10, 3));
    }

    @Test(timeout = 5000)
    public void testFallingPower2() {
        assertEquals(5040, LabOne.fallingPower(7, 7));
    }

    @Test(timeout = 5000)
    public void testFallingPower3() {
        assertEquals(75030638981760L, LabOne.fallingPower(99, 7));
    }

    @Test(timeout = 5000)
    public void testFallingPower4() {
        assertEquals(3024, LabOne.fallingPower(-6, 4));
    }

    @Test(timeout = 5000)
    public void testFallingPower5() {
        assertEquals(0, LabOne.fallingPower(3, 6));
    }

    @Test(timeout = 5000)
    public void testFallingPower6() {
        assertEquals(1, LabOne.fallingPower(42, 0));
    }

    @Test(timeout = 5000)
    public void testFallingPower7() {
        assertEquals(1, LabOne.fallingPower(-98765432, 0));
    }

    @Test(timeout = 5000)
    public void testFallingPower8() {
        assertEquals(-990, LabOne.fallingPower(-9, 3));
    }

    @Test
    public void testCountInversions1() {
        int[] a1 = { 42, 17, 99, 5 };
        assertEquals(4, LabOne.countInversions(a1));
    }

    @Test
    public void testCountInversions2() {
        int[] a2 = { 999 };
        assertEquals(0, LabOne.countInversions(a2));
    }

    @Test
    public void testCountInversions3() {
        int[] a3 = { 77, 44, 33, 22, 0, -11, -12345678 };
        assertEquals(21, LabOne.countInversions(a3));
    }

    @Test
    public void testCountInversions4() {
        int[] a4 = { -12345678, -11, 0, 22, 33, 44, 77 };
        assertEquals(0, LabOne.countInversions(a4));
    }

    @Test
    public void testCountInversions5() {
        int[] a5 = { 3, 5, 1, 7, 0, 9 };
        assertEquals(6, LabOne.countInversions(a5));
    }
}
