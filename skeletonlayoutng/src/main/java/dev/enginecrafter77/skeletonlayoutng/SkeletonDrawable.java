/*
 * This file is part of skeletonlayout-ng.
 * Copyright (c) 2023 Enginecrafter77 <hutiramichal@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>SkeletonDrawable is the main means of displaying skeletons.</p>
 *
 * <p>
 *     SkeletonDrawable takes the form of a simple strip with a running shimmer animation covering the whole element bounds.
 * </p>
 *
 * <p>
 *     SkeletonDrawable can be configured by using {@link #setStyle(SkeletonStyle)}.
 * </p>
 * @author Enginecrafter77
 */
public class SkeletonDrawable extends Drawable implements Skeleton {
	private final Matrix shimmerBoundMatrix;
	private final Matrix shimmerOutMatrix;
	private final ValueAnimator shimmerAnimator;
	private final Paint shimmerPaint;
	private final RectF paintingArea;
	private final Handler handler;

	private final Matrix shaderGradientRotationMatrix;
	private final RectF shaderGradientRectangle;

	private SkeletonStyle style;

	private boolean active;

	public SkeletonDrawable()
	{
		this.handler = new Handler(Looper.getMainLooper());
		this.shaderGradientRectangle = new RectF();
		this.shaderGradientRotationMatrix = new Matrix();
		this.paintingArea = new RectF();
		this.shimmerBoundMatrix = new Matrix();
		this.shimmerOutMatrix = new Matrix();
		this.style = SkeletonStyle.getDefault();
		this.active = false;

		this.shimmerAnimator = ValueAnimator.ofFloat(-1.75F, 1.75F);
		this.shimmerAnimator.setInterpolator(new LinearInterpolator());
		this.shimmerAnimator.setRepeatMode(ValueAnimator.RESTART);
		this.shimmerAnimator.setRepeatCount(ValueAnimator.INFINITE);
		this.shimmerAnimator.setDuration(this.style.shimmerPeriodMs);
		this.shimmerAnimator.addUpdateListener(this::onAnimationUpdate);

		this.shimmerPaint = new Paint();
		this.shimmerPaint.setAntiAlias(true);
		this.shimmerPaint.setColor(Color.BLACK);

		this.setStyle(SkeletonStyle.DEFAULT);
	}

	protected void onAnimationUpdate(@NonNull ValueAnimator animator)
	{
		this.invalidateSelf();
	}

	public SkeletonStyle getStyle()
	{
		return this.style;
	}

	public void setStyle(@NonNull SkeletonStyle style)
	{
		this.style = style;
		this.shimmerAnimator.setDuration(style.shimmerPeriodMs);
		this.recreateShader();
		this.invalidateSelf();
	}

	protected void recreateShader()
	{
		this.shaderGradientRectangle.set(0F, 0.5F, 1F, 0.5F);

		this.shaderGradientRotationMatrix.reset();
		this.shaderGradientRotationMatrix.preRotate(-this.style.shimmerAngle);
		this.shaderGradientRotationMatrix.mapRect(this.shaderGradientRectangle);

		Shader shimmerShader = new LinearGradient(this.shaderGradientRectangle.left, this.shaderGradientRectangle.top, this.shaderGradientRectangle.right, this.shaderGradientRectangle.bottom, new int[] {this.style.fillColor, this.style.shimmerColor, this.style.fillColor}, null, Shader.TileMode.CLAMP);
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

		canvas.drawRoundRect(this.paintingArea, this.style.borderRadius, this.style.borderRadius, this.shimmerPaint);
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
