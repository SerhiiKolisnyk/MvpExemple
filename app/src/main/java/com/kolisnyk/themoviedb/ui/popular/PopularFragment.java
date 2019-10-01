package com.kolisnyk.themoviedb.ui.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.network.model.Movie;
import com.kolisnyk.themoviedb.di.component.ActivityComponent;
import com.kolisnyk.themoviedb.ui.base.BaseFragment;
import com.kolisnyk.themoviedb.ui.detail.DetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularFragment extends BaseFragment implements PopularMvpView, PopularAdapter.PopularCallback {

    public static final String TAG = "PopularFragment";

    @Inject
    PopularMvpPresenter<PopularMvpView, PopularMvpInteractor> mPresenter;

    @BindView(R.id.popular_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    PopularAdapter popularAdapter;

    public static PopularFragment newInstance() {
        Bundle args = new Bundle();
        PopularFragment fragment = new PopularFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            popularAdapter.setCallback(this);
        }
        return view;
    }


    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(popularAdapter);
        mPresenter.onViewPrepared();
    }

    @Override
    public void updateList(@Nullable List<Movie> movieList) {
        popularAdapter.setMovieList(movieList);
    }



    @Override
    public void onClick(int id) {
        mPresenter.onDrawerDetailClick(id);
    }


    @Override
    public void onRetry() {
        mPresenter.onViewPrepared();
    }

    @Override
    public void showDetailFragment(int idOfFilm) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.fragment_container, DetailFragment.newInstance(idOfFilm), DetailFragment.TAG)
                .commit();
    }

}
