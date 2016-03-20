package com.matio.frameworkmodel.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.widget.ImageView;

import com.matio.frameworkmodel.R;

/**
 * Created by Angel on 2016/3/19.
 */
public class AnimatorUtils {


    public static void startAnimator(Context context) {

        int[] res = {R.mipmap.loading_01, R.mipmap.loading_02, R.mipmap.loading_03, R.mipmap.loading_04, R.mipmap.loading_05};

        for (int i = 0; i < 5; i++) {

            ImageView imageView = new ImageView(context);

            imageView.setImageResource(res[i]);

            Animator animator = AnimatorInflater.loadAnimator(context, R.anim.test);

            animator.setTarget(imageView);

            animator.start();
        }
    }

    public static void cancelAnimator(Context context) {


    }
}
