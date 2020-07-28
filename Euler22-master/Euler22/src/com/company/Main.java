package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String line = "";
        try {
            URL url = new URL("https://projecteuler.net/project/resources/p022_names.txt");
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
        } catch (IOException ex) {
            // there was some connection problem, or the file did not exist on the server,
            // or your URL was not in the right format.
            // think about what to do now, and put it here.
            ex.printStackTrace(); // for now, simply output it.
        }
        System.out.println("Main: " + nameScore(line));
    }
//method should return int

    public static double nameScore(String line) {

//  ====================================================================================================================
//holds final value
        double result = 0;
//stores the name and score value
        Map<String, Integer> nameListScore = new HashMap<String, Integer>();
//score of each letter
        Map<String, Integer> alphabet = new HashMap<String, Integer>();
        alphabet.put("A", 1);
        alphabet.put("B", 2);
        alphabet.put("C", 3);
        alphabet.put("D", 4);
        alphabet.put("E", 5);
        alphabet.put("F", 6);
        alphabet.put("G", 7);
        alphabet.put("H", 8);
        alphabet.put("I", 9);
        alphabet.put("J", 10);
        alphabet.put("K", 11);
        alphabet.put("L", 12);
        alphabet.put("M", 13);
        alphabet.put("N", 14);
        alphabet.put("O", 15);
        alphabet.put("P", 16);
        alphabet.put("Q", 17);
        alphabet.put("R", 18);
        alphabet.put("S", 19);
        alphabet.put("T", 20);
        alphabet.put("U", 21);
        alphabet.put("V", 22);
        alphabet.put("W", 23);
        alphabet.put("X", 24);
        alphabet.put("Y", 25);
        alphabet.put("Z", 26);

//  ====================================================================================================================

//removes "" from names
        String modded = "";
        modded = line.replaceAll("\"", "");

        String[] parts = modded.split(",");
        Arrays.sort(parts);

//holds the total scores of each name
        int[] letterScore = new int[6_000];
//this calls each name
        for (int i = 0; i < parts.length; i++) {
//this displays each letter of the name
            for (int j = 0; j < parts[i].length(); j++) {
//combines the values of each letter in the word
                letterScore[i] += (alphabet.get(parts[i].substring(j, j + 1)));
//slaps the name and combined score into the hashMap
                nameListScore.put(parts[i], (letterScore[i] * (i + 1)));
            }
//add up all values
            result += (nameListScore.get(parts[i]));
        }
        return result;
    }
}
