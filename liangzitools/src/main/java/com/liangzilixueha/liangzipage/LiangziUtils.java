package com.liangzilixueha.liangzipage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;

public class LiangziUtils {
    /**
     * @param activity 当前活动界面
     * @return 返回为bitmap类型
     */
    public Bitmap 获取当前屏幕截图(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        return activity.getWindow().getDecorView().getDrawingCache();
    }

    /**
     * @param context
     * @param bitmap  传入图片
     * @return
     */
    public int 保存图片到图库(Context context, Bitmap bitmap) {
        //保存的位置位于：/storage/emulated/0/Pictures
        ContentResolver cr = context.getContentResolver();
        MediaStore.Images.Media.insertImage(cr, bitmap, "截图" + System.currentTimeMillis(), "liangzi");
        return 0;
    }

    public Bitmap 毛玻璃效果(Bitmap bitmap) {
        //将图片进行毛玻璃效果
        return bitmap;
    }

}
