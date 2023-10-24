package dev.enginecrafter77.skeletonlayoutng;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A static facade for {@link SkeletonFactory#getDefault()}.
 * Any static call {@code DefaultSkeletonFactory.XYZ()} is equivalent to calling {@code SkeletonFactory.getDefault().XYZ()}.
 * Mostly used for convenience.
 * @author Enginecrafter77
 */
public class DefaultSkeletonFactory {
	public static DetachableSkeleton createSkeleton(View view)
	{
		return SkeletonFactory.getDefault().createSkeleton(view);
	}

	public static Skeleton createSkeletonGroup(View... views)
	{
		return SkeletonFactory.getDefault().createSkeletonGroup(views);
	}

	public static Skeleton createRecyclerViewSkeleton(RecyclerView recyclerView, int count, @LayoutRes int itemLayout, @IdRes int... itemSkeletons)
	{
		return SkeletonFactory.getDefault().createRecyclerViewSkeleton(recyclerView, count, itemLayout, itemSkeletons);
	}
}
