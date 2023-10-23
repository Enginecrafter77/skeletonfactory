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

import androidx.annotation.AnyThread;

/**
 * </p>Skeleton is an interface implemented by all classes facilitating the skeleton behavior.</p>
 *
 * <p>
 *     In general, skeletons describe an element optimally with a captivating animation that captures
 *     the user's attention while the underlying element is getting ready to be displayed with all it's
 *     actual data. Skeletons should visually adhere to the View structure, so that given all skeletons are
 *     showing, the actual layout composed from the skeletons is visually similar in structure to the actual
 *     layout after all the skeletons are hidden. In simplest terms, a skeleton might just be a gray strip
 *     covering TextView while the data that should be displayed by the TextView is being loaded.
 * </p>
 *
 * <p>
 *     All skeletons should implement 3 methods. {@link #showSkeleton()}, {@link #hideSkeleton()} and {@link #isActive()}.
 *     These methods should be used to show the skeleton, hide it, and query it's current state respectively.
 * </p>
 *
 * <p>
 *     Upon skeleton creation, its state is undefined. Therefore, it is a good practice to call either {@link #showSkeleton()} or {@link #hideSkeleton()}
 *     before the associated screen is displayed to the user.
 * </p>
 * @author Enginecrafter77
 */
public interface Skeleton {
	/**
	 * <p>
	 *     This method updates the skeleton so that the associated visual element is no longer visible,
	 *     and instead of it there should be a "skeleton" showing (see the definition of skeleton in {@link Skeleton}).
	 *     Further calls to this method should be idempotent.
	 * </p>
	 *
	 * <p>
	 *     Implementations of this method must take into account that there is no guarantee as to from
	 *     which thread the call might originate. Therefore, it is highly advised to use {@link android.os.Handler}
	 *     for any thread-sensitive work.
	 * </p>
	 */
	@AnyThread
	public void showSkeleton();

	/**
	 * <p>
	 *     This method updates the skeleton so that the skeleton displayed over the visual element
	 *     is dismissed and the underlying visual element becomes visible once more.
	 *     Further calls to this method should be idempotent.
	 * </p>
	 *
	 * <p>
	 *     Implementations of this method must take into account that there is no guarantee as to from
	 *     which thread the call might originate. Therefore, it is highly advised to use {@link android.os.Handler}
	 *     for any thread-sensitive work.
	 * </p>
	 */
	@AnyThread
	public void hideSkeleton();

	/**
	 * Queries the state of the skeleton.
	 * @return True if the skeleton is visible, false otherwise.
	 */
	@AnyThread
	public boolean isActive();
}
