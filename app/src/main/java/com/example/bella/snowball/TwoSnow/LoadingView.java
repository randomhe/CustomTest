package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bella on 2018/1/23.
 */

public class LoadingView extends View{
    private int mWidth;
    private int mheight;

    private Paint backPaint;
    private Paint paint;

    private int radios;

    private RectF mRectF;

    private int currentAngel;
    private States states=States.LOADING;
    //网络状态
    public enum States{
        LOADING,
        SUCCESS,
        FAILURE,
    }

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backPaint=new Paint();
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setStrokeWidth(30);
        backPaint.setColor(Color.GRAY);

        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        radios=200;
        currentAngel=0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mheight=h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(states==States.LOADING){//正在加载
            //背景圆
            canvas.drawCircle(mWidth/2,mheight/2,radios,backPaint);
            //半边圆
            mRectF=new RectF(mWidth/2-radios,mheight/2-radios,mWidth/2+radios,mheight/2+radios);
            canvas.drawArc(mRectF,currentAngel,90,false,paint);
        }else if(states==States.SUCCESS){//加载成功

        }else if(states==States.FAILURE){//加载失败

        }
    }

    public int getRadios() {
        return radios;
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }

    public int getCurrentAngel() {
        return currentAngel;
    }

    public void setCurrentAngel(int currentAngel) {
        this.currentAngel = currentAngel;
        invalidate();
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
        invalidate();
    }
}
