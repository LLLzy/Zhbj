package com.peizheng.lzy.zhbj;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.peizheng.lzy.zhbj.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.ALPHA;
import static android.view.View.ROTATION;
import static android.view.View.SCALE_X;
import static android.view.View.SCALE_Y;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startAnim();

    }

    private void startAnim() {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(rlRoot, ROTATION, 0, 360).setDuration(1000);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rlRoot, SCALE_X, 0, 1).setDuration(1000);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rlRoot, SCALE_Y, 0, 1).setDuration(1000);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rlRoot, ALPHA, 0, 1).setDuration(1000);

        animatorSet.playTogether(rotateAnimator, scaleXAnimator, scaleYAnimator, alphaAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                jumpNextPage();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    private void jumpNextPage() {
        boolean userGuideShowed = PrefUtils.getBoolean(this, "is_user_guide_showed", false);

        if (userGuideShowed) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));

        }
        finish();
    }
}


