package com.example.bella.snowball.CanvansText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by bella on 2018/1/27.
 */

public class SaveTestView extends View {
    private static final int defaultWidth = 300;
    private static final int defaultHeight = 300;
    //宽和高
    private int mWidth;
    private int mHeight;
    //画笔
    private Paint mPaint;

    private Paint bigLinePaint;
    private Paint smartLinePaint;
    private Paint textPaint;
    //画指针
    private Paint smartPaint;
    private Paint pointerPaint;
    //圆的半径
    private int radios;

    public SaveTestView(Context context) {
        this(context, null);
    }

    public SaveTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaveTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);

        bigLinePaint = new Paint();
        bigLinePaint.setColor(Color.BLACK);
        bigLinePaint.setStyle(Paint.Style.STROKE);
        bigLinePaint.setStrokeWidth(5);

        smartLinePaint = new Paint();
        smartLinePaint.setColor(Color.BLUE);

        //画文字的画笔初始化
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);

        //画指针的笔
        smartPaint = new Paint();
        smartPaint.setColor(Color.RED);

        pointerPaint = new Paint();
        pointerPaint.setStyle(Paint.Style.STROKE);
        pointerPaint.setColor(Color.BLUE);
        pointerPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = defaultWidth;
        int height = defaultHeight;
        if (widthMode == MeasureSpec.EXACTLY) {//真实值和matchparent
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = defaultWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {//真实值和matchparent
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = defaultHeight;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        radios = mWidth / 2 - 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.YELLOW);

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, radios, mPaint);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {//大针
                canvas.drawLine(0, -radios + 10, 0, -radios + 50, bigLinePaint);
            } else {
                canvas.drawLine(0, -radios + 10, 0, -radios + 40, smartLinePaint);
            }
            canvas.rotate(6);
        }

        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                drawNumText(canvas, "12", i * 30, textPaint);
            } else {
                drawNumText(canvas, "" + i, i * 30, textPaint);
            }
        }
        canvas.restore();//圆心的又回到了左上角

        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 20, smartPaint);
        canvas.drawLine(0, 20, 0, radios - 150, pointerPaint);
        canvas.restore();


    }

    private void drawNumText(Canvas canvas, String text, int degree, Paint paint) {
        Rect mRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), mRect);
        canvas.rotate(degree);
        canvas.translate(0, -(radios - 90));
        canvas.rotate(-degree);
        canvas.drawText(text, -(mRect.width() / 2), 0, paint);

        canvas.rotate(degree);
        canvas.translate(0, (radios - 90));
        canvas.rotate(-degree);
    }

    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {

        }
    };

}
