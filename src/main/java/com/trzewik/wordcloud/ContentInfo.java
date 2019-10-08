package com.trzewik.wordcloud;

/**
 * @author Agnieszka Trzewik
 */
public class ContentInfo {

    private boolean isPath;
    private String content;

    public boolean isPath() {
        return isPath;
    }

    public void setPath(boolean path) {
        isPath = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentInfo{" +
                "isPath=" + isPath +
                ", content='" + content + '\'' +
                '}';
    }
}
