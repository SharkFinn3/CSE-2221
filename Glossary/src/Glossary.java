import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Glossary program, organizing words and their definitions.
 *
 * @author Shafin Alam
 */
public final class Glossary {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Glossary() {
        // no code needed here
    }

    /**
     * This method should create and display the index page of the words.
     *
     * @param file
     *            the index page being written to
     * @param terms
     *            the queue of words / terms
     */
    private static void outputIndexPage(SimpleWriter file,
            Queue<String> terms) {
        //this should make the header and titles for the index page
        file.println("<html><head><title>Sample Glossary</title></head><body>");
        //it should be titled Glossary
        file.println("<h2>Glossary</h2>");
        file.println("<hr>");
        //it should then create an index with all the terms
        file.println("<h3>Index</h3><ul>");
        for (int i = 0; i < terms.length(); i++) {
            String term = terms.front();
            file.println("<li><a href=" + term + ".html>" + term + "</a></li>");
            terms.rotate(1);
        }
        //this should make the footer for the index page
        file.println("</ul>" + "</body>" + "</html>");
    }

    /**
     * This method should display the new page of the word with its definition.
     *
     * @param word
     *            a term without its definition
     * @param definition
     *            the definition of a word
     * @param out
     *            the file that gets written out to
     * @param words
     *            the queue of terms/ words
     */
    private static void wordPage(Queue<String> words, String word,
            String definition, SimpleWriter out) {
        //this creates a new variable with a value of definition,
        //but one that we can change if needed
        String definition2 = definition;
        //it should then display the word in red big font
        out.println("<html><head><title>" + word + "</title></head>");
        out.println("<h2><b2><i><font color = \"red\">" + word + "</font>");
        out.println("</i></b></h2>");
        int i = 0;
        //if any of the terms are found in any of the definitions,
        //it should hyperlink to their respective page with their definition
        while (i < words.length()) {
            String term = words.front();
            if (definition.contains(term)) {
                definition2 = definition.replaceAll(term,
                        "<a href=" + term + ".html>" + term + "</a>");
            }
            words.rotate(1);
            i++;
        }
        //it should then print the definitions under the terms
        out.println("<blockquote>" + definition2 + "</blockquote><hr>");
        //it should then allow the user to return to the main index page
        out.println("<p>Return to <a href=\"index.html\">index</a>.</p>"
                + "</body>" + "</html>");
    }

    /**
     * This method should read the words from the input file and place them into
     * a queue.
     *
     * @param in
     *            user input file
     * @return names (a queue of the words displayed , w/o definitions)
     */
    public static Queue<String> terms(SimpleReader in) {
        //this should create a new queue for all of the terms
        Queue<String> names = new Queue1L<>();
        //it should then read the entire file
        while (!in.atEOS()) {
            //if there is a term, it should have no spaces, and not be empty
            String word = in.nextLine();
            if ((!word.contains(" ")) && (!word.isEmpty())) {
                //it should add all of the terms it finds into the queue created
                names.enqueue(word);
            }
        }
        //it should return the queue of terms
        return names;
    }

    /**
     * This method should read the definitions from the input file and place
     * them into a queue.
     *
     * @param in
     *
     * @return definition (a queue of the meanings of the words displayed)
     */
    public static Queue<String> definitions(SimpleReader in) {
        //this will create an empty queue, that,
        //later, will receive all of the definitions
        Queue<String> definition = new Queue1L<>();
        String line = "";
        String meaning = "";
        //it should read the entire file, until it reaches the end
        while (!in.atEOS()) {
            //this skips the line with the term / word
            in.nextLine();
            //this saves the definitions as the second line
            meaning = in.nextLine();
            boolean bool = true;
            //if there are any lines that come after the first definition line
            //, and they aren't blank spaces, or terms,
            //they should be added to the definition
            while (bool) {
                line = in.nextLine();
                if (!line.contains(" ")) {
                    bool = false;
                } else {
                    meaning += line;

                }

            }
            //the definition should be added to the queue
            if (meaning.contains(" ")) {
                definition.enqueue(meaning);
            }
        }
        //the queue of definitions should get returned
        return definition;
    }

    /**
     * Comparator used to order the words queue.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            //should compare string1 to string2 based off of alphabetic order
            return s1.compareTo(s2);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //The user should be prompted for a file name
        out.print("Enter an input file: ");
        String inputFileName = in.nextLine();
        String inputFileName2 = inputFileName;
        //the user should be prompted for an output folder
        out.print("Enter the name of an output folder: ");
        String folder = in.nextLine();
        SimpleReader file = new SimpleReader1L(inputFileName);
        SimpleReader file2 = new SimpleReader1L(inputFileName2);
        //the index is printed to a file called as index.html
        SimpleWriter index = new SimpleWriter1L(folder + "index.html");
        //this should call a method that puts all of the terms into a queue
        Queue<String> terms = terms(file);
        Queue<String> terms2 = new Queue1L<>();
        for (int i = 0; i < terms.length(); i++) {
            String front = terms.front();
            terms2.enqueue(front);
            terms.rotate(1);
        }
        //this should call a file that puts all of the definitions into a queue
        Queue<String> definition = definitions(file2);
        //this should create a new comparator
        Comparator<String> alphabetic = new StringLT();
        int i = 0;
        //this will make all of the individual pages
        //for all of the terms and their definitions
        while (i < terms.length()) {
            String word = terms.front();
            String meaning = definition.front();
            Queue<String> temp = new Queue1L<>();
            temp.enqueue(word);
            SimpleWriter newHTML = new SimpleWriter1L(word + ".html");
            wordPage(terms2, word, meaning, newHTML);
            terms.rotate(1);
            definition.rotate(1);
            i++;
        }
        //the terms should then be sorted and displayed on the index page
        terms.sort(alphabetic);
        outputIndexPage(index, terms);
        //make sure to close all of the files, so no resource leak
        in.close();
        out.close();
        file.close();
        file2.close();
    }

}
