package dev.enginecrafter77.skeletonlayoutng;

import java.util.Arrays;
import java.util.List;

public class SkeletonGroup implements Skeleton {
	private final List<Skeleton> skeletons;

	private boolean active;

	protected SkeletonGroup(List<Skeleton> skeletons)
	{
		this.skeletons = skeletons;
		this.active = false;
	}

	protected void sync()
	{
		int numActive = 0;
		int numInactive = 0;

		for(Skeleton member : this.skeletons)
		{
			if(member.isActive())
				++numActive;
			else
				++numInactive;
		}

		boolean active = numActive >= numInactive;
		if(active)
			this.showSkeleton();
		else
			this.hideSkeleton();
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
		for(Skeleton skeleton : this.skeletons)
			skeleton.showSkeleton();
	}

	@Override
	public void hideSkeleton()
	{
		this.active = false;
		for(Skeleton skeleton : this.skeletons)
			skeleton.showSkeleton();
	}

	public static SkeletonGroup create(List<Skeleton> skeletons)
	{
		SkeletonGroup group = new SkeletonGroup(skeletons);
		group.sync();
		return group;
	}

	public static SkeletonGroup create(Skeleton... skeletons)
	{
		return SkeletonGroup.create(Arrays.asList(skeletons));
	}
}
