package com.trzewik.wordcloud;

import java.util.Arrays;

/**
 * @author Agnieszka Trzewik
 */
public enum FileType {
    NO_FILE("Select type", "", "Select type"),
    FILE("file://", "file://", "Please write full path to file: "),
    HTTP("http://", "http://", "Please write http address to file: "),
    HTTPS("https://", "https://", "Please write https address to file: "),
    TEXT("plain text", "", "Please paste text for check: ");

    private String stringRepresentation;
    private String contentForPath;
    private String infoForLabel;

    FileType(String stringRepresentation, String contentForPath, String infoForLabel) {
        this.stringRepresentation = stringRepresentation;
        this.contentForPath = contentForPath;
        this.infoForLabel = infoForLabel;
    }

    public static FileType findFileType(String stringRepresentation) {
        return Arrays.stream(values())
                .filter(s -> s.getStringRepresentation().equalsIgnoreCase(stringRepresentation))
                .findFirst()
                .orElse(NO_FILE);
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public String getContentForPath() {
        return contentForPath;
    }

    public String getInfoForLabel() {
        return infoForLabel;
    }

    boolean isPath() {
        return !this.equals(TEXT) && !this.equals(NO_FILE);
    }
}
