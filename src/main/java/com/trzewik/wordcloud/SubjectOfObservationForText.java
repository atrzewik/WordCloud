package com.trzewik.wordcloud;

/**
 * @author Agnieszka Trzewik
 */
interface SubjectOfObservationForText {

    void notifyAboutText(Observer observer, ContentInfo contentInfo) throws WrongPathException;
}
