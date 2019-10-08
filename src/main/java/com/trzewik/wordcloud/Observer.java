package com.trzewik.wordcloud;

/**
 * @author Agnieszka Trzewik
 */
interface Observer {

    void updateText(boolean isPath, String content) throws WrongPathException;


    int calculateQuantityOfWord(String searchWord);
}
