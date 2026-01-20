package labs;

import java.util.Arrays;

public class LabOne {

    public static int findIndex(int arr[], int index) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == index)
                return i;
        }
        return -1;
    }

    /*
     * Fill in the methods below according to the descriptions given.
     *
     * You should test your code by calling your methods in the main
     * method provided. One sample call for each method is given,
     * but you should write your own test cases in main() and try them
     * out as you go.
     *
     * Once you're satisfied that your methods are producing the correct
     * results, you can scroll down to the very bottom of this file
     * for instructions on how to run the unit tests.
     *
     * Note that your official lab grade will be based on the unit tests
     * and not what you wrote in main(), but it's still a good idea
     * to practice writing your own main() method. You won't always be
     * given unit tests.
     */

    public static void main(String[] args) {

        System.out.println(fallingPower(4, 3));

        // int[] arr = { 2, 4, 1, 3, 5 };
        int[] arr = { -12345678, -11, 0, 22, 33, 44, 77 };
        System.out.println(countInversions(arr));
    }

    /*
     * PROBLEM 1)
     *
     * Python has the integer exponentiation operator ** conveniently built
     * in the language, whereas Java unfortunately does not offer that.
     *
     * Instead of integer exponentiation, however, you will implement the
     * related operation of "falling power". Each term multiplied into the
     * product is always one less than the previous term.
     *
     * For example, the fallingPower(8, 3) is computed as 8 × 7 × 6 = 336.
     * Similarly, the fallingPower(10, 5) equals 10 × 9 × 8 × 7 × 6 = 30240.
     *
     * Nothing essential changes in this calculation when the base is negative;
     * the fallingPower(–4, 5) is computed as –4 × –5 × –6 × –7 × –8 = –6720.
     * Similar to normal exponentiation, fallingPower(n, 0) = 1 for any nonzero
     * integer n.
     *
     * This method should return the fallingPower(n, k) where the base n can be
     * any integer, and the exponent k can be any integer greater than zero.
     *
     * Hint: notice that the return type is long... you should probably declare
     * and store your running result similarly, as a long int.
     */

    public static long fallingPower(int n, int k) {
        if (k == 0)
            return 1;

        long result = n;

        for (int i = 1; i < k; i++)
            result *= n - i;

        return result;
    }

    /*
     * PROBLEM 2)
     *
     * Inside an array arr, an inversion is a pair of two positions i<j
     * such that arr[i]>arr[j]. In combinatorics, the inversion count inside
     * an array is a rough measure of how “out of order” that array is.
     *
     * If an array is sorted in ascending order, it has zero inversions,
     * whereas an n-element array sorted in reverse order has n(n–1)/2
     * inversions, the largest number possible.
     *
     * This method should count the inversions inside the given array arr,
     * and return that count. As always when writing methods that operate on
     * arrays, make sure that your method works correctly for arrays of any
     * length, including the important special cases arrays with length zero
     * and one.
     */

    public static int countInversions(int[] arr) {
        int inversions = 0;
        var clone = arr.clone();

        while (clone.length > 1) {
            var lowest = Arrays.stream(clone).min().getAsInt();
            // Each lowest needs to be moved down its index amount
            inversions += findIndex(clone, lowest);
            // Remove index
            clone = Arrays.stream(clone)
                    .filter(e -> e != lowest)
                    .toArray();
        }

        return inversions;
    }

    /*
     * RUNNING THE UNIT TESTS)
     *
     * Evaluate your code by using the following commands in the terminal.
     * Any time you make changes to your code, you will have to run
     * both commands again. The first one compiles your code and the
     * tester into two Java class files. The second command runs the
     * unit tests and displays the results.
     *
     * Compile)
     * javac -cp junit-1.11.0-M2.jar LabOne.java LabOneTest.java
     *
     * Run tests)
     * java -jar junit-1.11.0-M2.jar execute -cp . -c LabOneTest
     *
     * When all test report a pass, you're good to go.
     *
     */

}
