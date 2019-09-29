package com.kolisnyk.themoviedb.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.kolisnyk.themoviedb.di.component.ActivityComponent;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpView {

    private ProgressDialog mProgressDialog;
    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

//    @Override
//    public void showLoading() {
//        hideLoading();
//        //mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
//    }
//
//    @Override
//    public void hideLoading() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.cancel();
//        }
//    }
//
//    @Override
//    public void onError(String message) {
//        if (mActivity != null) {
//            mActivity.onError(message);
//        }
//    }
//
//    @Override
//    public void onError(@StringRes int resId) {
//        if (mActivity != null) {
//            mActivity.onError(resId);
//        }
//    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

//    @Override
//    public void showMessage(@StringRes int resId) {
//        if (mActivity != null) {
//            mActivity.showMessage(resId);
//        }
//    }
//
//    @Override
//    public boolean isNetworkConnected() {
//        if (mActivity != null) {
//            return mActivity.isNetworkConnected();
//        }
//        return false;
//    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

//    @Override
//    public void hideKeyboard() {
//        if (mActivity != null) {
//            mActivity.hideKeyboard();
//        }
//    }
//
//    @Override
//    public void openActivityOnTokenExpire() {
//        if (mActivity != null) {
//            mActivity.openActivityOnTokenExpire();
//        }
//    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
