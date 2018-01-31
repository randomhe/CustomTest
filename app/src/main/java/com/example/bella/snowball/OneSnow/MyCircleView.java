package com.example.bella.snowball.OneSnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bella on 2018/1/20.
 */

public class MyCircleView extends View{

    private Paint mPaint;

    public MyCircleView(Context context) {
        this(context,null);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth()/4,getHeight()/4,100,mPaint);
        canvas.drawCircle(3*getMeasuredWidth()/4,getHeight()/4,100,mPaint);
        canvas.drawLine(getMeasuredWidth()/5,getHeight()/2,4*getMeasuredWidth()/5,getHeight()/2,mPaint);
    }
}
