package dev.enginecrafter77.skeletonlayoutng;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SkeletonLayout extends FrameLayout implements Skeleton {
	private final SkeletonDrawable skeletonDrawable;

	public SkeletonLayout(@NonNull Context context)
	{
		this(context, null);
	}

	public SkeletonLayout(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		this.skeletonDrawable = new SkeletonDrawable();
		this.getOverlay().add(this.skeletonDrawable);

		this.skeletonDrawable.setStyle(SkeletonStyle.fromContext(context));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		this.skeletonDrawable.setBounds(left, top, right, bottom);
	}

	@Override
	public boolean isActive()
	{
		return this.skeletonDrawable.isActive();
	}

	@Override
	public void showSkeleton()
	{
		ViewGroupChildIterator.asIterable(this).forEach((View view) -> view.setVisibility(View.INVISIBLE));
		this.skeletonDrawable.showSkeleton();
	}

	@Override
	public void hideSkeleton()
	{
		ViewGroupChildIterator.asIterable(this).forEach((View view) -> view.setVisibility(View.VISIBLE));
		this.skeletonDrawable.hideSkeleton();
	}
}
