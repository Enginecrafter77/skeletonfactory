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

import android.content.Context;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>SkeletonFactory is a class that creates skeletons for various elements (maily Views).</p>
 *
 * <p>
 *     The main goal of an abstract SkeletonFactory is that it can be supplied with user-specified {@link SkeletonStyle},
 *     which is subsequently passed down onto the children (as is case in {@link SkeletonAdapter} for example).
 * </p>
 * @author Enginecrafter77
 */
public class SkeletonFactory {
	@Nullable
	private final SkeletonStyle style;

	public SkeletonFactory(@Nullable SkeletonStyle style)
	{
		this.style = style;
	}

	protected SkeletonStyle getSkeletonStyleInContext(Context context)
	{
		if(this.style != null)
			return this.style;
		return SkeletonStyle.fromContext(context);
	}

	public DetachableSkeleton createSkeleton(View view)
	{
		SkeletonOverlay overlay = new SkeletonOverlay(view);
		overlay.setStyle(this.getSkeletonStyleInContext(view.getContext()));
		overlay.install();
		return overlay;
	}

	public Skeleton createSkeletonGroup(View... views)
	{
		return SkeletonGroup.create(Stream.of(views).map(this::createSkeleton).collect(Collectors.toSet()));
	}

	public Skeleton createRecyclerViewSkeleton(RecyclerView recyclerView, int count, @LayoutRes int itemLayout, @IdRes int... itemSkeletons)
	{
		SkeletonAdapter adapter = new SkeletonAdapter(itemLayout, itemSkeletons, this);
		adapter.setItemCount(count);
		return new RecyclerViewSkeleton(recyclerView, adapter);
	}

	private static final SkeletonFactory DEFAULT = new SkeletonFactory(null);
	public static SkeletonFactory getDefault()
	{
		return SkeletonFactory.DEFAULT;
	}

	public static SkeletonFactory withStyle(SkeletonStyle style)
	{
		return new SkeletonFactory(style);
	}
}
