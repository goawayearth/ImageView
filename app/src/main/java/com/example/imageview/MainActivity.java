package com.example.imageview;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// 双击事件，放大或者缩小
//拖动事件，照片移动
//长按事件，
//双指旋转
//双击并拖动
//长按并拖动

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private float rotationAngle = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        gestureDetector = new GestureDetector(this, new MyGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(this, new MyScaleGestureListener());

        //向view中设置照片
        imageView.setImageResource(R.drawable.img);

        imageView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            scaleGestureDetector.onTouchEvent(event);
            return true;
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        boolean isDoubleTap = false;
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            isDoubleTap = true;
            // 双击事件
            scaleFactor = (scaleFactor == 1.0f) ? 2.0f : 1.0f;
            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
            return true;
        }


        @Override
        public void onLongPress(MotionEvent e) {
            // 长按事件
            Toast.makeText(MainActivity.this, "Long Press", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // 滑动事件
            imageView.scrollBy((int) distanceX, (int) distanceY);
            return true;
        }
    }

    private class MyScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // 缩放事件
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(1.0f, Math.min(scaleFactor, 3.0f)); // 设置最大和最小缩放倍数
            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            // 缩放开始事件
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            // 缩放结束事件
        }
    }
}
