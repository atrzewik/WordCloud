package com.trzewik.wordcloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Agnieszka Trzewik
 */
public class WordCloud implements Observer {

    private boolean isPath;

    private String content;

    private List<String> text;

    private static List<String> parseSentencesToWords(List<String> sentences) {
        List<String> words = new ArrayList<>();
        for (String sentence : sentences) {
            words.addAll(Arrays.asList(sentence.split("\\W")));
        }
        return words;
    }

    @Override
    public void updateText(boolean isPath, String content) throws WrongPathException {
        this.isPath = isPath;
        this.content = content;
        updateText();
    }

    @Override
    public int calculateQuantityOfWord(String searchWord) {

        if (text == null) return 0;

        int count = 0;
        for (String word : text) {
            if (searchWord.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    private void updateText() throws WrongPathException {
        if (isPath) {
            try {
                URL url = new URL(content);

                try (BufferedReader inputFromPath = new BufferedReader(new InputStreamReader(url.openStream()))) {

                    List<String> sentences = inputFromPath.lines().collect(Collectors.toList());

                    text = parseSentencesToWords(sentences);

                }
            } catch (IOException | IllegalArgumentException e) {
                throw new WrongPathException("Path doesn't exist");
            }
        } else text = parseSentencesToWords(Collections.singletonList(content));
    }
}
