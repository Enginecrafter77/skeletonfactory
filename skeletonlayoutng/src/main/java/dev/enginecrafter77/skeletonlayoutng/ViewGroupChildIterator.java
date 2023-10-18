package dev.enginecrafter77.skeletonlayoutng;

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
