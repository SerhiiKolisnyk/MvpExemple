package com.kolisnyk.themoviedb.ui.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.data.network.model.MovieListResponse;
import com.kolisnyk.themoviedb.di.component.ActivityComponent;
import com.kolisnyk.themoviedb.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PopularFragment extends BaseFragment implements PopularMvpView {

    public static final String TAG = "PopularFragment";

    @Inject
    PopularMvpPresenter<PopularMvpView, PopularMvpInteractor> mPresenter;

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
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mPresenter.onViewPrepared();
    }

    @Override
    public void updateList(MovieListResponse movieListResponse) {
        Toast.makeText(getContext(), "DONE !!! ", Toast.LENGTH_SHORT).show();
    }
}
