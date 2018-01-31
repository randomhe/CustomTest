package com.example.bella.snowball.ThreeSnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by bella on 2018/1/26.
 */

public class PathTextView extends View{
    //路径对象
    private Path mPath;
    //画笔对象
    private Paint mPaint;
    //控制点的坐标
    private float ctrX,ctrY;
    //顶点Y轴的坐标
    private float waveY;
    //控件的宽和高
    private int mWidth,mHieght;

    private boolean isCrs;

    public PathTextView(Context context) {
        this(context,null);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PathTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPath=new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHieght=h;

        waveY=1/8f*mHieght;
        //ctrX=-1/4f*mWidth;
        ctrY=-1/8f*mHieght;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //起点坐标
        mPath.moveTo(-1/4f*mWidth,waveY);
        //二阶曲线
        mPath.quadTo(ctrX,ctrY,mWidth+1/4f*mWidth,waveY);
        //画闭合曲线
        mPath.lineTo(mWidth+1/4f*mWidth,mHieght);
        mPath.lineTo(-1/4f*mWidth,mHieght);
        mPath.close();

        canvas.drawPath(mPath,mPaint);

        //判断左移还是右移
        if(ctrX>=1/4f*mWidth+mWidth){
            isCrs=false;
        }else if(ctrX<=-1/4f*mWidth){
            isCrs=true;
        }
        ctrX=isCrs?ctrX+20:ctrX-20;

        //Y轴下降
        if(ctrY<=mHieght){
            waveY+=2;
            ctrY+=2;
        }
        mPath.reset();
        invalidate();
    }
}
