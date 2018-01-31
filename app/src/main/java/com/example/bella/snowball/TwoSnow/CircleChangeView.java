package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/22.
 */

public class CircleChangeView extends View{
    private int backColor;
    private int toneColor;
    private float radios;
    private String text;
    private int textColor;
    private int textSize;
    private float strokeWidth;

    //文字画笔
    private Paint textPaint;
    //背景画笔
    private Paint backPaint;
    //进度条画笔
    private Paint tonePaint;
    //view的宽和高
    private int mWidth;
    private int mHeight;

    //进度
    private int currentProgress;
    private int maxProgress;

    private RectF rect;

    public CircleChangeView(Context context) {
        this(context,null);
    }

    public CircleChangeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleChangeView,defStyleAttr,0);
        for (int i = 0; i <typedArray.length() ; i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CircleChangeView_backColor:
                    backColor=typedArray.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.CircleChangeView_toneColor:
                    toneColor=typedArray.getInt(attr,Color.BLACK);
                    break;
                case R.styleable.CircleChangeView_radios:
                    radios=typedArray.getFloat(attr,50);
                    break;
                case R.styleable.CircleChangeView_texts:
                    text=typedArray.getString(attr);
                    break;
                case R.styleable.CircleChangeView_textColors:
                    textColor=typedArray.getInt(attr,Color.BLACK);
                    break;
                case R.styleable.CircleChangeView_textSizes:
                    textSize=typedArray.getDimensionPixelOffset(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleChangeView_strokeWidth:
                    strokeWidth=typedArray.getFloat(attr,50);
                    break;
            }
        }
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        //文字画笔
        textPaint=new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);

        //背景画笔
        backPaint=new Paint();
        backPaint.setStrokeWidth(30);
        backPaint.setColor(backColor);
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setStrokeCap(Paint.Cap.ROUND);
        backPaint.setAntiAlias(true);

        //进度条画布
        tonePaint=new Paint();
        tonePaint.setStrokeWidth(strokeWidth);
        tonePaint.setStyle(Paint.Style.STROKE);
        tonePaint.setColor(toneColor);
        tonePaint.setStrokeCap(Paint.Cap.ROUND);
        tonePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawCircle(mWidth/2,mHeight/2,radios,tonePaint);
        //这个表示在这个矩形范围类画你需要画的东西
        rect=new RectF(strokeWidth,strokeWidth,mWidth-strokeWidth,mHeight-strokeWidth);
        canvas.drawArc(rect,0,360,false,backPaint);

        if(currentProgress>0 && currentProgress<maxProgress){
            canvas.drawArc(rect,0,360*currentProgress/100,false,tonePaint);
        }else if(currentProgress==maxProgress){
            canvas.drawArc(rect,0,360,false,tonePaint);
        }
        //画文字
        text+="%";
        canvas.drawText(text,mWidth/2-strokeWidth,mHeight/2-strokeWidth,textPaint);
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
