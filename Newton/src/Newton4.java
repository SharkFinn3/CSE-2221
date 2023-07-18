import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Repeatedly asks the user whether they wish to calculate another square root.
 *
 * @author Shafin Alam
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * final int for the amount of iterations.
     */
    public static final int SEQUENCE = 10;

    /**
     * computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square of
     * @param epsilon
     *            accuracy
     * @return estimate of square root
     */

    private static double sqrt(double x, double epsilon) {
        int r = 1;
        double squareRoot = 1;
        if (x == 0) {
            squareRoot = 0;
        } else {
            while (r == 1) {
                squareRoot = (squareRoot + x / squareRoot) / 2;
                if (Math.abs(Math.sqrt(x) - squareRoot)
                        / Math.sqrt(x) <= epsilon) {
                    r++;
                }
            }
        }
        return squareRoot;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.print("Enter a new value for x: ");
        double input = in.nextDouble();
        while (input >= 0) {
            out.print("Enter a degree of accuracy: ");
            double accuracy = in.nextDouble();
            out.println(sqrt(input, accuracy));
            out.print("Enter a new value for x: ");
            input = in.nextDouble();
        }
        out.print("Bye!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
