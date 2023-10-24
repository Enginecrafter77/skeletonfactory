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

/**
 * SkeletonAdapter is a {@link RecyclerView.Adapter} implementation that displays skeleton children in the RecyclerView.
 * @author Enginecrafter77
 */
public class SkeletonAdapter extends RecyclerView.Adapter<SkeletonAdapter.SkeletonItem> {
	private final SkeletonFactory skeletonFactory;

	@LayoutRes
	private final int itemLayout;

	@IdRes
	private final int[] itemSkeletons;

	private int itemCount;

	/**
	 * Create a SkeletonAdapter using the given parameters.
	 * @param itemLayout The layout resource for children
	 * @param itemSkeletons The item IDs in the child layout that should be made into skeletons
	 * @param skeletonFactory The skeleton factory used to make the child skeletons
	 */
	public SkeletonAdapter(@LayoutRes int itemLayout, @IdRes int[] itemSkeletons, SkeletonFactory skeletonFactory)
	{
		this.skeletonFactory = skeletonFactory;
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
		return new SkeletonItem(root, this.itemSkeletons, this.skeletonFactory);
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

		public SkeletonItem(@NonNull View itemView, @IdRes int[] skeletonItems, SkeletonFactory factory)
		{
			super(itemView);
			List<Skeleton> skeletons = new ArrayList<Skeleton>(skeletonItems.length);
			for(@IdRes int id : skeletonItems)
			{
				View view = itemView.findViewById(id);
				Skeleton skeleton = factory.createSkeleton(view);
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
