package app.example.android.mymdb.network;

import android.util.Log;

import app.example.android.mymdb.model.MovieList;
import retrofit2.Call;

public class NetUtils {

    private final String apiKey = "";

    public Call<MovieList> getMoviesByRating(int pageNumber) {
        GetMovieDataService service = RetrofitInstance
                .getRetrofitInstance()
                .create(GetMovieDataService.class);
        Call<MovieList> call = service.getHighestRatedMoviesListData(pageNumber, apiKey);
        Log.wtf("URL Called", call.request().url() + "");
        return call;
    }

    public Call<MovieList> getMoviesByPopularity(int pageNumber) {
        GetMovieDataService service = RetrofitInstance
                .getRetrofitInstance()
                .create(GetMovieDataService.class);
        Call<MovieList> call = service.getMostPopularMoviesListData(pageNumber, apiKey);
        Log.wtf("URL Called", call.request().url() + "");
        return call;
    }

}
