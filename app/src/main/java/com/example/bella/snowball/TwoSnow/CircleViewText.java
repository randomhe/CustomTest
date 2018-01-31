package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/24.
 */

public class CircleViewText extends View{
    private Bitmap mBitmap;
    private int bRadios;

    private int mWidth;
    private int mHeight;
    //拉伸矩阵
    private Matrix mMatrix;
    //着色器
    private BitmapShader bitmapShader;
    //画笔
    private Paint mPaint;
//    private Paint linePaint;
//    private Paint circlePaint;

    private RectF rectF;
    public CircleViewText(Context context) {
        this(context,null);
    }

    public CircleViewText(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleViewText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleViewText,defStyleAttr,0);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CircleViewText_Bitmaps:
                    mBitmap= BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
                case R.styleable.CircleViewText_bRadios:
                    bRadios=typedArray.getDimensionPixelOffset(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        mMatrix=new Matrix();
        bitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);

//        linePaint=new Paint();
//        linePaint.setColor(Color.RED);
//        linePaint.setStrokeWidth(20);
//        circlePaint=new Paint();
//        circlePaint.setColor(Color.YELLOW);
//        //设置色彩矩阵
//        ColorMatrix colorMatrix=new ColorMatrix(new float[]{
//                0.33f,0.59f,0.11f,0,0,
//                0.33f,0.59f,0.11f,0,0,
//                0.33f,0.59f,0.11f,0,0,
//                0,0,0,1,0,
//        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        rectF=new RectF(0,0,mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRoundRect(rectF,bRadios,bRadios,mPaint);
        //setUpShader();
        //canvas.drawCircle(bRadios,bRadios,bRadios,mPaint);
        //canvas.drawLine(0,mHeight-50,mWidth,mHeight-50,linePaint);
        //canvas.drawCircle(30,mHeight-50,30,circlePaint);
    }

    private void setUpShader() {
        float scale=1.0f;
        scale=Math.max(mWidth*1.0f/mBitmap.getWidth(),mHeight*1.0f/mBitmap.getHeight());
        mMatrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(bitmapShader);
        //int bSize=Math.min(mBitmap.getWidth(),mBitmap.getHeight());
        //scale=mWidth*1.0f/bSize;
    }
}
