package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/24.
 */

public class PorterDuffText extends View{

    private Bitmap oneBitmap;
    private Bitmap twoBitmap;

    //屏幕的宽和高
    private int mWidth,mHeight;

    private int sr_x,sr_y;

    private PorterDuffXfermode porterDuffXfermode;

    private Paint mPaint;

    public PorterDuffText(Context context) {
       this(context,null);
    }

    public PorterDuffText(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PorterDuffText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.PorterDuffText,defStyleAttr,0);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.PorterDuffText_oneBitmap:
                    oneBitmap= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
                case R.styleable.PorterDuffText_twoBitmap:
                    twoBitmap=BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        porterDuffXfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        sr_x=mWidth/2-oneBitmap.getWidth()/2;
        sr_y=mHeight/2-oneBitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        //设置黎平缓存
        int sc=canvas.saveLayer(0,0,mWidth,mHeight,mPaint);

        //绘制目标
        canvas.drawColor(0xFF8f66DA);
        mPaint.setXfermode(porterDuffXfermode);
        //绘制原图
        canvas.drawBitmap(oneBitmap,sr_x,sr_y,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
