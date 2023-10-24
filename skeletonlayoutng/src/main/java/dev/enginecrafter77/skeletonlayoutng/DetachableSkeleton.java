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

/**
 * DetachableSkeleton is a simple extension to {@link Skeleton} allowing it to be completely
 * uninstalled from the associated element.
 * @author Enginecrafter77
 */
public interface DetachableSkeleton extends Skeleton {
	/**
	 * <p>Detaches the skeleton from the associated element.</p>
	 *
	 * <p>
	 *     Upon call to this method, {@link #showSkeleton()} and {@link #hideSkeleton()} methods
	 *     should become NO-OP, and the result of {@link #isActive()} becomes undefined.
	 * </p>
	 *
	 * <p>This method is idempotent. Subsequent calls to this method should be NO-OP.</p>
	 */
	public void detachSkeleton();
}
