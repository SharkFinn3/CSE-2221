import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {
    /**
     * @param length
     *            equals minimum password length
     */
    public static final int LENGTH = 8;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        if (s.length() <= LENGTH) {
            out.println("Password must be 8 characters!");
        }
        if (!containsUpperCaseLetter(s)) {
            out.println("Password must contain at least upper case letter!");
        }
        if (!containsLowerCaseLetter(s)) {
            out.println(
                    "Password must contain at least one lower case letter!");
        }
        if (!containsDigit(s)) {
            out.println("Password requires at least one digit!");
        } else {
            out.println("Strong Password!");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean uppercase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                uppercase = true;
            }
        }
        return uppercase;
    }

    /**
     * checks if the given string contains a lower case letter.
     *
     * @param s
     *            the string to check
     * @return true if s contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean lowercase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                lowercase = true;
            }
        }
        return lowercase;
    }

    /**
     * checks if the given string contains a digit.
     *
     * @param s
     *            the string to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigit(String s) {
        boolean digit = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digit = true;
            }
        }
        return digit;
    }

    private static int count(String s) {

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
        out.print("Enter a password: ");
        String password = in.nextLine();
        checkPassword(password, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
