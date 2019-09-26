package com.trzewik.TomcatWordCloud;

/**
 * @author Agnieszka Trzewik
 */
public interface Observer {

    void updateText(boolean isPath, String content) throws WrongPathException;


    int calculateQuantityOfWord(String searchWord);
}
