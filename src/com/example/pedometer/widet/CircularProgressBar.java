package com.example.pedometer.widet;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.view.View;

public class CircularProgressBar extends View {

	private int mDuration = 100;
	private int mProgress = 30;
	// private float mRatio = 0;

	// BarAnimation ani;

	private Paint mPaint = new Paint();
	private RectF mRectF = new RectF();

	private int mBackgroundColor = Color.LTGRAY;
	private int mPrimaryColor = Color.parseColor("#6DCAEC");
	private float mStrokeWidth = 30F;

	public interface onProgressChangeListener {
		public void onChange(int duration, int progress, float rate);
	}

	private onProgressChangeListener monChangeListener;

	public void setOnProgressChangeListener(onProgressChangeListener l) {
		monChangeListener = l;
	}

	public CircularProgressBar(Context context) {
		super(context);
		// ani = new BarAnimation();
		// ani.setDuration(2000);
	}

	public void setMax(int max) {
		if (max < 0) {
			max = 0;
		}
		mDuration = max;
	}

	public void setProgress(int progress) {
		if (progress > mDuration) {
			progress = mDuration;
		}
		mProgress = progress;
		if (monChangeListener != null) {
			monChangeListener
					.onChange(mDuration, progress, getRateOfProgress());
		}
		invalidate();
	}

	public int getmProgress() {
		return mProgress;
	}

	public void setCircleWidth(float width) {
		mStrokeWidth = width;

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int halfHeight = getHeight() / 2;
		int halfWidth = getWidth() / 2;
		int radius = halfHeight < halfWidth ? halfHeight : halfWidth;
		float halfStrokeWidth = mStrokeWidth / 2;

		mPaint.setColor(mBackgroundColor);
		mPaint.setDither(true);
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStrokeWidth(mStrokeWidth);
		mPaint.setStyle(Paint.Style.STROKE);



		canvas.drawCircle(halfWidth, halfHeight, radius - halfStrokeWidth,
				mPaint);

		mPaint.setColor(mPrimaryColor); // 改变画笔颜色
		mRectF.top = halfHeight - radius + halfStrokeWidth;
		mRectF.bottom = halfHeight + radius - halfStrokeWidth;
		mRectF.left = halfWidth - radius + halfStrokeWidth;
		mRectF.right = halfWidth + radius - halfStrokeWidth;
		// 画圆弧，第二个参数为：起始角度，第三个为跨的角度，第四个为true的时候是实心，false的时候为空心
		
		canvas.drawArc(mRectF, -90, getRateOfProgress() * 360, false, mPaint);
		
		canvas.save();
	}

	private float getRateOfProgress() {
		return (float) mProgress / mDuration;
	}

	// public class BarAnimation extends Animation{
	// @Override
	// protected void applyTransformation(float interpolatedTime,
	// Transformation t) {
	// super.applyTransformation(interpolatedTime, t);
	// if (interpolatedTime < 1.0f) {
	// mRatio = getRateOfProgress() * 360 * interpolatedTime;
	// }else {
	// mRatio = getRateOfProgress() * 360;
	// }
	// postInvalidate();
	//
	// }
	// }

}
