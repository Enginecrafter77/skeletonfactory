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
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

public class SkeletonLayoutUtils {
	public static DetachableSkeleton createSkeleton(View view)
	{
		SkeletonOverlay overlay = new SkeletonOverlay(view);
		overlay.setStyle(SkeletonStyle.fromContext(view.getContext()));
		overlay.install();
		return overlay;
	}

	public static Skeleton applySkeleton(RecyclerView recyclerView, int count, @LayoutRes int itemLayout, @IdRes int... itemSkeletons)
	{
		SkeletonAdapter adapter = new SkeletonAdapter(itemLayout, itemSkeletons);
		adapter.setItemCount(count);
		return new RecyclerViewSkeleton(recyclerView, adapter);
	}

	private static class SkeletonOverlay extends SkeletonDrawable implements DetachableSkeleton
	{
		private final View.OnLayoutChangeListener onLayoutChangeListener;

		private final View view;

		public SkeletonOverlay(View view)
		{
			super();
			this.view = view;
			this.onLayoutChangeListener = this::onViewLayoutChange;
		}

		protected void install()
		{
			this.view.getOverlay().add(this);
			this.view.addOnLayoutChangeListener(this.onLayoutChangeListener);
		}

		@UiThread
		protected void onViewLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
		{
			this.setBounds(left, top, right, bottom);
		}

		@Override
		public void detachSkeleton()
		{
			this.view.getOverlay().remove(this);
			this.view.removeOnLayoutChangeListener(this.onLayoutChangeListener);
		}
	}
}
