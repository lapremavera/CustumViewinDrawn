package com.example.draakje.customviewindrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.StringBuilderPrinter;
import android.view.View;


public class AndroidATCView extends View {
    private int mSquareColor, mLabelColor;
    private String mSquareText;
    private Paint mSquarePaint;

    public AndroidATCView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mSquarePaint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AndroitATCView, 0, 0);

        try {
            mSquareText = typedArray.getString(R.styleable.AndroitATCView_squareLabel);
            mSquareColor = typedArray.getInteger(R.styleable.AndroitATCView_squareColor, 0);
            mLabelColor = typedArray.getInteger(R.styleable.AndroitATCView_labelColor, 0);
        } finally {
            typedArray.recycle();
        }
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        if (!isInEditMode()) {

        mSquarePaint.setStyle(Paint.Style.FILL);
        mSquarePaint.setAntiAlias(true);

        mSquarePaint.setColor(mSquareColor);
        canvas.drawRect(0,0, this.getMeasuredWidth(), this.getMeasuredHeight(), mSquarePaint);
        mSquarePaint.setColor(mLabelColor);

        mSquarePaint.setTextAlign(Paint.Align.CENTER);
        mSquarePaint.setTextSize(50);
        canvas.drawText(mSquareText, this.getMeasuredWidth()/2, this.getMeasuredWidth()/2, mSquarePaint);
        }
    }

    public int getmSquareColor() {
        return mSquareColor;
    }

    public void setmSquareColor(int neweColor) {
        mSquareColor = neweColor;
        invalidate();
        requestLayout();
    }

    public int getmLabelColor() {
        return mLabelColor;
    }

    public void setmLabelColor(int newColor) {
        mLabelColor= newColor;
        invalidate();
        requestLayout();
    }

    public String getmSquareText() {
        return mSquareText;
    }

    public void setmSquareText(String newLabel) {
        mSquareText = newLabel;
        invalidate();
        requestLayout();
    }

    public Paint getmSquarePaint() {
        return mSquarePaint;
    }

    public void setmSquarePaint(Paint mSquarePaint) {
        this.mSquarePaint = mSquarePaint;
    }
}
