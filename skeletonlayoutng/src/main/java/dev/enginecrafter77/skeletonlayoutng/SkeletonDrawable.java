package dev.enginecrafter77.skeletonlayoutng;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SkeletonDrawable extends Drawable implements Skeleton {
	private final Matrix shimmerBoundMatrix;
	private final Matrix shimmerOutMatrix;
	private final ValueAnimator shimmerAnimator;
	private final Paint shimmerPaint;
	private final RectF paintingArea;
	private final Handler handler;

	private int fillColor;
	private int shimmerColor;

	private float borderRadius;

	private boolean active;

	public SkeletonDrawable()
	{
		this.handler = new Handler(Looper.getMainLooper());
		this.paintingArea = new RectF();
		this.shimmerBoundMatrix = new Matrix();
		this.shimmerOutMatrix = new Matrix();
		this.borderRadius = 8F;
		this.active = false;
		this.fillColor = 0xFF888888;
		this.shimmerColor = 0xFF999999;

		this.shimmerAnimator = ValueAnimator.ofFloat(-1.75F, 1.75F);
		this.shimmerAnimator.setInterpolator(new LinearInterpolator());
		this.shimmerAnimator.setRepeatMode(ValueAnimator.RESTART);
		this.shimmerAnimator.setRepeatCount(ValueAnimator.INFINITE);
		this.shimmerAnimator.setDuration(1500);
		this.shimmerAnimator.addUpdateListener(this::onAnimationUpdate);

		this.shimmerPaint = new Paint();
		this.shimmerPaint.setAntiAlias(true);
		this.shimmerPaint.setColor(Color.BLACK);

		this.recreateShader();
	}

	protected void onAnimationUpdate(@NonNull ValueAnimator animator)
	{
		this.invalidateSelf();
	}

	public void setBorderRadius(float borderRadius)
	{
		this.borderRadius = borderRadius;
		this.invalidateSelf();
	}

	public float getBorderRadius()
	{
		return this.borderRadius;
	}

	public void setFillColor(@ColorInt int color)
	{
		this.fillColor = color;
		this.recreateShader();
	}

	public void setShimmerColor(@ColorInt int shimmerColor)
	{
		this.shimmerColor = shimmerColor;
		this.recreateShader();
	}

	protected void recreateShader()
	{
		Shader shimmerShader = new LinearGradient(0F, 0.4F, 1F, 0.6F, new int[] {this.fillColor, this.shimmerColor, this.fillColor}, null, Shader.TileMode.CLAMP);
		this.shimmerPaint.setShader(shimmerShader);
		this.invalidateSelf();
	}

	@Override
	protected void onBoundsChange(@NonNull Rect bounds)
	{
		super.onBoundsChange(bounds);
		this.paintingArea.set(bounds);
		this.paintingArea.offsetTo(0, 0);

		this.shimmerBoundMatrix.reset();
		this.shimmerBoundMatrix.postScale(bounds.width(), bounds.height());
	}

	@Override
	public void draw(@NonNull Canvas canvas)
	{
		if(!this.active)
			return;

		float value = (float)this.shimmerAnimator.getAnimatedValue();
		float shift = value * this.paintingArea.width();

		this.shimmerOutMatrix.set(this.shimmerBoundMatrix);
		this.shimmerOutMatrix.postTranslate(shift, 0F);
		this.shimmerPaint.getShader().setLocalMatrix(this.shimmerOutMatrix);

		canvas.drawRoundRect(this.paintingArea, this.borderRadius, this.borderRadius, this.shimmerPaint);
	}

	@Override
	public void setAlpha(int i)
	{
		this.shimmerPaint.setAlpha(i);
		this.invalidateSelf();
	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter)
	{
		this.shimmerPaint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity()
	{
		return this.shimmerPaint.getAlpha();
	}

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public void showSkeleton()
	{
		this.handler.post(() -> {
			if(this.active)
				return;

			this.active = true;
			this.shimmerAnimator.start();
			this.invalidateSelf();
		});
	}

	@Override
	public void hideSkeleton()
	{
		this.handler.post(() -> {
			if(!this.active)
				return;

			this.active = false;
			this.shimmerAnimator.end();
			this.invalidateSelf();
		});
	}
}
