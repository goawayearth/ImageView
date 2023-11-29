package com.example.imageview;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private float rotationAngle = 0.0f;//旋转的角度
    private float lastFocusX = 0;
    private float lastFocusY = 0;
    RelativeLayout rlImage;
    private int colorNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlImage = findViewById(R.id.rl_image);
        //rlImage.setBackgroundColor(Color.rgb(255, 255, 0)); // 设为黄色

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
            colorNum = (colorNum+1)%2;
            if(colorNum == 0){
                rlImage.setBackgroundColor(getResources().getColor(R.color.teal_200)); // 设为黄色
            }
            else{
                rlImage.setBackgroundColor(getResources().getColor(R.color.purple_500)); // 设为黄色
            }

            // 长按事件
            Toast.makeText(MainActivity.this, "颜色改变成功！", Toast.LENGTH_SHORT).show();
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

            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();
            float currentSpan = detector.getCurrentSpan();

            if (lastFocusX == 0 && lastFocusY == 0) {
                lastFocusX = focusX;
                lastFocusY = focusY;
            }

            // 当前帧与上一帧的手指向量
            Vector2D v1 = new Vector2D(focusX - lastFocusX, focusY - lastFocusY);
            Vector2D v2 = new Vector2D(focusX - focusX, focusY - focusY);

            // 计算两个向量之间的角度
            double angle = Vector2D.angleBetween(v1, v2);

            // 积累总角度
            rotationAngle += angle;
            // Reset when reach limits
            if (rotationAngle >= 360f) {
                rotationAngle -= 360f;
            } else if (rotationAngle <= -360f) {
                rotationAngle += 360f;
            }
            // Save focus for next call
            lastFocusX = focusX;
            lastFocusY = focusY;
            imageView.setRotation(rotationAngle);

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
