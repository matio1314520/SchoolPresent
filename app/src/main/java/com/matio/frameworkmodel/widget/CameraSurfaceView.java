package com.matio.frameworkmodel.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Angel on 2016/3/18.
 * 1.添加调用摄像头的权限
 */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Camera mCamera;

    public CameraSurfaceView(Context context) {
        super(context);

        init();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {

        //获取holder 并设置回调接口
        getHolder().addCallback(this);
    }

    public Camera getmCamera() {

        return mCamera;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //开启摄像头,0为后置摄像头
        mCamera = Camera.open(0);

        try {
            //将摄像头捕获的画面通过holder对象绘制到SurfaceView中
            mCamera.setPreviewDisplay(holder);

            //默认捕获画面倒着
            //将画面旋转90度
            mCamera.setDisplayOrientation(90);

            //开始预览（打开摄像头，开始捕获画面）
            mCamera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        //结束预览
        mCamera.stopPreview();

        mCamera.release();
    }
}
