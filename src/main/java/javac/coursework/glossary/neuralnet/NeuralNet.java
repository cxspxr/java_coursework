package javac.coursework.glossary.neuralnet;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Neural network for named-entity recognition
 *
 * (c) Yaroslav Kasperovych
 * MIT License
 */

public class NeuralNet {
    /**
     * Named-entity recognition person's name finder
     */
    private NameFinderME nameFinder;

    /**
     * Named-entity recognition tokenizer
     */
    private TokenizerME tokenizer;

    public NeuralNet() {
        try {
            // Teach how to recognize names
            InputStream inputStreamNER = new FileInputStream("/home/casper/IdeaProjects/coursework/src/main/java/javac/coursework/glossary/neuralnet/en-ner-person.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNER);
            this.nameFinder = new NameFinderME(model);

            // Create tokenizer model
            InputStream inputStreamTokens = new FileInputStream("/home/casper/IdeaProjects/coursework/src/main/java/javac/coursework/glossary/neuralnet/en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(inputStreamTokens);
            this.tokenizer = new TokenizerME(tokenModel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find name in a sentence
     *
     * @param sentence - names to be found in
     * @return names found in a sentence
     */
    public ArrayList<String> findNames(String sentence) {
        String[] tokens = tokenizer.tokenize(sentence);
        Span[] nameSpans = nameFinder.find(tokens);

        ArrayList<String> names = new ArrayList<>();

        for (Span nameSpan : nameSpans) {
            names.add(tokens[nameSpan.getStart()]);
        }

        return names;
    }
}
