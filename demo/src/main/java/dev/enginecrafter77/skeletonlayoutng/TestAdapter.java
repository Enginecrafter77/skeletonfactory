package dev.enginecrafter77.skeletonlayoutng;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.enginecrafter77.skeletonlayoutng.demo.R;
import dev.enginecrafter77.skeletonlayoutng.demo.databinding.TestItemBinding;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
	private final int count;

	public TestAdapter(int count)
	{
		this.count = count;
	}

	@NonNull
	@Override
	public TestAdapter.TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		TestItemBinding binding = TestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
		return new TestViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(@NonNull TestAdapter.TestViewHolder holder, int position)
	{
		holder.load(position);
	}

	@Override
	public int getItemCount()
	{
		return this.count;
	}

	public static class TestViewHolder extends RecyclerView.ViewHolder {
		private final TestItemBinding binding;

		public TestViewHolder(@NonNull TestItemBinding binding)
		{
			super(binding.getRoot());
			this.binding = binding;
		}

		@SuppressLint("SetTextI18n")
		public void load(int position)
		{
			this.binding.icon.setImageResource(R.drawable.ic_launcher_background);
			this.binding.itemList.setText(Integer.toString(position));
		}
	}
}
