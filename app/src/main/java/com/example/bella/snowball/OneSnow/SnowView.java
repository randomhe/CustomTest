package com.example.bella.snowball.OneSnow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bella on 2018/1/10.
 */

public class SnowView extends View{
    //默认的宽高
    private int defaultWidth=600;
    private int defaultHeight=1000;
    //真是的宽高
    private int viewWidth;
    private int viewHeight;
    //变化的Y轴
    private int snowY;
    //画笔
    private Paint mPaint;
    private List<FallObject> list;

    public SnowView(Context context) {
        super(context);
        init();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        snowY=0;
        list=new ArrayList<>();
//        mPaint=new Paint();
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.FILL);
    }
    //自定义view的测量规则
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=onMeasureSize(defaultWidth,widthMeasureSpec);
        int height=onMeasureSize(defaultHeight,heightMeasureSpec);

        setMeasuredDimension(width,height);
        viewWidth=width;
        viewHeight=height;
    }
    //测量
    private int onMeasureSize(int defaults,int measureSpec){

        int result=defaults;
        int specMode=View.MeasureSpec.getMode(measureSpec);
        int specSize=View.MeasureSpec.getSize(measureSpec);

        if(specMode== MeasureSpec.EXACTLY){
            result=specSize;
        }else if(specMode== MeasureSpec.AT_MOST){
            result=Math.min(result,specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawCircle(100,snowY,20,mPaint);
        Log.d("******************",list.size()+"");
        if(list.size()>0){
            for (int i = 0; i <list.size() ; i++) {
                list.get(i).drawObject(canvas);
            }
        }
        getHandler().postDelayed(runnable,5);
    }

    //重绘线程
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
//            snowY+=15;
//            if(snowY>viewHeight){
//                snowY=0;
//            }
            invalidate();
        }
    };

    public void addFallObject(final FallObject fallObject, final int num){
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                for (int i = 0; i < num; i++) {
                    FallObject newFallObject=new FallObject(fallObject.builders,viewWidth,viewHeight);
                    list.add(newFallObject);
                }
                invalidate();
                return true;
            }
        });
    }

}
