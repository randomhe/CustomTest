package com.example.bella.snowball.OneSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/20.
 */

public class MyTextView extends View{
    private String text;
    private int textColor;
    private int textSize;

    private Paint mPaint;
    private Rect mRect;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.TextViewStyle,defStyleAttr,0);
        for (int i = 0; i <typedArray.length() ; i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.TextViewStyle_Text:
                    text=typedArray.getString(attr);
                    break;
                case R.styleable.TextViewStyle_TextColor:
                    textColor=typedArray.getInt(attr,Color.BLACK);
                    break;
                case R.styleable.TextViewStyle_TextSize:
                    textSize=typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();

        mPaint=new Paint();
       //mPaint.setColor(textColor);

        mRect=new Rect();
       // mPaint.getTextBounds(text,0,text.length(),mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else{
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text,0,text.length(),mRect);
            float textWidth=mRect.width();
            int desired= (int) (getPaddingLeft()+textWidth+getPaddingRight());
            width=desired;
        }

        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text,0,text.length(),mRect);
            float textHeight=mRect.height();
            int desied= (int) (getPaddingTop()+textHeight+getPaddingBottom());
            height=desied;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(textColor);
        canvas.drawText(text,getWidth()/2-mRect.width()/2,getHeight()/2+mRect.height()/2,mPaint);
    }
}
