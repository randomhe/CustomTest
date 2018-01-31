package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/23.
 */

public class FlowerView extends View{

    //图片
    private Bitmap bitmap;
    //圆角的大小
    private int roundRadios;
    private static final int BODER_RADIUS_DEFAULT=10;

    //宽度和高度
    private int mWidth;
    private int mHeight;

    //用于缩放的
    private Matrix mMatrix;
    //着色器
    private BitmapShader mBitmapShader;
    //画笔
    private Paint mBitmapPaint;

    public FlowerView(Context context) {
        this(context,null);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public FlowerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SrcView,defStyleAttr,0);
        for (int i = 0; i <typedArray.length() ; i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.SrcView_Src:
                    bitmap=BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;
                case R.styleable.SrcView_roundRadios:
                    roundRadios=typedArray.getDimensionPixelOffset(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        mMatrix=new Matrix();
        mBitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint=new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("******************",""+getMeasuredWidth());
        Log.e("******************",""+getMeasuredHeight());
        mWidth=Math.min(getMeasuredWidth(),getMeasuredHeight());
        mHeight=mWidth;
        roundRadios=mWidth/2;
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("******************","onDraw");
       setUpShader();
       canvas.drawCircle(roundRadios,roundRadios,roundRadios,mBitmapPaint);
    }

    private void setUpShader() {
        float scale=1.0f;
        //获得图片和view的比例
        int bSmart=Math.min(bitmap.getWidth(),bitmap.getHeight());
        scale=mWidth*1.0f/bSmart;
        //设置缩放
        mMatrix.setScale(scale,scale);
        //设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        //设置着色器
        mBitmapPaint.setShader(mBitmapShader);
    }
}
