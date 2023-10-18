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

import android.content.Context;
import android.content.res.TypedArray;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import java.util.Objects;

public class SkeletonStyle {
	public static final SkeletonStyle DEFAULT = new SkeletonStyle(0xFFE0E0E0, 0xFFD5D5D5, 5F, 8F);

	@ColorInt
	public final int fillColor;

	@ColorInt
	public final int shimmerColor;

	public final float shimmerAngle;

	public final float borderRadius;

	public SkeletonStyle(int fillColor, int shimmerColor, float shimmerAngle, float borderRadius)
	{
		this.borderRadius = borderRadius;
		this.fillColor = fillColor;
		this.shimmerColor = shimmerColor;
		this.shimmerAngle = shimmerAngle;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.fillColor, this.shimmerColor, this.shimmerAngle);
	}

	@Override
	public boolean equals(@Nullable Object obj)
	{
		if(!(obj instanceof SkeletonStyle))
			return false;
		SkeletonStyle other = (SkeletonStyle)obj;
		return this.fillColor == other.fillColor && this.shimmerColor == other.shimmerColor && this.shimmerAngle == other.shimmerAngle;
	}

	public SkeletonStyleBuilder derive()
	{
		return new SkeletonStyleBuilder(this);
	}

	public static SkeletonStyleBuilder builder()
	{
		return SkeletonStyle.getDefault().derive();
	}

	public static SkeletonStyle getDefault()
	{
		return SkeletonStyle.DEFAULT;
	}

	public static SkeletonStyle fromContext(Context ctx)
	{
		SkeletonStyle style = SkeletonStyle.getDefault();
		SkeletonStyleBuilder builder = style.derive();

		TypedArray attrs = ctx.obtainStyledAttributes(R.styleable.SkeletonLayout);
		builder.withFillColor(attrs.getColor(R.styleable.SkeletonLayout_skeletonMaskColor, style.fillColor));
		builder.withShimmerColor(attrs.getColor(R.styleable.SkeletonLayout_skeletonShimmerColor, style.shimmerColor));
		builder.withShimmerAngle(attrs.getFloat(R.styleable.SkeletonLayout_skeletonShimmerAngle, style.shimmerAngle));
		builder.withBorderRadius(attrs.getDimensionPixelSize(R.styleable.SkeletonLayout_skeletonBorderRadius, (int)style.borderRadius));
		attrs.recycle();

		return builder.build();
	}

	public static class SkeletonStyleBuilder
	{
		private int fillColor;
		private int shimmerColor;
		private float shimmerAngle;
		private float borderRadius;

		public SkeletonStyleBuilder(SkeletonStyle from)
		{
			this.fillColor = from.fillColor;
			this.shimmerColor = from.shimmerColor;
			this.shimmerAngle = from.shimmerAngle;
			this.borderRadius = from.borderRadius;
		}

		public SkeletonStyleBuilder withFillColor(@ColorInt int fillColor)
		{
			this.fillColor = fillColor;
			return this;
		}

		public SkeletonStyleBuilder withShimmerColor(@ColorInt int shimmerColor)
		{
			this.shimmerColor = shimmerColor;
			return this;
		}

		public SkeletonStyleBuilder withShimmerAngle(@FloatRange(from = 0, to = 90) float shimmerAngle)
		{
			this.shimmerAngle = shimmerAngle;
			return this;
		}

		public SkeletonStyleBuilder withBorderRadius(float borderRadius)
		{
			this.borderRadius = borderRadius;
			return this;
		}

		public SkeletonStyle build()
		{
			return new SkeletonStyle(this.fillColor, this.shimmerColor, this.shimmerAngle, this.borderRadius);
		}
	}
}
