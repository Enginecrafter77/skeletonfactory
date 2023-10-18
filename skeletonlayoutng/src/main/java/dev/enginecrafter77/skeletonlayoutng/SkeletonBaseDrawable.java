package dev.enginecrafter77.skeletonlayoutng;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SkeletonBaseDrawable extends Drawable {
	private final Paint paint;

	private final RectF paintingArea;

	private float borderRadius;

	public SkeletonBaseDrawable()
	{
		this.borderRadius = 8F;
		this.paintingArea = new RectF();

		this.paint = new Paint();
		this.paint.setColor(Color.GRAY);
		this.paint.setAntiAlias(true);
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

	@Override
	public void setBounds(int left, int top, int right, int bottom)
	{
		super.setBounds(left, top, right, bottom);
		this.paintingArea.set(left, top, right, bottom);
		this.paintingArea.offsetTo(0, 0);
		this.invalidateSelf();
	}

	@Override
	public void draw(@NonNull Canvas canvas)
	{
		canvas.drawRoundRect(this.paintingArea, this.borderRadius, this.borderRadius, this.paint);
	}

	@Override
	public void setAlpha(int i)
	{
		this.paint.setAlpha(i);
		this.invalidateSelf();
	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter)
	{
		this.paint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity()
	{
		return this.paint.getAlpha();
	}
}
