package com.kolisnyk.themoviedb.ui.base;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kolisnyk.themoviedb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyViewHolder  extends BaseViewHolder{
    @BindView(R.id.btn_retry)
    Button retryButton;

    @BindView(R.id.txt_msg)
    TextView messageTextView;

     private RetryCallback retryListener;
    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    protected void clear() {
    }

    public void setRetryListener(RetryCallback retryListener, @Nullable String msg) {
        this.retryListener = retryListener;
        if (msg!=null)
            messageTextView.setText(msg);
    }

    @OnClick(R.id.btn_retry)
    void onRetryClick() {
        if (retryListener != null)
            retryListener.retry();
    }

    public interface RetryCallback {
        void retry();
    }
}
