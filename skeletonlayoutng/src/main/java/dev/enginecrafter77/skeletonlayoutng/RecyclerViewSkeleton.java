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
