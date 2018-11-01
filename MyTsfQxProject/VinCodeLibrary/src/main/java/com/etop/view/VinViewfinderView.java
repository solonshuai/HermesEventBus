package com.etop.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.etop.vincode.R;

public class VinViewfinderView extends View {

    private static final long ANIMATION_DELAY = 10L;
    private Paint paint;
    private Paint paintLine;
    private int maskColor;
    private int frameColor;
    private Rect frame;
    private int w, h;
    //private Paint mTextPaint;
    private String mText;
    private int viewRotation;

    public VinViewfinderView(Context context, int w, int h) {
        super(context);
        this.w = w;
        this.h = h;
        paint = new Paint();
        paintLine = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        frameColor = resources.getColor(R.color.viewfinder_frame);// 绿色
        float textSize = resources.getDimension(R.dimen.view_text_size);

        /*mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(frameColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);*/
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        //viewRotation = OrientationSensorListener.getRotation();
        if (viewRotation==0 || viewRotation==2) {
            int t, b, l, r;
            l = w/6;
            r = w - l;
            int ntmp = (w-2*l)/4;
            t = (h-ntmp)/2;
            b = t + ntmp;
            frame = new Rect(l, t, r, b);

            // 画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面
            // 扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边
            paint.setColor(maskColor);
            canvas.drawRect(0, 0, width, frame.top, paint);
            canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
            canvas.drawRect(0, frame.bottom + 1, width, height, paint);

            paintLine.setColor(frameColor);
            paintLine.setStrokeWidth(10);
            paintLine.setAntiAlias(true);
            int num = (b - t) / 6;

            canvas.drawLine(l - 5, t, l + num, t, paintLine);
            canvas.drawLine(l, t, l, t + num, paintLine);

            canvas.drawLine(r, t, r - num, t, paintLine);
            canvas.drawLine(r, t - 5, r, t + num, paintLine);

            canvas.drawLine(l - 5, b, l + num, b, paintLine);
            canvas.drawLine(l, b, l, b - num, paintLine);

            canvas.drawLine(r, b, r - num, b, paintLine);
            canvas.drawLine(r, b + 5, r, b - num, paintLine);

            //mText = "如果无法识别，请点击拍照按钮保存图像";
        }else if (viewRotation==1 || viewRotation==3){
            paint.setColor(maskColor);
            canvas.drawRect(0, 0, width/2-100, height, paint);
            canvas.drawRect(width/2+100, 0, width, height, paint);

            paintLine.setColor(frameColor);
            paintLine.setStrokeWidth(10);
            paintLine.setAntiAlias(true);
            int num = 40;
            int t, b, l, r;
            l = width/2-100;
            r = width/2+100;
            t = 0;
            b = height;
            canvas.drawLine(l - 5, t, l + num, t, paintLine);
            canvas.drawLine(l, t, l, t + num, paintLine);

            canvas.drawLine(r, t, r - num, t, paintLine);
            canvas.drawLine(r, t - 5, r, t + num, paintLine);

            canvas.drawLine(l - 5, b, l + num, b, paintLine);
            canvas.drawLine(l, b, l, b - num, paintLine);

            canvas.drawLine(r, b, r - num, b, paintLine);
            canvas.drawLine(r, b + 5, r, b - num, paintLine);
        }

        /**
         * 当我们获得结果的时，我们更新整个屏幕的内
         */
        postInvalidateDelayed(ANIMATION_DELAY);
    }

    public void setViewRotation(int viewRotation) {
        this.viewRotation = viewRotation;
    }
}
