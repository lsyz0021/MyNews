package com.bandeng.mynews.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bandeng.mynews.R;

public class SplashActivity extends AppCompatActivity {
    private Handler myHandle = new Handler() {
        int second = 0;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    second++;
                    time.setText("跳过" + second + "s");
                    myHandle.sendEmptyMessageDelayed(1, 1000);
                    break;
                case 2:

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                    break;

            }
        }
    };
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        // 去掉全屏
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash);
        time = (TextView) findViewById(R.id.tv_time);
        // 点击跳过
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        });

        // 做动画
        AnimationSet animation = createAnimation();
        imageView.setAnimation(animation);

        // 动画监听器，动画做完开始计时
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 定时跳过
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 用于显示右上角的倒计时
                        myHandle.sendEmptyMessageDelayed(1, 1000);
                        // 用于启动下一个Activity
                        myHandle.sendEmptyMessageDelayed(2, 3000);

                    }
                }).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private AnimationSet createAnimation() {
        AnimationSet set = new AnimationSet(false);
        // 旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF
                , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF
                , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);

        // 透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);

        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        return set;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandle.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
        // 屏蔽返回键
//        super.onBackPressed();
    }
}
