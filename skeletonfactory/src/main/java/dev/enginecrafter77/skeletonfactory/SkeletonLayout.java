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
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>SkeletonLayout is a simple {@link FrameLayout} with an implicitly constructed {@link Skeleton}.</p>
 *
 * <p>
 *     SkeletonLayout can be used as an XML-explicit declaration of skeleton covering a view. This instance
 *     can then directly be used to control the skeleton covering the view.
 * </p>
 *
 * <p>
 *     Upon construction, the skeleton state is undefined. Therefore, it is advised to call either
 *     {@link #showSkeleton()} or {@link #hideSkeleton()} before the view gets displayed.
 * </p>
 * @author Enginecrafter77
 */
public class SkeletonLayout extends FrameLayout implements Skeleton {
	private final SkeletonDrawable skeletonDrawable;
	private final Handler handler;

	public SkeletonLayout(@NonNull Context context)
	{
		this(context, null);
	}

	public SkeletonLayout(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		this.handler = new Handler(Looper.getMainLooper());
		this.skeletonDrawable = new SkeletonDrawable();
		this.getOverlay().add(this.skeletonDrawable);

		this.skeletonDrawable.setStyle(SkeletonStyle.fromContext(context));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		this.skeletonDrawable.setBounds(left, top, right, bottom);
	}

	@Override
	public boolean isActive()
	{
		return this.skeletonDrawable.isActive();
	}

	@Override
	public void showSkeleton()
	{
		this.handler.post(() -> {
			ViewGroupChildIterator.asIterable(this).forEach((View view) -> view.setVisibility(View.INVISIBLE));
			this.skeletonDrawable.showSkeleton();
		});
	}

	@Override
	public void hideSkeleton()
	{
		this.handler.post(() -> {
			ViewGroupChildIterator.asIterable(this).forEach((View view) -> view.setVisibility(View.VISIBLE));
			this.skeletonDrawable.hideSkeleton();
		});
	}
}
