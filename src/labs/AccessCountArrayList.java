package labs;

import java.util.ArrayList;

public class AccessCountArrayList<E> extends ArrayList<E> {

    /*
     * This subclass should maintain one data field int count to keep
     * track of how many times the methods get and set have been called.
     * One counter keeps the simultaneous count for both of these methods
     * together. You should override the inherited get and set methods so
     * that both of these methods first increment the access counter, and
     * only then call the superclass version of that same method,
     * returning whatever result that superclass version returned.
     *
     * In addition to these overridden methods inherited from the superclass,
     * your class should define the two new methods seen below: getAccessCount()
     * and resetCount().
     *
     * To test your code, you can scroll down to the very bottom of this
     * file for instructions on how to run the unit tests.
     *
     * Note that your official lab grade will be based on the proportion
     * of unit tests that you pass.
     *
     */

    private int count;

    public AccessCountArrayList() {
        super();
    }

    @Override
    public E get(int idx) {
        count++;
        return super.get(idx);
    }

    @Override
    public E set(int idx, E value) {
        count++;
        return super.set(idx, value);
    }

    public int getAccessCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
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
     * javac -cp junit-1.11.0-M2.jar AccessCountArrayList.java
     * AccessCountArrayListTest.java
     *
     * Run tests)
     * java -jar junit-1.11.0-M2.jar execute -cp . -c AccessCountArrayListTest
     *
     * When all test report a pass, you're good to go.
     *
     */

}
