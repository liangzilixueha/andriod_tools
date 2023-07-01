package com.liangzilixueha.andriod_tools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liangzilixueha.andriod_tools.databinding.ActivityMainBinding;
import com.liangzilixueha.liangzipage.LiangziCircle;
import com.liangzilixueha.liangzipage.LiangziPageAdapter;
import com.liangzilixueha.liangzipage.LiangziPop;
import com.liangzilixueha.liangzipage.LiangziUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "csw";
    private ActivityMainBinding binding;
    private List<View> mViews;  //存放视图的数组
    private View view1, view2, view3;
    private PagerAdapter mPagerAdapter;//适配器
    public Button btn;
    public static TextView tv;
    public static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1:
                    tv.setText("修改过了");
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btn = binding.btn;
        tv = binding.textView;
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });


        LayoutInflater inflater = getLayoutInflater();//获取布局对象管理
        view1 = inflater.inflate(R.layout.cl1, null);//实例化view
        view2 = inflater.inflate(R.layout.cl2, null);
        view3 = inflater.inflate(R.layout.cl3, null);

        mViews = new ArrayList<View>();//将要显示的布局存放到list数组
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mPagerAdapter = new LiangziPageAdapter(mViews);//实例化适配器
        binding.vp.setAdapter(mPagerAdapter);//设置适配器
        binding.vp.setPageTransformer(true, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;


            @Override
            public void transformPage(@NonNull View view, float position) {
                int pageWidth = view.getWidth();
                if (position < -1) { // \[-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);
                } else if (position <= 0) { // \[-1,0\]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);
                } else if (position <= 1) { // (0,1\]
                    // Fade the page out.
                    view.setAlpha(1 - position);
                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);
                    // Scale the page down (between MIN_SCALE and 1)
//                    float scaleFactor = MIN_SCALE
//                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
//                    view.setScaleX(scaleFactor);
//                    view.setScaleY(scaleFactor);
                } else { // (1,+Infinity\]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }

        });
        ArrayList<TextView> list = new ArrayList<>();
        list.add(binding.tv1);
        list.add(binding.tv2);
        list.add(binding.tv3);
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            list.get(i).setOnClickListener(view -> {
                binding.vp.setCurrentItem(finalI);
            });
        }
        binding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.cv.setTranslationX(position * binding.tv1.getWidth() + positionOffsetPixels / 3);


            }

            @Override
            public void onPageSelected(int position) {
//                Log.e(TAG, "onPageSelected: " + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}