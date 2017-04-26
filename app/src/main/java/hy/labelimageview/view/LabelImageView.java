package hy.labelimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import hy.labelimageview.R;

/**
 * Created by huangyue on 2017/4/25.
 */

public class LabelImageView extends android.support.v7.widget.AppCompatImageView {
    /*条形*/
    public static final int TYPE_BAR = 0;
    /*三角形*/
    public static final int TYPE_TRIANGLE = 1;
    /*标签类型*/
    private int mLabelType;
    private Paint mPaint;
    private Paint mTextPaint;
    /*条形标签宽度*/
    private int mBarWidth;
    /*条形标签高度*/
    private int mBarHeight;
    /*条形标签距离顶部的距离*/
    private int mBarTopMargin;
    /*条形标签圆弧的弧度*/
    private int mBarRadius;
    private float mTextOffsetY;
    /*标签中文字*/
    private String mText = "";
    /*三角形标签的边长*/
    private int mSideSize;

    public LabelImageView(Context context) {
        this(context,null);
    }

    public LabelImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        /*解析attrs*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelImageView);
        /*默认值-1，如果不设置标签类型则不显示标签，就是一个普通的ImageView*/
        mLabelType = typedArray.getInteger(R.styleable.LabelImageView_label_type,-1);
        if(mLabelType == 0){
            mBarWidth = (int)typedArray.getDimension(R.styleable.LabelImageView_bar_width,0);
            mBarHeight = (int)typedArray.getDimension(R.styleable.LabelImageView_bar_height,0);
            mBarRadius = typedArray.getInt(R.styleable.LabelImageView_bar_radius,4);
            mBarTopMargin = (int)typedArray.getDimension(R.styleable.LabelImageView_bar_topMargin,0);
        }else {
            mSideSize = (int)typedArray.getDimension(R.styleable.LabelImageView_side_size,0);
        }
        /*标签颜色*/
        int labelColor = typedArray.getColor(R.styleable.LabelImageView_label_color,0xbbbbbbbb);
        setLabelColor(labelColor);
        /*文字颜色*/
        int textColor = typedArray.getColor(R.styleable.LabelImageView_text_color,0xff000000);
        setTextColor(textColor);
        /*文字大小*/
        int textSize = (int)typedArray.getDimension(R.styleable.LabelImageView_text_size,0);
        setTextSize(textSize);
        mText = typedArray.getString(R.styleable.LabelImageView_text);
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.LTGRAY);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mLabelType == 0){
            drawBar(canvas,mText);
        }else {
            drawTriangle(canvas,mText);
        }
    }

    /**
     * 画条形标签
     * @param canvas
     * @param text  标签文字
     */
    private void drawBar(Canvas canvas,String text){
        canvas.save();
        /*获取view宽度*/
        int w = getWidth();
        /*如果用户没设置宽高则使用默认值*/
        if(mBarWidth==0 || mBarHeight==0){
            mBarWidth = w/4;
            mBarHeight = mBarWidth/2;
            mTextPaint.setTextSize(mBarHeight/2);
            mTextOffsetY = (mTextPaint.descent() + mTextPaint.ascent()) / 2;
        }
        /*矩形的四个点*/
        int rectLeft = w-mBarWidth;
        int rectTop = mBarTopMargin;
        int rectRight = w;
        int rectBottom = mBarTopMargin+mBarHeight;

        /*弧形的四个点*/
        float arcLeft = w-mBarWidth-mBarWidth/mBarRadius;
        float arcTop = mBarTopMargin;
        float arcRight = w-mBarWidth+mBarWidth/mBarRadius;
        float arcBottom = mBarTopMargin+mBarHeight;

        Rect rect = new Rect(rectLeft,rectTop,rectRight,rectBottom);
        /*画矩形*/
        canvas.drawRect(rect,mPaint);
        RectF rectf = new RectF(arcLeft,arcTop,arcRight,arcBottom);
        /*画弧形*/
        canvas.drawArc(rectf,90f,180f,true,mPaint);
        if(!TextUtils.isEmpty(text)){
            /*写文字*/
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, rect.centerX(),rect.centerY()-mTextOffsetY,mTextPaint);
        }
        canvas.restore();
    }

    /**
     * 画三角形标签
     * @param canvas
     * @param text  标签文字
     */
    private void drawTriangle(Canvas canvas,String text){
        canvas.save();
        int w = getWidth();
        Path path = new Path();
        /*三角形三个点*/
        path.moveTo(w,0);
        path.lineTo(w-mSideSize,0);
        path.lineTo(w,mSideSize);
        /*闭合路径使其成为一个三角形*/
        path.close();
        /*画三角形*/
        canvas.drawPath(path,mPaint);
        /*写文字*/
        canvas.drawText(text,w-mSideSize/2,mSideSize/2+mTextOffsetY,mTextPaint);
        canvas.restore();
    }

    public void setBarHeight(int mBarHeight) {
        this.mBarHeight = mBarHeight;
    }

    public void setBarWidth(int mBarWidth) {
        this.mBarWidth = mBarWidth;
    }

    public void setLabelColor(int color){
        mPaint.setColor(color);
    }

    public void setTextColor(int color){
        mTextPaint.setColor(color);
    }

    public void setText(String text){
        mText = text;
    }

    public void setTextSize(int size){
        mTextPaint.setTextSize(size);
        mTextOffsetY = (mTextPaint.descent() + mTextPaint.ascent()) / 2;
    }

    public void setBarTopMargin(int topMargin){
        mBarTopMargin = topMargin;
    }

    public void setSideSize(int mSideSize) {
        this.mSideSize = mSideSize;
    }

    public void setLabelType(int mLabelType) {
        this.mLabelType = mLabelType;
    }

    public void setBarRadius(int mBarRadius) {
        this.mBarRadius = mBarRadius;
    }
}
