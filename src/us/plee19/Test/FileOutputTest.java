package us.plee19.Test;

import junit.framework.TestCase;
import org.junit.*;
import us.plee19.FileOutput;

import java.io.*;

public class FileOutputTest extends TestCase {
    FileOutput testOutput;
    public final ByteArrayOutputStream outputStuff = new ByteArrayOutputStream();
    public final PrintStream original = System.out;

    @Before
    public void setUp() throws Exception {
        testOutput = new FileOutput("testFileOutput.txt");
        testOutput.out = new BufferedWriter(new OutputStreamWriter(outputStuff));
        System.setOut(new PrintStream(outputStuff));
    }

    @After
    public void tearDown() throws Exception {
        testOutput.out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testFileOutput.txt")));
        System.setOut(original);
    }

    @Test
    public void testFileWrite() {
        testOutput.fileWrite("test");
        assertEquals("test\n",outputStuff.toString());
    }

    @Test
    public void testFileClose() {
        testOutput.fileClose();
        testOutput.fileWrite("Test closed");
        assertEquals("File Write Error: testFileOutput.txt java.io.IOException: Stream closed\n",testOutput.toString());
    }
}