package com.trzewik.wordcloud;

/**
 * @author Agnieszka Trzewik
 */
class ContentInfo {

    private boolean isPath;
    private String content;

    boolean isPath() {
        return isPath;
    }

    void setPath(boolean path) {
        isPath = path;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
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
