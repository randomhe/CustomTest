package com.example.bella.snowball.ThreeSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/25.
 */

public class ShadeBitmapView extends View{
    //原图
    private Bitmap mBitmap;
    //图像着色器
    //private BitmapShader bitmapShader;
    private LinearGradient linearGradient;
    private Paint circlePaint;

    private int mWidth,mHeight;
    //private float posX,posY;
//    private Matrix matrix;
//    private int Radios=200;

    //阴影图
    //private Bitmap mBitmapShade;
    //private int mWidth,mHeight;

    public ShadeBitmapView(Context context) {
        this(context,null);
    }

    public ShadeBitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ShadeBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.ShadeBitmapView,defStyleAttr,0);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.ShadeBitmapView_shadeBitmap:
                    mBitmap= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        //画圆的笔
         circlePaint=new Paint();
//        //画线的笔
//        strokePaint=new Paint();
//        strokePaint.setColor(Color.BLACK);
//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setStrokeWidth(10);
//
//        //matrix=new Matrix();
          //bitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

//        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
//        mPaint.setColor(Color.DKGRAY);
//        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        //mBitmapShade=mBitmap.extractAlpha();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
         mWidth=w;
         mHeight=h;
         linearGradient=new LinearGradient(0,0,mWidth,mHeight/2,Color.RED,Color.YELLOW, Shader.TileMode.MIRROR);
         circlePaint.setShader(linearGradient);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction()==MotionEvent.ACTION_MOVE){
//            posX=event.getX();
//            posY=event.getY();
//            invalidate();
//        }
//        return true;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
         canvas.drawRect(0,0,mWidth,mHeight,circlePaint);
//        canvas.drawColor(Color.YELLOW);
//
//        canvas.drawCircle(posX,posY,100,circlePaint);
//        canvas.drawCircle(posX,posY,100,strokePaint);

        //canvas.drawRoundRect(0,0,mWidth,mHeight,20,20,mPaint);
        //canvas.drawBitmap(mBitmapShade,10,10,mPaint);
        //canvas.drawBitmap(mBitmap,30,30,null);
    }


}
