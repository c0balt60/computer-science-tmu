package labs;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.zip.CRC32;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LabTwoTest {

    private static final int SEED = 12345;
    private static final int TRIALS = 10000;
    private static final int ROUNDS = 1000;

    @Test(timeout = 5000)
    public void testEveryOther1() {
        int[] a1 = { 42, 99, 17, 33 };
        int[] b1 = { 42, 17 };
        assertArrayEquals(b1, LabTwo.everyOther(a1));
    }

    @Test(timeout = 5000)
    public void testEveryOther2() {
        int[] a2 = { 42, 99, 17 };
        int[] b2 = { 42, 17 };
        assertArrayEquals(b2, LabTwo.everyOther(a2));
    }

    @Test(timeout = 5000)
    public void testEveryOther3() {
        int[] a3 = { 99 };
        int[] b3 = { 99 };
        assertArrayEquals(b3, LabTwo.everyOther(a3));
    }

    @Test(timeout = 5000)
    public void testEveryOther4() {
        int[] a4 = { 11, 22, 33, 44, 55, 66, 77, 88, 99 };
        int[] b4 = { 11, 33, 55, 77, 99 };
        assertArrayEquals(b4, LabTwo.everyOther(a4));
    }

    @Test(timeout = 5000)
    public void testEveryOther5() {
        int[] a4 = { 11, 22, 33, 44, 55, 66, 77, 88, 99 };
        int[] a4_2 = { 11, 22, 33, 44, 55, 66, 77, 88, 99 };
        int[] b4 = { 11, 33, 55, 77, 99 };
        assertArrayEquals(b4, LabTwo.everyOther(a4));
        assertArrayEquals(a4, a4_2);
    }

    @Test(timeout = 5000)
    public void testEveryOtherMass() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for (int i = 0; i < TRIALS; i++) {
            int len = rng.nextInt(1000);
            int[] a = new int[len];
            for (int j = 0; j < len; j++) {
                a[j] = rng.nextInt(100000);
            }
            int[] b = LabTwo.everyOther(a);
            check.update(b.length);
            for (int e : b) {
                check.update(e);
            }
        }
        assertEquals(3861208241L, check.getValue());
    }

    @Test(timeout = 5000)
    public void testCreateZigZag1() {
        // Explicit test cases
        int[][] e1 = { { 4, 5, 6, 7 }, { 11, 10, 9, 8 }, { 12, 13, 14, 15 } };
        int[][] r1 = LabTwo.createZigZag(3, 4, 4);
        assertEquals(Arrays.deepToString(e1), Arrays.deepToString(r1));
    }

    @Test(timeout = 5000)
    public void testCreateZigZag2() {
        int[][] e2 = { { 1, 2 }, { 4, 3 }, { 5, 6 }, { 8, 7 }, { 9, 10 } };
        int[][] r2 = LabTwo.createZigZag(5, 2, 1);
        assertEquals(Arrays.deepToString(e2), Arrays.deepToString(r2));
    }

    @Test(timeout = 5000)
    public void testCreateZigZag3() {
        int[][] e3 = { { 42 }, { 43 }, { 44 }, { 45 } };
        int[][] r3 = LabTwo.createZigZag(4, 1, 42);
        assertEquals(Arrays.deepToString(e3), Arrays.deepToString(r3));
    }

    @Test(timeout = 5000)
    public void testCreateZigZag4() {
        int[][] e4 = { { 77, 78, 79, 80, 81, 82 } };
        int[][] r4 = LabTwo.createZigZag(1, 6, 77);
        assertEquals(Arrays.deepToString(e4), Arrays.deepToString(r4));
    }

    @Test(timeout = 5000)
    public void testCreateZigZag5() {
        int[][] e5 = { { 42 } };
        int[][] r5 = LabTwo.createZigZag(1, 1, 42);
        assertEquals(Arrays.deepToString(e5), Arrays.deepToString(r5));
    }

    @Test(timeout = 5000)
    public void testCreateZigZagMass() {
        // Pseudorandom fuzz tester
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for (int i = 0; i < TRIALS; i++) {
            int rows = rng.nextInt(20) + 1;
            int cols = rng.nextInt(20) + 1;
            int start = rng.nextInt(100);
            int[][] zig = LabTwo.createZigZag(rows, cols, start);
            assertEquals(rows, zig.length);
            for (int j = 0; j < rows; j++) {
                assertEquals(cols, zig[j].length);
                for (int e : zig[j]) {
                    check.update(e);
                }
            }
        }
        assertEquals(3465650385L, check.getValue());
    }

    @Test(timeout = 5000)
    public void testReverseAscendingSubarrays1() {
        int[] a1 = { 42, 99, 17, 0 };
        int[] b1 = { 99, 42, 17, 0 };
        LabTwo.reverseAscendingSubarrays(a1);
        assertArrayEquals(b1, a1);
    }

    @Test(timeout = 5000)
    public void testReverseAscendingSubarrays2() {
        int[] a2 = { 4, 18, 99, 67, 71, 72, 100, 42, 66 };
        int[] b2 = { 99, 18, 4, 100, 72, 71, 67, 66, 42 };
        LabTwo.reverseAscendingSubarrays(a2);
        assertArrayEquals(b2, a2);
    }

    @Test(timeout = 5000)
    public void testReverseAscendingSubarrays3() {
        int[] a3 = { -1000, 33, 999999999 };
        int[] b3 = { 999999999, 33, -1000 };
        LabTwo.reverseAscendingSubarrays(a3);
        assertArrayEquals(b3, a3);
    }

    @Test(timeout = 5000)
    public void testReverseAscendingSubarrays4() {
        int[] a4 = { 53, -99, -75, -16, -3, -18, -39, 42, 8 };
        int[] b4 = { 53, -3, -16, -75, -99, -18, 42, -39, 8 };
        LabTwo.reverseAscendingSubarrays(a4);
        assertArrayEquals(b4, a4);
    }

    @Test(timeout = 5000)
    public void testReverseAscendingSubarraysMass() {
        // Pseudorandom fuzz tester
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for (int i = 0; i < ROUNDS; i++) {
            int len = rng.nextInt(1000);
            int[] a = new int[len];
            for (int j = 0; j < len; j++) {
                a[j] = rng.nextInt(1000000);
            }
            LabTwo.reverseAscendingSubarrays(a);
            for (int j = 0; j < len; j++) {
                check.update(a[j]);
            }
        }
        assertEquals(3118921076L, check.getValue());
    }

    @Test(timeout = 5000)
    public void testPancakeScramble1() {
        assertEquals("", LabTwo.pancakeScramble(""));
    }

    @Test(timeout = 5000)
    public void testPancakeScramble2() {
        assertEquals("alu", LabTwo.pancakeScramble("lua"));
    }

    @Test(timeout = 5000)
    public void testPancakeScramble3() {
        assertEquals("heya", LabTwo.pancakeScramble("yeah"));
    }

    @Test(timeout = 5000)
    public void testPancakeScramble4() {
        assertEquals("eoeawsm", LabTwo.pancakeScramble("awesome"));
    }

    @Test(timeout = 5000)
    public void testPancakeScramble5() {
        assertEquals("enisrtpocmue cec", LabTwo.pancakeScramble("computer science"));
    }

}
