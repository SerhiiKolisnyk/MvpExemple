package com.kolisnyk.themoviedb.ui.popular;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.network.model.Movie;
import com.kolisnyk.themoviedb.di.qualifier.ApiInfo;
import com.kolisnyk.themoviedb.ui.base.BaseViewHolder;
import com.kolisnyk.themoviedb.ui.base.EmptyViewHolder;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularAdapter extends RecyclerView.Adapter<BaseViewHolder> implements EmptyViewHolder.RetryCallback{
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private static final String baseUr="https://image.tmdb.org/t/p/w200/";
    private List<Movie> movieList;
    private PopularCallback callback;
    public PopularAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public PopularAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                EmptyViewHolder emptyViewHolder=new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false));
                emptyViewHolder.setRetryListener(this, null);
                return emptyViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        if (movieList != null && movieList.size() > 0) {
            return movieList.size();
        } else {
            return 1;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (movieList != null && movieList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void retry() {
        if (callback !=null){
            callback.onRetry();
        }
    }

    public void setMovieList(@Nullable List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void setCallback(PopularCallback callback) {
        this.callback = callback;
    }



    public interface PopularCallback {
        void onClick(int id);
        void onRetry();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_movie)
        ImageView coverImageView;

        @BindView(R.id.txt_title)
        TextView titleTextView;

        @BindView(R.id.txt_release_date)
        TextView dateTextView;

        @BindView(R.id.txt_overView)
        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            contentTextView.setText("");
            dateTextView.setText("");
        }

        public void onBind(int position) {
            final Movie movie = movieList.get(position);
            Glide.with(itemView.getContext())
                    .load(baseUr + movie.getThumbPath())
                    .centerInside()
                    .into(coverImageView);

            titleTextView.setText(movie.getTitle());
            dateTextView.setText(movie.getReleaseDate());
            contentTextView.setText(movie.getOverview());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null)
                        callback.onClick(movie.getId());
                }

            });
        }
    }

}
