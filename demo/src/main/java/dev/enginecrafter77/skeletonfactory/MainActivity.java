package dev.enginecrafter77.skeletonfactory;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import dev.enginecrafter77.skeletonfactory.demo.R;
import dev.enginecrafter77.skeletonfactory.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	private Skeleton skeleton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
		this.setContentView(this.binding.getRoot());

		this.binding.skeletonSwitch.setOnCheckedChangeListener(this::onToggleSwitched);

		TestAdapter adapter = new TestAdapter(10);
		this.binding.testList.setAdapter(adapter);

		Skeleton textSkeleton = DefaultSkeletonFactory.createSkeleton(this.binding.textField);
		Skeleton groupSkeleton = this.binding.skeletonLayout;
		Skeleton list = DefaultSkeletonFactory.createRecyclerViewSkeleton(this.binding.testList, 3, R.layout.test_item, R.id.icon, R.id.item_list);

		this.skeleton = SkeletonGroup.create(textSkeleton, groupSkeleton, list);
		this.skeleton.hideSkeleton();
	}

	@UiThread
	protected void onToggleSwitched(View view, boolean state)
	{
		if(state)
		{
			this.skeleton.showSkeleton();
		}
		else
		{
			this.skeleton.hideSkeleton();
		}
	}
}
