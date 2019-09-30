package com.kolisnyk.themoviedb.ui.favor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.ui.base.BaseViewHolder;
import com.kolisnyk.themoviedb.ui.base.ErrorViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kolisnyk.themoviedb.data.network.NetworkService.BASE_URL_IMG;

public class FavorAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ErrorViewHolder.RetryCallback{
    private static final int VIEW_TYPE_EROR = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_EMPTY = 2;
    private List<MovieDetail> movieList;
    private static final String baseUr=BASE_URL_IMG;
    private FavorCallback callback;

    public FavorAdapter(List<MovieDetail> movieList) {
        this.movieList = movieList;
    }

    public FavorAdapter() {
        movieList=new ArrayList<>(1);
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
            case VIEW_TYPE_EMPTY:

                 return new EmptyHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false));

            case VIEW_TYPE_EROR:
            default:
                ErrorViewHolder errorViewHolder =new ErrorViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false));
                errorViewHolder.setRetryListener(this, null);
                return errorViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (movieList != null &&movieList.size()!=0) {
            return movieList.size();
        } else {
            return 1;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(movieList==null)return VIEW_TYPE_EROR;
        else if (movieList.size()==0)return VIEW_TYPE_EMPTY;
        else return VIEW_TYPE_NORMAL;
    }

    @Override
    public void retry() {
        if (callback !=null){
            callback.onRetry();
        }
    }
    public interface FavorCallback {
        void onClick(int id);
        void onRetry();
    }

    public void setMovieList(List<MovieDetail> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void setCallback(FavorCallback callback) {
        this.callback = callback;
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
            final MovieDetail movie = movieList.get(position);
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
    public class EmptyHolder extends BaseViewHolder {
        public EmptyHolder(View itemView) {
            super(itemView);
        }

        protected void clear() {
        }

        public void onBind(int position) {
        }
    }

}
