package app.example.android.mymdb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.example.android.mymdb.R;
import app.example.android.mymdb.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final ArrayList<Movie> dataList;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movieSelected);
    }

    public MovieAdapter(ArrayList<Movie> dataList, MovieAdapterOnClickHandler handler) {
        this.dataList = dataList;
        this.mClickHandler = handler;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.grid_layout_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        String path = "http://image.tmdb.org/t/p/w342" + dataList.get(i).getPosterPath();
        Picasso.with(viewHolder.itemView.getContext()).load(path).into(viewHolder.mGridItemIV);
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        } else {
            return dataList.size();
        }
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mGridItemIV;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mGridItemIV = itemView.findViewById(R.id.grid_item_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie m = dataList.get(getAdapterPosition());
            mClickHandler.onClick(m);
        }
    }

}
