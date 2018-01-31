package com.example.bella.snowball.OneSnow;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by bella on 2018/1/10.
 */

public class FallObject {
    //下落速度和下落的东西
    private int initSpeed;
    private Bitmap bitmap;

    //随机下落的点
    private Random random;

    //物体的XY坐标
    private int intX;
    private int intY;

    //物体的当前坐标
    private int presentX;
    private int presentY;

    //父容器的宽高
    private int parentWidth;
    private int parentHeight;

    //当前物体的宽高
    private int presentWidth;
    private int presentHeight;

    //当前的下落速度
    private int presentSpeed;

    public builder builders;

    private static int defalutSpeed=10;

    public FallObject(builder builders,int parentWidth,int parentHeight){
        random=new Random();

        this.parentWidth=parentWidth;
        this.parentHeight=parentHeight;

        intX=random.nextInt(parentWidth);
        intY=random.nextInt(parentHeight)-parentHeight;//从头顶落下

        presentX=intX;
        presentY=intY;

        initSpeed=builders.speed;

        bitmap=builders.bitmap;

        presentWidth=bitmap.getWidth();
        presentHeight=bitmap.getHeight();
    }

    private FallObject(builder builders){
        this.builders=builders;
        this.initSpeed=builders.speed;
        this.bitmap=builders.bitmap;
    }

    //绘制图像
    public void drawObject(Canvas canvas){
        moveObject();
        canvas.drawBitmap(bitmap,presentWidth,presentHeight,null);
    }

    private void moveObject() {
        moveY();
        if(presentHeight>parentHeight){
            reset();
        }
    }

    private void moveY() {
        presentY+=presentSpeed;
    }

    private void reset() {
        presentHeight=-presentY;
        presentSpeed=initSpeed;
    }


    public static class builder{
        private int speed;
        private Bitmap bitmap;
        public builder(Bitmap bitmap){
            this.bitmap=bitmap;
            this.speed=defalutSpeed;
        }


        public builder setSpeed(int speed){
            this.speed=speed;
            return this;
        }

        public builder setBitmap(Bitmap bitmap){
            this.bitmap=bitmap;
            return this;
        }

        public FallObject build(){
            return new FallObject(this);
        }
    }
}
