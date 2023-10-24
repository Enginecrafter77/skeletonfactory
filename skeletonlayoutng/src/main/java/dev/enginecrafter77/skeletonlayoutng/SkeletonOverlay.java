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

import androidx.annotation.UiThread;

public class SkeletonOverlay extends SkeletonDrawable implements DetachableSkeleton {
	private final View.OnLayoutChangeListener onLayoutChangeListener;

	private final View view;

	private boolean installed;

	public SkeletonOverlay(View view)
	{
		super();
		this.view = view;
		this.onLayoutChangeListener = this::onViewLayoutChange;
		this.installed = false;
	}

	protected synchronized void install()
	{
		if(this.installed)
			return;

		this.view.getOverlay().add(this);
		this.view.addOnLayoutChangeListener(this.onLayoutChangeListener);
		this.installed = true;
	}

	@UiThread
	protected void onViewLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
	{
		this.setBounds(left, top, right, bottom);
	}

	@Override
	public synchronized void detachSkeleton()
	{
		if(!this.installed)
			return;

		this.view.getOverlay().remove(this);
		this.view.removeOnLayoutChangeListener(this.onLayoutChangeListener);
		this.installed = false;
	}
}
