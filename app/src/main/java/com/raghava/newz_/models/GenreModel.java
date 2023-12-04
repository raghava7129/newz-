package com.raghava.newz_.models;

public class GenreModel {
    private String GenreName;
    private String GenrePic;

    public GenreModel(String genreName, String genrePic) {
        GenreName = genreName;
        GenrePic = genrePic;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }

    public String getGenrePicUrl() {
        return GenrePic;
    }

    public void setGenrePic(String genrePic) {
        GenrePic = genrePic;
    }
}
