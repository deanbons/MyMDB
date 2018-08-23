package app.example.android.mymdb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import app.example.android.mymdb.R;
import app.example.android.mymdb.adapter.MovieAdapter;
import app.example.android.mymdb.model.Movie;
import app.example.android.mymdb.model.MovieList;
import app.example.android.mymdb.network.NetUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {


    private Menu mMenu;
    @BindView(R.id.recycler_view)
    RecyclerView mItemGrid;
    private MovieAdapter mAdapter = new MovieAdapter(null, this);
    private char mSortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int span = getWidth() / 350;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,  span);

        mItemGrid.setLayoutManager(layoutManager);
        mItemGrid.setAdapter(mAdapter);

        loadHighestRatedMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_change_sort:
                if (mSortType == 'P') {
                    loadHighestRatedMovies();
                    MenuItem mItem = mMenu.findItem(R.id.action_change_sort);
                    mItem.setTitle(R.string.sort_p_btn);
                } else if (mSortType == 'R') {
                    loadMostPopularMovies();
                    MenuItem mItem = mMenu.findItem(R.id.action_change_sort);
                    mItem.setTitle(R.string.sort_r_btn);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMostPopularMovies(){
        mSortType = 'P';
        this.setTitle(getString(R.string.sort_p_title));
        Call<MovieList> call = new NetUtils().getMoviesByPopularity(1);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                generateMovieList(response.body().getMoviesList());
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.toast_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadHighestRatedMovies(){
        mSortType = 'R';
        this.setTitle(getString(R.string.sort_r_title));
        Call<MovieList> call = new NetUtils().getMoviesByRating(1);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                generateMovieList(response.body().getMoviesList());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.toast_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // method to generate List of movies using RecyclerView with custom adapter
    private void generateMovieList(ArrayList<Movie> movieDataList) {
        mAdapter = new MovieAdapter(movieDataList, this);
        mItemGrid.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Movie movieSelected) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movieSelected);
        startActivity(intent);
    }

    private int getWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
