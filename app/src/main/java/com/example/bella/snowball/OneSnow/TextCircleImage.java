package com.example.bella.snowball.OneSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/20.
 */

public class TextCircleImage extends View{
    private String text;
    private int textColor;
    private int textSize;
    private Bitmap image;
    private int imageScaleType;

    //画笔和矩形
    Paint mPaint;
    Rect mRect;
    Rect rect;

    public TextCircleImage(Context context) {
        this(context,null);
    }

    public TextCircleImage(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TextCircleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewStyle,defStyleAttr,0);
        for (int i = 0; i <typedArray.length() ; i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.TextViewStyle_Text:
                    text=typedArray.getString(attr);
                    break;
                case R.styleable.TextViewStyle_TextColor:
                    textColor=typedArray.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.TextViewStyle_TextSize:
                    textSize=typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.TextViewStyle_Image:
                    image= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
                case R.styleable.TextViewStyle_ImageScaleType:
                    imageScaleType=typedArray.getInt(attr,0);
                    break;
            }
         }
        typedArray.recycle();

        //初始化画笔和矩形
        mPaint=new Paint();
        mRect=new Rect();
        rect=new Rect();
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(text,0,text.length(),mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       //在这里面来测量大小
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);

        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        int width=0;
        int height=0;

        if(widthMode==MeasureSpec.EXACTLY){//真实值或者match_parent
            width=widthSize;
        }else{

            //文字决定的宽
            int textWidth= getPaddingLeft()+mRect.width()+getPaddingRight();
            //图片决定的宽
            int imageWidth=getPaddingLeft()+image.getWidth()+getPaddingRight();

            if(widthMode==MeasureSpec.AT_MOST){//wrap_content
                int decried=Math.max(textWidth,imageWidth);
                width=Math.min(decried,widthSize);
            }
        }

        if(heightMode==MeasureSpec.EXACTLY) {//真实值或者match_parent
            height=heightSize;
        }else{
            int textHeight=getPaddingTop()+mRect.height()+getPaddingBottom();
            int imageHeight=getPaddingTop()+image.getHeight()+getPaddingBottom();

            if(heightMode==MeasureSpec.AT_MOST){
                int decried=textHeight+imageHeight;
                height=Math.min(decried,heightSize);
            }
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        if(mRect.width()>getMeasuredWidth()){
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(text, paint, getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), getMeasuredHeight() - getPaddingBottom(), mPaint);
        }else {
            canvas.drawText(text, getMeasuredWidth() / 2 - mRect.width() * 1.0f / 2, getMeasuredHeight() - getPaddingBottom(), mPaint);
        }
        canvas.drawBitmap(image, null, rect, mPaint);
    }
}
