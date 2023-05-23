package com.liangzilixueha.liangzipage;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public class LiangziPageAdapter extends androidx.viewpager.widget.PagerAdapter {
    private List<View> mViews;  //存放视图的数组

    public LiangziPageAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override   //返回要滑动的VIew的个数
    public int getCount() {
        return mViews.size();
    }

    @Override  //来判断pager的一个view是否和instantiateItem方法返回的object有关联
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override  //从当前container中删除指定位置（position）的View;
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView(mViews.get(position));
    }

    @NonNull
    @Override  //第一：将当前视图添加到container中，第二：返回当前View
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        container.addView(mViews.get(position));
        return mViews.get(position);
    }
}
