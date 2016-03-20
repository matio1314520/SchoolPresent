package com.matio.frameworkmodel.activity;

import android.hardware.Camera;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.matio.frameworkmodel.R;
import com.matio.frameworkmodel.base.BaseActivity;
import com.matio.frameworkmodel.widget.CameraSurfaceView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Angel on 2016/3/18.
 */
@ContentView(R.layout.activity_capture)
public class CaptureActivity extends BaseActivity {

    @ViewInject(R.id.scan_activity_capture)
    private ImageView mScanImg;

    @ViewInject(R.id.camera_activity_capture)
    private CameraSurfaceView mCameraSurView;

    @Override
    public void onOperate() {

        //设置扫描动画
        setCustomAnimation();
    }

    /**
     * 设置扫描动画
     */
    private void setCustomAnimation() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0f, 1.0f);

        scaleAnimation.setDuration(1500);  //单次时间

        scaleAnimation.setRepeatCount(-1); // 无限循环

        scaleAnimation.setRepeatMode(Animation.RESTART);  //从新开始

        scaleAnimation.setInterpolator(new LinearInterpolator());

        mScanImg.startAnimation(scaleAnimation);  //开启动画
    }

    @Event({R.id.back_activity_capture, R.id.camera_activity_capture})
    private void onClick(View view) {

        switch (view.getId()) {

            case R.id.back_activity_capture:
                finish();
                break;

            case R.id.camera_activity_capture:

                Camera camera = mCameraSurView.getmCamera();

                if (camera != null) {

                    //
                    camera.takePicture(null, null, new Camera.PictureCallback() {

                        @Override
                        public void onPictureTaken(byte[] data, Camera camera) {

                            Toast.makeText(CaptureActivity.this, new String(data, 0, data.length), Toast.LENGTH_LONG).show();
                        }
                    });
                }

                break;
        }
    }

    /**
     * 1.surfaceView的使用
     * 2.实现SurfaceHolder.Callback接口
     * 3.重写其中的方法
     * 4.获取holder对象(就是后台的绘图助手)
     * 5.将2中回调借口配置到holder对象中，用于接收holder对象提交的画面
     */
//    class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
//
//        private boolean mIsDraw;
//
//        //绘图助手，可以工作在工作线程中
//        private SurfaceHolder mHolder;
//
//        public CustomSurfaceView(Context context) {
//            super(context);
//
//            init();
//        }
//
//        public CustomSurfaceView(Context context, AttributeSet attrs) {
//            super(context, attrs);
//
//            init();
//        }
//
//        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//
//            init();
//        }
//
//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//            super(context, attrs, defStyleAttr, defStyleRes);
//
//            init();
//        }
//
//        private void init() {
//
//            mHolder = getHolder();
//
//            //设置holder的回调借口，用于将工作线程中绘制好的画面发回主线程，然后显示到主屏幕上
//            mHolder.addCallback(this);
//        }
//
//        /**
//         * 创建绘图表面,就是显示在前台的界面
//         * <p/>
//         * surface被创建的时候回调
//         *
//         * @param holder
//         */
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            mIsDraw = true;
//
//            new CanvasThread(holder).start();
//        }
//
//        /**
//         * 当surfaceView的窗口大小发生变化时，回调此方法(比如横竖屏界面切换时)
//         *
//         * @param holder
//         * @param format
//         * @param width
//         * @param height
//         */
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//        }
//
//        /**
//         * 销毁此surface的时候（比如退出当前界面）
//         *
//         * @param holder
//         */
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//
//
//            mIsDraw = false;
//            holder.removeCallback(this);
//        }
//
//
//        /**
//         * 创建一个线程，完成绘图
//         */
//        class CanvasThread extends Thread {
//
//            private Paint mPaint;  //画笔
//
//            private SurfaceHolder mSurfaceHolder;
//
//            public CanvasThread(SurfaceHolder surfaceHolder) {
//
//                mPaint = new Paint();
//
//                this.mSurfaceHolder = surfaceHolder;
//
//                mPaint.setColor(Color.RED);
//            }
//
//            @Override
//            public void run() {
//                super.run();
//
//                //1.锁定canvas画布，并且可以获得被锁定的画布对象
//                Canvas canvas = mSurfaceHolder.lockCanvas();
//
//                float radius = 10f;
//
//
//                //    while (mIsDraw) {
//
//                //2 绘制图像
//                canvas.drawCircle(400f, 400f, radius, mPaint);
//
//                //     radius ++;
//
////                    try {
////                        Thread.sleep(500);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//
//                //3.画布解锁并将绘制好的图像提交到UI线程中显示
//                mHolder.unlockCanvasAndPost(canvas);
//                // }
//            }
//        }
//}


}