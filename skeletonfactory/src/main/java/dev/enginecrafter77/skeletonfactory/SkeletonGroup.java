/*
 * This file is part of skeletonfactory.
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
package dev.enginecrafter77.skeletonfactory;

import com.google.common.collect.ImmutableSet;

/**
 * <p>
 *     SkeletonGroup is a {@link Skeleton} implementation that is used to
 *     synchronize the Skeleton behavior of one or more other skeleton instances.
 * </p>
 *
 * <p>
 *     As is case with other Skeleton implementations, upon group formation the
 *     state of its constituents is undefined, and therefore the state of the
 *     whole group is undefined as well. The value returned by {@link #isActive()}
 *     should not be relied upon. Only after a call to {@link #showSkeleton()}
 *     or {@link #hideSkeleton()} can the state be considered defined, and {@link #isActive()}
 *     will return the expected result.
 * </p>
 * @author Enginecrafter77
 */
public class SkeletonGroup implements Skeleton {
	private final ImmutableSet<Skeleton> skeletons;

	private boolean active;

	public SkeletonGroup(ImmutableSet<Skeleton> skeletons)
	{
		this.skeletons = skeletons;
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
		return new SkeletonGroup(ImmutableSet.copyOf(skeletons));
	}

	public static SkeletonGroup create(Skeleton... skeletons)
	{
		return SkeletonGroup.create(ImmutableSet.copyOf(skeletons));
	}
}
