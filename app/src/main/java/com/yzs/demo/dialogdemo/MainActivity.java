package com.yzs.demo.dialogdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.yzs.demo.dialogdemo.dialog.BottomDialog;

public class MainActivity extends AppCompatActivity {

    LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llRoot = findViewById(R.id.ll_root);
    }

    public void toastBottomDialog(View view) {
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle(R.string.dialog_title_str)
                .setMessage(R.string.dialog_message_str)
                .setCancelable(true)
                .setPositiveButton(R.string.btn_confirm_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.btn_cancel_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        recoveryAppAnim();
                    }
                })
                .create((ViewGroup) view.getParent());
        bottomDialog.show();
        showAppAnim(view);
    }

    private View rootView;
    public void showAppAnim(View view) {
        if (rootView == null)
            rootView = view.getRootView();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(rootView, "scaleY", 1, 0.9f);
        objectAnimatorY.setDuration(300);
        objectAnimatorY.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(rootView, "scaleX", 1, 0.9f);
        objectAnimatorX.setDuration(300);
        objectAnimatorX.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rootView, "rotationX", 0, 5, 0);
        objectAnimator.setDuration(600);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY, objectAnimator);
        animatorSet.start();
    }

    private void recoveryAppAnim() {
        if (rootView == null)
            return;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(rootView, "scaleY", 0.9f, 1f);
        objectAnimatorY.setDuration(300);
        objectAnimatorY.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(rootView, "scaleX", 0.9f, 1f);
        objectAnimatorX.setDuration(300);
        objectAnimatorX.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(rootView, "rotationX", 0, 5, 0);
        objectAnimator.setDuration(600);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY, objectAnimator);
        animatorSet.start();
    }
}
