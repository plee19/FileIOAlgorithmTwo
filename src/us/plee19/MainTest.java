package us.plee19;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static us.plee19.Main.buildRatingsArray;

public class MainTest extends TestCase {
    private FileInput cardAccts;
    private FileInput cardPurchases;
    private FileInput movieRating;
    private FileOutput outFile;
    private FileOutput ratingsSum;

    @Before
    public void setUp() throws Exception {
        cardAccts = new FileInput("movie_cards.csv");
        cardPurchases = new FileInput("movie_purchases.csv");
        movieRating = new FileInput("movie_rating.csv");
        outFile = new FileOutput("output_file.csv");
        ratingsSum = new FileOutput("ratings_sum.txt");
    }

    @After
    public void tearDown() throws Exception {
        cardAccts.fileClose();
        cardPurchases.fileClose();
        movieRating.fileClose();
        outFile.fileClose();
        ratingsSum.fileClose();
    }
//WIP
    @Test
    public void testFindPurchases() {
    }
//WIP
    @Test
    public void testFindAvgRating() {
    }

    @Test
    public void testBuildRatingsArray() {
        ArrayList<Integer> buildTest = new ArrayList<>();
        buildTest = buildRatingsArray();
        assertTrue(buildTest.size() == 147);
    }
}