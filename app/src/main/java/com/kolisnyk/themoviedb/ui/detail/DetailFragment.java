package com.kolisnyk.themoviedb.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.network.model.MovieDetailResponse;
import com.kolisnyk.themoviedb.di.component.ActivityComponent;
import com.kolisnyk.themoviedb.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kolisnyk.themoviedb.data.network.NetworkService.BASE_URL_IMG;

public class DetailFragment extends BaseFragment implements DetailMvpView {
    public static final String TAG = "DetailFragment";
    private static final String KEY_ID_FILM = "KEY_ID_FILM";

    @Inject
    DetailMvpPresenter<DetailMvpView, DetailMvpInteractor> mPresenter;

    @BindView(R.id.liner_content)
    LinearLayout linearLayoutContent;

    @BindView(R.id.empty_view)
    View emptyView;

    @BindView(R.id.img_poster)
    ImageView imgPoster;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.txt_revenue)
    TextView txtRevenue;
    @BindView(R.id.txt_adult)
    TextView txtAdult;

    @BindView(R.id.txt_release_date)
    TextView txtReleaseDate;
    @BindView(R.id.txt_rate)
    TextView txtRate;


    public static DetailFragment newInstance(int idOfFilm) {
        Bundle args = new Bundle();
        args.putInt(KEY_ID_FILM, idOfFilm);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        getBaseActivity().getSupportActionBar().hide();
        ;
        return view;
    }

    @Override
    protected void setUp(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.getInt(KEY_ID_FILM, 0);
            mPresenter.onViewPrepared(bundle.getInt(KEY_ID_FILM, 0));

        }
    }

    @Override
    public void updateInfo(@Nullable MovieDetailResponse movieDetailResponse) {
        if (movieDetailResponse == null) {
            emptyView.setVisibility(View.VISIBLE);
            linearLayoutContent.setVisibility(View.GONE);
            return;
        }
        emptyView.setVisibility(View.GONE);
        linearLayoutContent.setVisibility(View.VISIBLE);
        Glide.with(getContext())
                .load(BASE_URL_IMG + movieDetailResponse.getThumbPath())
                .centerInside()
                .into(imgPoster);
        txtTitle.setText(movieDetailResponse.getTitle());
        txtStatus.setText(getString(R.string.status, movieDetailResponse.getStatus()));
        txtRevenue.setText(getString(R.string.revenue, movieDetailResponse.getRevenue()));
        txtRate.setText(String.valueOf(movieDetailResponse.getRating()));
        txtReleaseDate.setText(movieDetailResponse.getReleaseDate());
        if (movieDetailResponse.isAdult()) txtAdult.setVisibility(View.VISIBLE);
        else txtAdult.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.btn_retry)
    void onRetryClick() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.getInt(KEY_ID_FILM, 0);
            mPresenter.onViewPrepared(bundle.getInt(KEY_ID_FILM, 0));
        }
    }

    @Override
    public void onDetach() {
        getBaseActivity().getSupportActionBar().show();
        ;
        super.onDetach();

    }
}
