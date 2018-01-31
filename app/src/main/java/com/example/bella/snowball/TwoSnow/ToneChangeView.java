package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/20.
 */

public class ToneChangeView extends View{
    //背景色
    private int oneColor;
    //进度条色
    private int twoColor;
    //文字
    private int textColor;
    private String text;
    private int textSize;
    //线宽
    private float strokeWidth;
    //内边距
    private float padding;
    //最大进度
    private int maxProgress;
    //宽高
    private int mWidth;
    private int mHeight;
    private int oneProgress;

    private int currentProgress=0;

    private Paint backGrandPaint;
    private Paint mPaint;
    private Paint textPaint;

    private Rect mRect;

    public ToneChangeView(Context context) {
        this(context,null);
    }

    public ToneChangeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ToneChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("**************","ToneChangeView");
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.ToneChangeView,defStyleAttr,0);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.ToneChangeView_OneColor:
                    oneColor=typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.ToneChangeView_TwoColor:
                    twoColor=typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.ToneChangeView_textColor:
                    textColor=typedArray.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.ToneChangeView_text:
                    text=typedArray.getString(attr);
                    break;
                case R.styleable.ToneChangeView_textSize:
                    textSize=typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ToneChangeView_StrokeWidth:
                    strokeWidth=typedArray.getFloat(attr,50);
                    break;
                case R.styleable.ToneChangeView_padding:
                    padding=typedArray.getFloat(attr,20);
                    break;
                case R.styleable.ToneChangeView_MaxProgress:
                    maxProgress=typedArray.getInt(attr,100);
                    break;
            }
        }
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        //背景画笔
        backGrandPaint=new Paint();
        backGrandPaint.setColor(oneColor);
        backGrandPaint.setStrokeWidth(strokeWidth);
        backGrandPaint.setAntiAlias(true);
        backGrandPaint.setStrokeCap(Paint.Cap.ROUND);

        //进度条画笔
        mPaint=new Paint();
        mPaint.setColor(twoColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //文字画笔
        textPaint=new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        textPaint.setStrokeCap(Paint.Cap.ROUND);

        mRect=new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("**************","onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //这里得到测量的宽和高
        Log.e("**************","onSizeChanged");
        Log.e("**************","w"+w);
        Log.e("**************","h"+h);
        Log.e("**************","oldw"+oldw);
        Log.e("**************","oldh"+oldh);
        mWidth=w;
        mHeight=h;
        oneProgress= (int) ((mWidth-padding*2)/maxProgress);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("**************","onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("**************"," onDraw");
        canvas.drawLine(padding,mHeight/2,mWidth-padding,mHeight/2,backGrandPaint);
        //画进度条
        if(currentProgress==0){
            //先画背景
            canvas.drawLine(padding,mHeight/2,padding+5,mHeight/2,mPaint);
        }else if(currentProgress>0 && currentProgress<maxProgress){
            canvas.drawLine(padding,mHeight/2,currentProgress*oneProgress+padding,mHeight/2,mPaint);
        }else if(currentProgress==maxProgress){//结束的时候画文字和线
            canvas.drawLine(padding,mHeight/2,mWidth-padding,mHeight/2,mPaint);
        }

        //画文字
        text+="%";
        textPaint.getTextBounds(text,0,text.length(),mRect);
        if(currentProgress==0){
            canvas.drawText(text,padding,mHeight/2,textPaint);
        }else if(currentProgress>0 && currentProgress<maxProgress){
            canvas.drawText(text,currentProgress*oneProgress+padding,mHeight/2,textPaint);
        }else if(currentProgress==maxProgress){
            if (currentProgress * oneProgress + padding >= mWidth - padding) {
                canvas.drawText(text, mWidth - padding , mHeight / 2 , textPaint);
            } else {
                canvas.drawText(text, currentProgress * oneProgress + padding, mHeight / 2 , textPaint);
            }
        }

    }

    public int getOneColor() {
        return oneColor;
    }

    public void setOneColor(int oneColor) {
        this.oneColor = oneColor;
    }

    public int getTwoColor() {
        return twoColor;
    }

    public void setTwoColor(int twoColor) {
        this.twoColor = twoColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getOneProgress() {
        return oneProgress;
    }

    public void setOneProgress(int oneProgress) {
        this.oneProgress = oneProgress;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public Paint getBackGrandPaint() {
        return backGrandPaint;
    }

    public void setBackGrandPaint(Paint backGrandPaint) {
        this.backGrandPaint = backGrandPaint;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
    }

    public Rect getmRect() {
        return mRect;
    }

    public void setmRect(Rect mRect) {
        this.mRect = mRect;
    }
}
