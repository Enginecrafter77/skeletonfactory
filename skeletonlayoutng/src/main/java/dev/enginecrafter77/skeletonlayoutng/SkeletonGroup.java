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

import com.google.common.collect.ImmutableSet;

public class SkeletonGroup implements Skeleton {
	private final ImmutableSet<Skeleton> skeletons;

	private boolean active;

	protected SkeletonGroup(ImmutableSet<Skeleton> skeletons)
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
			skeleton.hideSkeleton();
	}

	public static SkeletonGroup create(Iterable<Skeleton> skeletons)
	{
		SkeletonGroup group = new SkeletonGroup(ImmutableSet.copyOf(skeletons));
		group.sync();
		return group;
	}

	public static SkeletonGroup create(Skeleton... skeletons)
	{
		return SkeletonGroup.create(ImmutableSet.copyOf(skeletons));
	}
}
