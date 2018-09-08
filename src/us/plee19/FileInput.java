package us.plee19;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Creates class to handle file input methods.
 * @author plee19
 * @version 1
 */
public class FileInput {

    private BufferedReader in = null;
    private String fileName;

    /**
     * FileInput constructor creates new instance of FileInput with a given fileName while checking to ensure the file exists.
     * @param fileName The requested file name for the FileInput instance
     */
    public FileInput(String fileName) {
        this.fileName = fileName;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " " + e);
        }
    }

    /**
     * Prints each line of the file, while catching any file write errors in the process.
     * @return void
     */
    public void fileRead() {
        String line;
        try {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("File Write Error: " + fileName + " " + e);
        }
    }

    /**
     * Returns the next line of the file, while catching any file write errors in the process.
     * @return String of a single line of the file
     */
    public String fileReadLine() {
        try {
            String line = in.readLine();
            return line;
        } catch (Exception e) {
            System.out.println("File Write Error: " + fileName + " " + e);
            return null;
        }
    }

    /**
     * Closes the file, while catching any IOException errors in the process.
     * @return void
     */
    public void fileClose() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}