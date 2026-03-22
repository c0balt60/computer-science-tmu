package labs;

import java.util.*;

/*
 * SpySet<E> should be a child class of HashSet<E>.
 * SpySet keeps track of the number of times we attempt
 * to add a duplicate value. If we attempt to add a
 * duplicate value N times, remove all elements from
 * the SpySet.
 *
 * The value of N should be obtained as an argument to
 * the constructor.
 *
 * Note the type of the SpySet, and the HashSet it
 * extends, is E. This is what is called a generic type,
 * and can be thought of as a stand-in for any object
 * type in Java.
 *
 * For example, if we create a SpySet of Strings, then
 * in the code below, every occurrence of E would refer to
 * a String.
 *
 * To test your code, you can scroll down to the very bottom of this
 * file for instructions on how to run the unit tests.
 *
 * Note that your official lab grade will be based on the proportion
 * of unit tests that you pass.
 *
 */

public class SpySet<E> extends HashSet<E> {
    /*
     * Declare instance variables as needed. They should be private.
     */
    private int n;
    private int dupCount = 0;

    /*
     * Write a constructor for SpySet that has an int parameter.
     * The argument passed into the SpySet constructor is the
     * number of times we can attempt to add a duplicate before
     * the SpySet is cleared.
     */
    public SpySet(int N) {
        super();
        this.n = N;
    }

    /*
     * Override the add method inherited from HashSet.
     * This method adds an element to the set. It returns
     * true if the element was not already present, and
     * false if it was.
     *
     * If the number of duplicate adds reaches the limit,
     * clear the SpySet and reset the duplicate count to 0
     */
    @Override
    public boolean add(E elem) {
        // Check for duplicate
        boolean isDup = super.contains(elem);
        if (isDup && getDuplicateCount() + 1 >= getDuplicateLimit()) {
            clear();
            return false;
        }

        if (isDup) {
            dupCount++;
            return false;
        }

        // Add to set
        super.add(elem);
        return true;
    }

    /*
     * Override the clear() method inherited from HashSet.
     * The clear method should clear the set as usual, but
     * also reset the duplicate count to zero.
     */
    @Override
    public void clear() {
        // Clear hashset
        super.clear();
        // Reset count
        dupCount = 0;
    }

    /*
     * This method returns the current duplicate count
     */
    public int getDuplicateCount() {
        return dupCount;
    }

    /*
     * This method returns the duplicate limit
     */
    public int getDuplicateLimit() {
        return n;
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
     * javac -cp junit-1.11.0-M2.jar SpySet.java SpySetTest.java
     *
     * Run tests)
     * java -jar junit-1.11.0-M2.jar execute -cp . -c SpySetTest
     *
     * When all test report a pass, you're good to go.
     *
     */

}
