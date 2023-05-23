package com.liangzilixueha.liangzipage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class LiangziPop extends View {
    private Paint _paint = new Paint();
    private float _width;
    private float _height;
    private int startRawX;
    private int startRawY;
    private int 白线距底 = 200;
    private OnClickListener 左边;
    private OnClickListener 右边;

    public LiangziPop() {
        super(null);
    }

    public LiangziPop(Context context) {
        super(context);
        //将该控件添加到窗口中
    }

    public LiangziPop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        _width = getMeasuredWidth();
        _height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _paint.setAntiAlias(true);
        //画一个圆角矩形
        _paint.setColor(Color.rgb(72, 149, 255));
        canvas.drawRoundRect(0, 0, _width, _height, 60, 60, _paint);
        //添加一个文本控件
        _paint.setTextSize(50);
        _paint.setColor(0xff00ff00);
        canvas.drawText("Hello World", 0, 100, _paint);
        //画一条宽度为10的直线
        _paint.setStrokeWidth(3);
        _paint.setColor(0xffffffff);
        canvas.drawLine(0, _height - 白线距底, _width, _height - 白线距底, _paint);
        canvas.drawLine(_width / 2, _height - 白线距底, _width / 2, _height, _paint);
        //在正中间文字你好
        _paint.setTextSize(100);
        _paint.setColor(0xffffffff);
        canvas.drawText("你好", _width / 4 - 100, _height - 白线距底 / 2 + 25, _paint);
        canvas.drawText("再见", 3 * _width / 4 - 100, _height - 白线距底 / 2 + 25, _paint);

    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startRawX = rawX;
                startRawY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //一半距离
                int halfWidth = getMeasuredWidth() / 2;
                if (x < halfWidth && y > _height - 白线距底) {
                    if (左边 != null)
                        左边.onClick(this);
                }
                if (x > halfWidth && y > _height - 白线距底) {
                    if (右边 != null)
                        右边.onClick(this);
                }
                break;
        }
        return true;
    }

    public void 设置点击事件(myclick myclick) {
        this.左边 = v -> myclick.左侧点击();
        this.右边 = v -> myclick.右侧点击();
    }

    public interface myclick {
        void 左侧点击();

        void 右侧点击();
    }

    public void show(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = (int) _width;
        params.height = (int) _height;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        wm.addView(this, params);
    }

    public void set_width(float _width) {
        this._width = _width;
    }

    public void set_height(float _height) {
        this._height = _height;
    }
}
