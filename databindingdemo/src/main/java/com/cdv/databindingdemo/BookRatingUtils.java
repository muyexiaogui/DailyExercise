package com.cdv.databindingdemo;

public class BookRatingUtils {
    public static String getBookRating(int rating){
        switch (rating){
            case 1:
                return "1星";
            default:
                return "0星";
        }
    }
}
