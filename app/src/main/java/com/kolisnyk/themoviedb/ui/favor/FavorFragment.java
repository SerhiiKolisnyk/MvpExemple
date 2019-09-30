package com.kolisnyk.themoviedb.ui.favor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.db.model.MovieDetail;
import com.kolisnyk.themoviedb.di.component.ActivityComponent;
import com.kolisnyk.themoviedb.ui.base.BaseFragment;
import com.kolisnyk.themoviedb.ui.detail.DetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavorFragment extends BaseFragment implements FavorMvpView, FavorAdapter.FavorCallback {
    public static final String TAG = "FavorFragment";
    @Inject
    FavorMvpPresenter<FavorMvpView, FavorMvpInteractor> mPresenter;
    @BindView(R.id.favor_recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    FavorAdapter favorAdapter;
    public static FavorFragment newInstance() {
        Bundle args = new Bundle();
        FavorFragment fragment = new FavorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favor, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            favorAdapter.setCallback(this);

        }
        return view;
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

    @Override
    public void onResume() {
        mPresenter.onViewPrepared();
        super.onResume();
    }

    @Override
    public void updateList(@Nullable List<MovieDetail> movies) {
        favorAdapter.setMovieList(movies);
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(favorAdapter);
        mPresenter.onViewPrepared();
    }
}
