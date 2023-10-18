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

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SkeletonAdapter extends RecyclerView.Adapter<SkeletonAdapter.SkeletonItem> {
	@LayoutRes
	private final int itemLayout;

	@IdRes
	private final int[] itemSkeletons;

	private int itemCount;

	public SkeletonAdapter(@LayoutRes int itemLayout, @IdRes int[] itemSkeletons)
	{
		this.itemLayout = itemLayout;
		this.itemSkeletons = itemSkeletons;
		this.itemCount = 3;
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setItemCount(int itemCount)
	{
		this.itemCount = itemCount;
		this.notifyDataSetChanged();
	}

	@NonNull
	@Override
	public SkeletonAdapter.SkeletonItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View root = LayoutInflater.from(parent.getContext()).inflate(this.itemLayout, parent, false);
		return new SkeletonItem(root, this.itemSkeletons);
	}

	@Override
	public void onBindViewHolder(@NonNull SkeletonAdapter.SkeletonItem holder, int position)
	{
		holder.getSkeletonGroup().showSkeleton();
	}

	@Override
	public int getItemCount()
	{
		return this.itemCount;
	}

	public static class SkeletonItem extends RecyclerView.ViewHolder {
		private final SkeletonGroup skeletonGroup;

		public SkeletonItem(@NonNull View itemView, @IdRes int[] skeletonItems)
		{
			super(itemView);
			List<Skeleton> skeletons = new ArrayList<Skeleton>(skeletonItems.length);
			for(@IdRes int id : skeletonItems)
			{
				View view = itemView.findViewById(id);
				Skeleton skeleton = SkeletonLayoutUtils.createSkeleton(view);
				skeletons.add(skeleton);
			}
			this.skeletonGroup = SkeletonGroup.create(skeletons);
		}

		public SkeletonGroup getSkeletonGroup()
		{
			return this.skeletonGroup;
		}
	}
}
