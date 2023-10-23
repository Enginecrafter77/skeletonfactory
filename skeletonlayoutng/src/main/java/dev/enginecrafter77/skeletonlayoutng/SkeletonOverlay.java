package dev.enginecrafter77.skeletonlayoutng;

import android.view.View;

import androidx.annotation.UiThread;

public class SkeletonOverlay extends SkeletonDrawable implements DetachableSkeleton {
	private final View.OnLayoutChangeListener onLayoutChangeListener;

	private final View view;

	public SkeletonOverlay(View view)
	{
		super();
		this.view = view;
		this.onLayoutChangeListener = this::onViewLayoutChange;
	}

	protected void install()
	{
		this.view.getOverlay().add(this);
		this.view.addOnLayoutChangeListener(this.onLayoutChangeListener);
	}

	@UiThread
	protected void onViewLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
	{
		this.setBounds(left, top, right, bottom);
	}

	@Override
	public void detachSkeleton()
	{
		this.view.getOverlay().remove(this);
		this.view.removeOnLayoutChangeListener(this.onLayoutChangeListener);
	}
}
