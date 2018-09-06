package us.plee19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    private final static FileInput movieRating = new FileInput("movie_rating.csv");
    private final static FileOutput outFile = new FileOutput("output_file.csv");
    private final static FileOutput ratingsSum = new FileOutput("ratings_sum.txt");
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        String[] fields;
        int[] nums = new int[2];
        int[] ratings = new int[2];
        int sum = 0;
        System.out.format("%8s  %-18s %6s %6s %10s\n","Account","Name", "Movies", "Points", "Avg Rating");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            findAvgRating(fields[0], ratings);

            // Set up file for input of above data
            System.out.printf("00%6s  %-18s  %2d    %4d  %8.2f\n", fields[0], fields[1], nums[0], nums[1], (float) ratings[1] / ratings[0]);
            String outputString = String.format("00%s,%s,%d,%d,%.2f", fields[0], fields[1], nums[0], nums[1], (float) ratings[1] / ratings[0]);

            outFile.fileWrite(outputString);
        }
        outFile.fileClose();

        ArrayList<Integer> ratingsArray = new ArrayList<>(buildRatingsArray());
        Collections.sort(ratingsArray);
        int oneCount = Collections.frequency(ratingsArray, 1);
        int twoCount = Collections.frequency(ratingsArray, 2);
        int threeCount = Collections.frequency(ratingsArray, 3);
        int fourCount = Collections.frequency(ratingsArray, 4);
        int fiveCount = Collections.frequency(ratingsArray, 5);
        String results1 = "Rating Count";
        String results2 = 1 + "      " + oneCount;
        String results3 = 2 + "      " + twoCount;
        String results4 = 3 + "      " + threeCount;
        String results5 = 4 + "      " + fourCount;
        String results6 = 5 + "      " + fiveCount;
        ratingsSum.fileWrite(results1);
        ratingsSum.fileWrite(results2);
        ratingsSum.fileWrite(results3);
        ratingsSum.fileWrite(results4);
        ratingsSum.fileWrite(results5);
        ratingsSum.fileWrite(results6);

        ratingsSum.fileClose();


        // Open file for input and print a line for each movie rating, with count/total

    }

    public static void findPurchases(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while (((line = cardPurchases.fileReadLine()) != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += parseInt(fields[2]);
            }

        }
    }

    public static void findAvgRating(String acct, int[] ratings) {
        String line;
        String[] fields;
        ratings[0] = 0;
        ratings[1] = 0;
        boolean done = false;
        while (((line = movieRating.fileReadLine()) != null && !(done))) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                ratings[0]++;
                ratings[1] += parseInt(fields[1]);
            }
        }
    }

    public static ArrayList<Integer> buildRatingsArray() {
        String line;
        String[] fields;
        boolean done = false;
        ArrayList<Integer> ratingsList = new ArrayList<>();
        while (((line = movieRating.fileReadLine()) != null && !(done))) {
            fields = line.split(",");
            ratingsList.add(parseInt(fields[1]));
        }
        return ratingsList;
    }
}