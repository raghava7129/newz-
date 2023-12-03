package com.raghava.newz_.models;

public class GenreModel {
    private String GenreName;

    public GenreModel(String genreName, String genrePic) {
        GenreName = genreName;
        GenrePic = genrePic;
    }

    private String GenrePic;

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
