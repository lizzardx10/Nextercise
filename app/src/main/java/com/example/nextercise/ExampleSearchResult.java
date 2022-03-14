package com.example.nextercise;

import com.parse.ParseFile;

public class ExampleSearchResult {
private ParseFile ivSearchImage;
private String tvSearchTitle;
private String tvSearchDesc;

    public ExampleSearchResult(ParseFile searchImage, String searchTitle, String searchDesc){
        ivSearchImage = searchImage;
        tvSearchTitle = searchTitle;
        tvSearchDesc = searchDesc;
    }
    public ParseFile getIvSearchImage() {
        return ivSearchImage;
    }
    public String getTvSearchTitle() {
        return tvSearchTitle;
    }
    public String getTvSearchDesc() {
        return tvSearchDesc;
    }
}
