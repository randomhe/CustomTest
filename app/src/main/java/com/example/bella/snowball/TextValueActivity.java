package com.example.bella.snowball;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class TextValueActivity extends AppCompatActivity {
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_value);
        mImageView=findViewById(R.id.imageView);
        AnimatedVectorDrawable animatedVectorDrawable= (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.ani_heart);
        mImageView.setImageDrawable(animatedVectorDrawable);
        if(animatedVectorDrawable!=null){
            animatedVectorDrawable.start();
        }

    }





 //   loadingView=findViewById(R.id.loading_view);
//    mHanlder.postDelayed(runnable,5);
//    private Handler mHanlder=new Handler();
//    private Runnable runnable=new Runnable() {
//        @Override
//        public void run() {
//            if(i<360){
//                i+=10;
//                loadingView.setCurrentAngel(i);
//            }else{
//                i=0;
//            }
//            mHanlder.postDelayed(runnable,5);
//        }
//    };


 //开关
//        switch_view=findViewById(R.id.switch_view);
//        switch_view.setMyCallBack(new SwithView.MyCallBack() {
//            @Override
//            public void OnMyCallBack(View view) {
//                Log.e("************","没有");
//                if(!switch_view.isChecked()){
//                    switch_view.setChecked(true);
//                }else{
//                    switch_view.setChecked(false);
//                }
//            }
//        });
//        circleChangeView=findViewById(R.id.circle_change);
//        circleChangeView.setMaxProgress(100);
//        handler.postDelayed(runnable,500);


 //进度条
//    private Handler handler=new Handler();
//    private Runnable runnable=new Runnable() {
//        @Override
//        public void run() {
//            if(i<100){
//                i+=1;
//                circleChangeView.setText(i+"");
//                circleChangeView.setCurrentProgress(i);
//            }
//            handler.postDelayed(this,500);
//        }
//    };
}
