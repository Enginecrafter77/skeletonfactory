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
			skeleton.hideSkeleton();
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
