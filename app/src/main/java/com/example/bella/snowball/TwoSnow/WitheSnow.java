package com.example.bella.snowball.TwoSnow;

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

public class WitheSnow extends View{
    private int width;
    private int height;
    private Paint mPaint;

    public WitheSnow(Context context) {
        this(context,null);
    }

    public WitheSnow(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public WitheSnow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth()/2,height,50,mPaint);
        getHandler().postDelayed(rannable,5);
    }

    Runnable rannable=new Runnable() {
        @Override
        public void run() {
           height+=20;
           if(height>=getMeasuredHeight()){
               height=0;
           }
            invalidate();
        }
    };
}
