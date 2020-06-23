package javac.coursework.glossary;

import javac.coursework.glossary.neuralnet.NeuralNet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Company class
 *
 * (c) Yaroslav Kasperovych
 * MIT License
 */

public class Glossary {
    /**
     * Sorted TreeSet for distinct words. It will sort entries by their frequencies in descending order
     */
    private TreeSet<Map.Entry<String, Integer>> distinctWords = new TreeSet<>((e1, e2) -> {
        return e2.getValue().compareTo(e1.getValue());
    });

    /**
     * Sorted TreeSet for neural network found names. They will be sorted alphabetically by default
     */
    private TreeSet<String> names = new TreeSet<>();

    /**
     * Neural network for name recognition
     */
    private NeuralNet neuralNet = new NeuralNet();

    public Glossary() {
        try {
            Scanner scanner = new Scanner(new File("/home/casper/IdeaProjects/coursework/src/main/java/javac/coursework/harry.txt"));
            // LinkedHashMap for unique unsorted words
            LinkedHashMap<String, Integer> uniqueUnsortedWords = new LinkedHashMap<>();

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Skip line if it contains no characters
                if (line.length() == 0) {
                    continue;
                }

                String[] words = line.split("\\W+");


                for (String word : words) {
                    // Get current word frequency stored in LinkedHashMap
                    int wordFrequency = uniqueUnsortedWords.getOrDefault(word, 0);
                    // Increment a frequency of the word
                    uniqueUnsortedWords.put(word, wordFrequency + 1);
                }

                // Find names with neural network and add it to TreeSet (doesn't need to be sorted)
                this.names.addAll(neuralNet.findNames(line));
            }

            scanner.close();

            // Add all found unique unsorted words and their frequencies to a sorted TreeSet
            this.distinctWords.addAll(uniqueUnsortedWords.entrySet());


            Iterator namesIterator = this.names.iterator();

            // Write 20 or less names to test.txt
            try {
                FileWriter fileWriter = new FileWriter("test.txt");
                fileWriter.write("");

                for(int i = 0; i < this.names.size() && i < 20; i++) {
                    fileWriter.append(namesIterator.next().toString() + "\n");
                }

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Distinct words getter
     *
     * @return distinct words
     */
    public TreeSet<Map.Entry<String, Integer>> getDistinctWords() {
        return distinctWords;
    }

    /**
     * Neural network found names getter
     *
     * @return found names
     */
    public TreeSet<String> getNames() {
        return names;
    }
}
