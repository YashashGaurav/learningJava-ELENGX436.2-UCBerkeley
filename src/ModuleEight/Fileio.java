package ModuleEight;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.FileNotFoundException;

public class Fileio {
    // Example: ModuleEight.Fileio C:\path\to\\sampleFile.txt -5
    public static void main(String[] args) {

        String filePath = args[0];
        int toPrintLines = Integer.parseInt(args[1]);

        try {
            // If number of lines are to display is positive
            if (toPrintLines > 0) {
                printFileLinesInRange(filePath, 1, toPrintLines);
            }
            // If number of lines are to display is zero
            else if (toPrintLines == 0) {
                printFileLinesInRange(filePath, 1, getNumberOfFileLines(filePath));
            }
            // If number of lines are to display is negative
            else {
                printFileLinesInRange(filePath, getNumberOfFileLines(filePath) - Math.abs(toPrintLines) + 1,
                        getNumberOfFileLines(filePath));
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("### File not found. ###");
        } catch (IOException ioe) {
            System.out.println("### File IO exception thrown. ###");
        }
    }

    /**
     * Prints lines in file between interval
     * 
     * @param filePath        String path to the file
     * @param beginLineNumber Line to start printing from (included)
     * @param endLineNumber   Line to print till (Included)
     * @throws IOException
     */
    private static void printFileLinesInRange(String filePath, int beginLineNumber, int endLineNumber)
            throws IOException {

        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))) {
            lineNumberReader.setLineNumber(beginLineNumber);

            while (lineNumberReader.getLineNumber() <= endLineNumber) {
                System.out.println(lineNumberReader.getLineNumber() + " : " + lineNumberReader.readLine());
            }
        }
    }

    /**
     * Get the total number of lines in a document
     * 
     * @param filePath String path of the document
     * @return Interger number of lines in a document
     * @throws IOException
     */
    private static int getNumberOfFileLines(String filePath) throws IOException {
        int noOfFileLines;

        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))) {
            lineNumberReader.skip(Integer.MAX_VALUE);
            noOfFileLines = lineNumberReader.getLineNumber() + 1;
        }

        return noOfFileLines;
    }
}