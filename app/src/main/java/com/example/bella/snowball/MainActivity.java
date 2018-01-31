package com.example.bella.snowball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bella.snowball.OneSnow.FallObject;
import com.example.bella.snowball.OneSnow.SnowView;

public class MainActivity extends AppCompatActivity {
    private SnowView mSnowBall;
    private Paint mPaint;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSnowBall=findViewById(R.id.snowball);
        mPaint=new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawCircle(25,25,25,mPaint);

        FallObject.builder builders = new FallObject.builder(bitmap);
        FallObject fallObject = builders
                .setSpeed(10)
                .build();

        mSnowBall.addFallObject(fallObject,50);//添加50个雪球对象
    }
}
