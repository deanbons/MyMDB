package app.example.android.mymdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {
    @SerializedName("results")
    private ArrayList<Movie> moviesList;

    public MovieList(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }
}
