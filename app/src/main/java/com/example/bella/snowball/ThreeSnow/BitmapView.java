package com.example.bella.snowball.ThreeSnow;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bella on 2018/1/25.
 */

public class BitmapView extends View{
    //两张图片
    private Bitmap srcBitmap;
    private Bitmap drcBitmap;
    //宽和高
    private int mWidth,mHeight;

    public BitmapView(Context context) {
        super(context);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
