package com.example.dasha_000.shopping.WebParsing;

/**
 * Created by dasha_000 on 28.05.2018.
 */

class ParseHelper {
    private String flink;
    private int category;
    private int pages_count;

    public ParseHelper(String flink, int category, int pages_count){
        this.setFlink(flink);
        this.setCategory(category);
        this.setPages_count(pages_count);
    }

    public ParseHelper() {}

    public int getPages_count() {
        return pages_count;
    }

    private void setPages_count(int pages_count) {
        this.pages_count = pages_count;
    }

    public int getCategory() {

        return category;
    }

    private void setCategory(int category) {
        this.category = category;
    }

    public String getFlink() {

        return flink;
    }

    private void setFlink(String flink) {
        this.flink = flink;
    }
}
