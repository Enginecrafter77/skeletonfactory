package dev.enginecrafter77.skeletonlayoutng;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewSkeleton implements Skeleton {
	private final RecyclerView view;
	private final SkeletonAdapter skeletonAdapter;

	@Nullable
	private RecyclerView.Adapter<?> suppressed;
	private boolean active;

	public RecyclerViewSkeleton(RecyclerView view, SkeletonAdapter adapter)
	{
		this.view = view;
		this.skeletonAdapter = adapter;
		this.suppressed = null;
		this.active = false;
	}

	@Override
	public boolean isActive()
	{
		return this.active;
	}

	@Override
	public void showSkeleton()
	{
		if(this.active)
			return;

		this.suppressed = this.view.getAdapter();
		this.view.setAdapter(this.skeletonAdapter);
		this.active = true;
	}

	@Override
	public void hideSkeleton()
	{
		if(!this.active)
			return;

		this.view.setAdapter(this.suppressed);
		this.suppressed = null;
		this.active = false;
	}
}
