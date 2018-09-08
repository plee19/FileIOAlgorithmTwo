package us.plee19;

import java.io.*;

/**
 * Creates class to handle file output methods.
 * @author plee19
 * @version 1
 */
public class FileOutput {

    Writer out = null;
    private String fileName;

    /**
     * FileOutput constructor creates new instance of FileOutput with a given fileName while checking to ensure the file exists.
     * @param fileName name of the file to be output to
     */
    public FileOutput(String fileName) {
        this.fileName = fileName;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " "  + e);
        }
    }

    /**
     * Writes a given String line to the file, while checking for file write errors in the process.
     * @param line String line to be written to the file
     */
    public void fileWrite(String line) {
        try {
            out.write(line+"\n");
        }
        catch(Exception e) {
            System.out.println("File Write Error: " + fileName + " "  + e);
        }
    }

    /**
     * Closes the file, while catching any IOException errors in the process.
     */
    public void fileClose() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}