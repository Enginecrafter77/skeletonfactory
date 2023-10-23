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

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkeletonFactory {
	public static DetachableSkeleton createSkeleton(View view)
	{
		SkeletonOverlay overlay = new SkeletonOverlay(view);
		overlay.setStyle(SkeletonStyle.fromContext(view.getContext()));
		overlay.install();
		return overlay;
	}

	public static Skeleton createSkeletonGroup(View... views)
	{
		return SkeletonGroup.create(Stream.of(views).map(SkeletonFactory::createSkeleton).collect(Collectors.toSet()));
	}

	public static Skeleton createRecyclerViewSkeleton(RecyclerView recyclerView, int count, @LayoutRes int itemLayout, @IdRes int... itemSkeletons)
	{
		SkeletonAdapter adapter = new SkeletonAdapter(itemLayout, itemSkeletons);
		adapter.setItemCount(count);
		return new RecyclerViewSkeleton(recyclerView, adapter);
	}
}
