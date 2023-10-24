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

import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;

public class ViewGroupChildIterator implements Iterator<View> {
	private final ViewGroup group;

	private int index;

	public ViewGroupChildIterator(ViewGroup group)
	{
		this.group = group;
		this.index = -1;
	}

	@Override
	public boolean hasNext()
	{
		return (this.index + 1) < this.group.getChildCount();
	}

	@Override
	public View next()
	{
		return this.group.getChildAt(++this.index);
	}

	public static Iterable<View> asIterable(ViewGroup group)
	{
		return () -> new ViewGroupChildIterator(group);
	}
}
