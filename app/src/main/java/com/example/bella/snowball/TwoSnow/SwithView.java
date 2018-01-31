package com.example.bella.snowball.TwoSnow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.bella.snowball.R;

/**
 * Created by bella on 2018/1/22.
 */

public class SwithView extends View implements View.OnClickListener{

    private int openColor;
    private int closeColor;
    private float swRadios;
    private boolean isChecked;

    private int mWidth;
    private int mHeight;

    private Paint circlePaint;

    private Paint backPaint;
    private Paint backOpenPaint;

    public SwithView(Context context) {
        this(context,null);
    }

    public SwithView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SwithView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SwitchView,defStyleAttr,0);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.SwitchView_openColor:
                    openColor=typedArray.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.SwitchView_closeColor:
                    closeColor=typedArray.getInt(attr,Color.BLACK);
                    break;
                case R.styleable.SwitchView_swradios:
                    swRadios=typedArray.getFloat(attr,50);
                    break;
                case R.styleable.SwitchView_isChecked:
                    isChecked=typedArray.getBoolean(attr,false);
                    break;
            }
        }
        typedArray.recycle();
        initPaint();
        setOnClickListener(this);
    }

    private void initPaint() {
       //关闭
        circlePaint=new Paint();
        circlePaint.setColor(closeColor);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        //关闭
        backPaint=new Paint();
        //打开
        backOpenPaint=new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isChecked){//如果点击了的话
            backOpenPaint.setColor(Color.YELLOW);
            canvas.drawCircle(swRadios,mHeight-2*swRadios,swRadios,backOpenPaint);
            backOpenPaint.setStyle(Paint.Style.STROKE);
            backOpenPaint.setStrokeWidth(swRadios*4);
            canvas.drawLine(swRadios,0,mWidth-swRadios,0,backOpenPaint);
            backOpenPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(mWidth-swRadios,mHeight-2*swRadios,swRadios,backOpenPaint);

            canvas.drawCircle(swRadios,mHeight-2*swRadios,swRadios,circlePaint);
        }else{//关闭
            //画背景
            backPaint.setColor(Color.GRAY);
            canvas.drawCircle(swRadios,mHeight-2*swRadios,swRadios,backPaint);
            backPaint.setStyle(Paint.Style.STROKE);
            backPaint.setStrokeWidth(swRadios*4);
            canvas.drawLine(swRadios,0,mWidth-swRadios,0,backPaint);
            backPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(mWidth-swRadios,mHeight-2*swRadios,swRadios,backPaint);
            //画圆
            canvas.drawCircle(mWidth-swRadios,mHeight-2*swRadios,swRadios,circlePaint);
        }
    }

    @Override
    public void onClick(View view) {
        callBack.OnMyCallBack(view);
    }

    public interface MyCallBack{
        void OnMyCallBack(View view);
    }

    private MyCallBack callBack;

    public void setMyCallBack(MyCallBack callBack){
        this.callBack=callBack;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        invalidate();
    }
}
