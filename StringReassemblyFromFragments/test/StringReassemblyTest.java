import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Shafin Alam
 */

public class StringReassemblyTest {
    /*
     * Tests of combination
     */
    /**
     * tests 2 strings and if they concatenate properly w 1 overlap (Boundary).
     */
    @Test
    public void testCombinationBoundary() {
        String str1 = "Spiderman";
        String str2 = "nightCrawler";
        String combination = "";
        String realCombination = "SpidermanightCrawler";
        int overlap = 1;
        combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(realCombination, combination);
    }

    /**
     * tests 2 strings and if they concatenate properly (Routine).
     */
    @Test
    public void testCombinationBobcatCatInTheHat() {
        String str1 = "bobcat";
        String str2 = "catInTheHat";
        String combination = "";
        String realCombination = "bobcatInTheHat";
        int overlap = 3;
        combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(realCombination, combination);
    }

    /**
     * tests 2 strings, w one a complete substring of the other, to check
     * concatenation(Challenging).
     */
    @Test
    public void testCombinationLongerOverlap() {
        String str1 = "TATACAT";
        String str2 = "TATACATGCCT";
        String combination = "";
        String realCombination = "TATACATGCCT";
        int overlap = 7;
        combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(realCombination, combination);
    }

    /*
     * Tests for addToSetAvoidingSubstrings
     */
    /**
     * tests addToSetAvoidingSubstrings for repeats of small string(Boundary).
     */
    @Test
    public void testaddToSetAvoidingSubstringsBoundary() {
        Set<String> strings = new Set1L<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        String str = "def";
        StringReassembly.addToSetAvoidingSubstrings(strings, str);
        Set<String> actual = new Set1L<>();
        actual.add("a");
        actual.add("b");
        actual.add("c");
        actual.add("def");
        assertEquals(actual, strings);
    }

    /**
     * tests addToSetAvoidingSubstrings for repeats of random string(Routine).
     */
    @Test
    public void testaddToSetAvoidingSubstringsRoutine() {
        Set<String> strings = new Set1L<>();
        strings.add("ab");
        strings.add("cd");
        strings.add("ef");
        String str = "abdef";
        StringReassembly.addToSetAvoidingSubstrings(strings, str);
        Set<String> actual = new Set1L<>();
        actual.add("abdef");
        actual.add("cd");
        assertEquals(actual, strings);
    }

    /**
     * tests addToSetAvoidingSubstrings for repeats of strings already
     * present(challenging).
     */
    @Test
    public void testaddToSetAvoidingSubstringsSpidermanChallenging() {
        Set<String> strings = new Set1L<>();
        strings.add("The");
        strings.add("Amazing");
        strings.add("Spider");
        strings.add("Man");
        String str = "SpidersScareMeMan";
        StringReassembly.addToSetAvoidingSubstrings(strings, str);
        Set<String> actual = new Set1L<>();
        actual.add("The");
        actual.add("Amazing");
        actual.add("SpidersScareMeMan");
        assertEquals(actual, strings);
    }

    /*
     * Test cases for linesFromInput
     */
    /**
     * tests 2 words from an input file to see if a set is made(Boundary).
     */
    @Test
    public void testlinesFromInputBoundary() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("Hello");
        out.println("World");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));

        Set<String> actual = new Set1L<>();
        actual.add("Hello");
        actual.add("World");
        in.close();
        assertEquals(actual, test);
    }

    /**
     * tests 2 words from an input file to see if a set is made(Routine).
     */
    @Test
    public void testlinesFromInputRoutine() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("Hello");
        out.println("World!");
        out.println("My");
        out.println("name is");
        out.println("Shafin :)");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));
        Set<String> actual = new Set1L<>();
        actual.add("Hello");
        actual.add("World!");
        actual.add("My");
        actual.add("name is");
        actual.add("Shafin :)");
        in.close();
        assertEquals(actual, test);
    }

    /**
     * tests Star Wars script for linesFromInput(Challenging).
     */
    @Test
    public void testlinesFromInputStarWarsChallenging() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        out.println("It's over Anakin, I have the highground!");
        out.println("You underestimate my power!");
        out.println("YOU WERE THE CHOSEN ONE! ");
        out.println(
                "It was said that you would destroy the Sith, not join them,");
        out.println(" bring balance to the force, not leave it in darkness!");
        out.close();
        Set<String> test = new Set1L<>();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        test.add(StringReassembly.linesFromInput(in));

        Set<String> actual = new Set1L<>();
        actual.add("It's over Anakin, I have the highground!");
        actual.add("You underestimate my power!");
        actual.add("YOU WERE THE CHOSEN ONE! ");
        actual.add(
                "It was said that you would destroy the Sith, not join them,");
        actual.add(" bring balance to the force, not leave it in darkness!");
        in.close();
        assertEquals(actual, test);
    }

    /*
     * Tests for printWithLineSeparators
     */
    /**
     * tests line separation of a little string(Boundary).
     */
    @Test
    public void testPrintWithLineSeparatorsBoundary() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        StringReassembly.printWithLineSeparators("~1", out);
        out.close();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String actual = "";
        String actual2 = "1";
        in.close();
        assertEquals(actual, line1);
        assertEquals(actual2, line2);

    }

    /**
     * tests line separation of an example given in class(Routine).
     */
    @Test
    public void testPrintWithLineSeparatorsCSE2221() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        StringReassembly.printWithLineSeparators("CSE~2221", out);
        out.close();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String actual = "CSE";
        String actual2 = "2221";
        in.close();
        assertEquals(actual, line1);
        assertEquals(actual2, line2);

    }

    /**
     * tests line separation of a complex string(Challenging).
     */
    @Test
    public void testPrintWithLineSeparatorsChallenging() {
        SimpleWriter out = new SimpleWriter1L("testcase1.txt");
        StringReassembly.printWithLineSeparators("~~~Hello~~World", out);
        out.close();
        SimpleReader in = new SimpleReader1L("testcase1.txt");
        in.nextLine();
        in.nextLine();
        in.nextLine();
        String line4 = in.nextLine();
        in.nextLine();
        String line6 = in.nextLine();
        String actual = "Hello";
        String actual2 = "World";
        in.close();
        assertEquals(actual, line4);
        assertEquals(actual2, line6);

    }
}
