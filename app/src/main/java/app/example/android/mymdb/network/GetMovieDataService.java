package app.example.android.mymdb.network;

import app.example.android.mymdb.model.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface GetMovieDataService {
    @GET("/3/movie/popular")
    Call<MovieList> getMostPopularMoviesListData(
            @Query("page") int pageNumber,
            @Query("api_key") String apiKey);

    @GET("/3/movie/top_rated")
    Call<MovieList> getHighestRatedMoviesListData(
            @Query("page") int pageNumber,
            @Query("api_key") String apiKey);
}






