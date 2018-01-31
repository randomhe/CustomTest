package com.example.bella.snowball.Adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bella on 2018/1/26.
 */

public class TimeItemDecoration extends RecyclerView.ItemDecoration{
    private static final int LEFTDISTANCE=200;
    private static final int TOPDISTANCE=50;

    private Paint circlePaint;
    private Paint textPaint;
    private Paint mPaint;

    private int radios=30;

    //构造函数
    public TimeItemDecoration(){

        circlePaint=new Paint();
        circlePaint.setColor(Color.RED);

        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);

        textPaint=new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(10);
        textPaint.setColor(Color.BLUE);
    }


    //设置分割线的宽度和高度
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(LEFTDISTANCE,TOPDISTANCE,0,0);
    }

    //画在item的下方
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i <parent.getChildCount(); i++) {

            View child=parent.getChildAt(i);
            //找到圆心画圆
            int circle_x=child.getLeft()-60;
            int circle_y=child.getTop()+radios;
            c.drawCircle(circle_x,circle_y,radios,circlePaint);

            //画上线段
            int up_x=circle_x;
            int up_y=child.getTop()-TOPDISTANCE;
            c.drawLine(up_x,up_y,up_x,circle_y-radios,textPaint);

            //画下线段
            int down_x=circle_x;
            int down_y=circle_y+radios;
            c.drawLine(down_x,down_y,down_x,child.getBottom(),textPaint);

            int index=parent.getChildAdapterPosition(child);
            int text_x=child.getLeft()-190;
            int text_y=child.getTop()-20;
            switch (index){
                case 0:
                    c.drawText("16:35",text_x,text_y,mPaint);
                    c.drawText("2017.1.8",text_x+5,text_y+20,mPaint);
                    break;
                case 1:
                    c.drawText("11:25",text_x,text_y,mPaint);
                    c.drawText("2017.1.4",text_x+5,text_y+20,mPaint);
                    break;
                case 2:
                    c.drawText("10:25",text_x,text_y,mPaint);
                    c.drawText("2017.1.3",text_x+5,text_y+20,mPaint);
                    break;
                case 3:
                    c.drawText("09:25",text_x,text_y,mPaint);
                    c.drawText("2017.1.2",text_x+5,text_y+20,mPaint);
                    break;
                case 4:
                    c.drawText("8:25",text_x,text_y,mPaint);
                    c.drawText("2017.1.1",text_x+5,text_y+20,mPaint);
                    break;
                case 5:
                    c.drawText("7:13",text_x,text_y,mPaint);
                    c.drawText("2016.12.31",text_x+5,text_y+20,mPaint);
                    break;
                case 6:
                    c.drawText("7:13",text_x,text_y,mPaint);
                    c.drawText("2016.12.31",text_x+5,text_y+20,mPaint);
                    break;
            }
        }
    }
    //画在Item的上方
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
