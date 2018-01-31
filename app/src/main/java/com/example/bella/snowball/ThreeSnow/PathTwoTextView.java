package com.example.bella.snowball.ThreeSnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by bella on 2018/1/26.
 */

public class PathTwoTextView extends View{

    private Path mPath;
    private Paint mPaint;

    private float ctrX,ctrY;
    private int mWidth,mheight;

    private float waveY;

    private boolean isRL;

    public PathTwoTextView(Context context) {
        this(context,null);
    }

    public PathTwoTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PathTwoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath=new Path();
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mheight=h;
        waveY=7/8f*mheight;
        ctrY=waveY-1/16f*mheight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       mPath.moveTo(-1/4f*mWidth,waveY);
       mPath.quadTo(ctrX,ctrY,mWidth+1/4f*mWidth,waveY);

       mPath.lineTo(mWidth+1/4f*mWidth,0);
       mPath.lineTo(-1/4f*mWidth,0);

       canvas.drawPath(mPath,mPaint);

       //判断左移右移的函数
        if(ctrX>=mWidth+1/4f*mWidth){
            isRL=false;
        }else if(ctrX<=-1/4f*mWidth){
            isRL=true;
        }
        ctrX=isRL?ctrX+20:ctrX-20;
        //判断Y轴
        if(ctrY>=0){
            waveY-=2;
            ctrY-=2;
        }
        mPath.reset();
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
