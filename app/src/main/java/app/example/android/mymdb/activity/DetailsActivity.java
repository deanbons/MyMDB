package app.example.android.mymdb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.example.android.mymdb.R;
import app.example.android.mymdb.model.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Math.round;



public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_title)
    TextView mTitleTV;
    @BindView(R.id.iv_detail_poster)
    ImageView mPosterIV;
    @BindView(R.id.tv_detail_release_date)
    TextView mReleaseDateTV;
    @BindView(R.id.tv_detail_overview)
    TextView mOverviewTV;
    @BindView(R.id.tv_detail_votes)
    TextView mVoteTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent parentIntent = getIntent();
        Movie movie = parentIntent.getParcelableExtra("movie");

        String screenType = getScreenType();
        loadScaledContent(movie, screenType);

    }

    private String getScreenType() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int halfWidth = displayMetrics.widthPixels/2;
        if (halfWidth < 185) {
            return "XS";
        } else if (halfWidth < 342) {
            return "S";
        } else if (halfWidth < 500) {
            return "M";
        } else if (halfWidth < 780) {
            return "L";
        } else {
            return "XL";
        }
    }

    private void loadScaledContent(Movie movie, String screenType) {
        String basePath = "http://image.tmdb.org/t/p";
        int imgHeight;
        switch (screenType) {
            case "XS":
                // load xs content
                loadText(movie);
                imgHeight = (int) round(154 * 1.5);
                loadImage(basePath + "/w154" + movie.getPosterPath());
                scaleText(imgHeight);
                break;
            case "S":
                // load s content
                loadText(movie);
                imgHeight = (int) round(185 * 1.5);
                loadImage(basePath + "/w185" + movie.getPosterPath());
                scaleText(imgHeight);
                break;
            case "M":
                // load m content
                loadText(movie);
                imgHeight = (int) round(343 * 1.5);
                loadImage(basePath + "/w343" + movie.getPosterPath());
                scaleText(imgHeight);
                break;
            case "L":
                // load l content
                loadText(movie);
                imgHeight = (int) round(500 * 1.5);
                loadImage(basePath + "/w500" + movie.getPosterPath());
                scaleText(imgHeight);
                break;
            case "XL":
                // load xl content
                loadText(movie);
                imgHeight = (int) round(780 * 1.5);
                loadImage(basePath + "/w780" + movie.getPosterPath());
                scaleText(imgHeight);
                break;
            default:
                break;
        }
    }

    private void loadText(Movie movie) {
        mTitleTV.setText(movie.getTitle());
        mReleaseDateTV.setText( movie.getReleaseDate());
        mOverviewTV.setText(movie.getOverview());
        String voteDisplay = Float.toString(movie.getVoteAverage()) + "\n(" + Integer.toString(movie.getVoteCount()) + " votes)";
        mVoteTV.setText(voteDisplay);
    }


    private void loadImage(String path){
        Picasso.with(this).load(path).into(mPosterIV);
    }

    private void scaleText(int imgHeight) {
        // scale mTitleTV
        mTitleTV.setTextSize(imgHeight/20);
        // scale mReleaseDateTV
        mReleaseDateTV.setHeight(imgHeight/2);
        mReleaseDateTV.setTextSize(imgHeight/25);
        // scale mVotesTV
        mVoteTV.setHeight(imgHeight/2);
        mVoteTV.setTextSize(imgHeight/30);
        // scale mOverviewTV
        mOverviewTV.setTextSize(imgHeight/50);
    }


}
