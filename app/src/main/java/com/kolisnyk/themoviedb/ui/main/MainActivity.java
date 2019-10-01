package com.kolisnyk.themoviedb.ui.main;

import android.os.Bundle;


import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.kolisnyk.themoviedb.R;
import com.kolisnyk.themoviedb.ui.base.BaseActivity;
import com.kolisnyk.themoviedb.ui.detail.DetailFragment;
import com.kolisnyk.themoviedb.ui.favor.FavorFragment;
import com.kolisnyk.themoviedb.ui.popular.PopularFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {
    private final static String TAG="MainActivity";

    @Inject
    MainMvpPresenter<MainMvpView, MainMvpInteractor> mPresenter;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setUp();
      //  showPopularFragmentFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        mPresenter.onViewInitialized();
    }
    void setupNavMenu() {
        ( (NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.nav_gallery:
                            showPopularFragmentFragment();
                            return true;
                        case R.id.nav_slideshow:
                            showFavorFragment();
                            return true;
                        default:
                            return false;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(DetailFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(DetailFragment.TAG);
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.LEFT);
        }
    }


    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void showGalleryFragment() {

    }

    @Override
    public void showPopularFragmentFragment() {
        lockDrawer();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.fragment_container, PopularFragment.newInstance(), PopularFragment.TAG)
                .addToBackStack(null)
                .commit();
        unlockDrawer();
    }

    @Override
    public void showFavorFragment() {
        lockDrawer();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.fragment_container, FavorFragment.newInstance(), FavorFragment.TAG)
                .addToBackStack(null)
                .commit();
        unlockDrawer();
    }
}
