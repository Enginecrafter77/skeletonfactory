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

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A static facade for {@link SkeletonFactory#getDefault()}.
 * Any static call {@code DefaultSkeletonFactory.XYZ()} is equivalent to calling {@code SkeletonFactory.getDefault().XYZ()}.
 * Mostly used for convenience.
 * @author Enginecrafter77
 */
public class DefaultSkeletonFactory {
	public static DetachableSkeleton createSkeleton(View view)
	{
		return SkeletonFactory.getDefault().createSkeleton(view);
	}

	public static Skeleton createSkeletonGroup(View... views)
	{
		return SkeletonFactory.getDefault().createSkeletonGroup(views);
	}

	public static Skeleton createRecyclerViewSkeleton(RecyclerView recyclerView, int count, @LayoutRes int itemLayout, @IdRes int... itemSkeletons)
	{
		return SkeletonFactory.getDefault().createRecyclerViewSkeleton(recyclerView, count, itemLayout, itemSkeletons);
	}
}
