package dev.enginecrafter77.skeletonlayoutng;

import android.graphics.Canvas;

import androidx.annotation.NonNull;

public class SkeletonDrawable extends SkeletonBaseDrawable implements Skeleton {
	private boolean active;

	public SkeletonDrawable(boolean active)
	{
		this.active = active;
	}

	public SkeletonDrawable()
	{
		this(true);
	}

	@Override
	public void draw(@NonNull Canvas canvas)
	{
		if(!this.active)
			return;
		super.draw(canvas);
	}

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public void showSkeleton()
	{
		this.active = true;
		this.invalidateSelf();
	}

	@Override
	public void hideSkeleton()
	{
		this.active = false;
		this.invalidateSelf();
	}
}
