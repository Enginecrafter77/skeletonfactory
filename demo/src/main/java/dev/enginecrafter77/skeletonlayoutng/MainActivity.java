package dev.enginecrafter77.skeletonlayoutng;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import dev.enginecrafter77.skeletonlayoutng.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	private Skeleton textSkeleton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
		this.setContentView(this.binding.getRoot());

		this.binding.skeletonSwitch.setOnCheckedChangeListener(this::onToggleSwitched);

		this.textSkeleton = SkeletonLayoutUtils.createSkeleton(this.binding.textField);
		this.textSkeleton.hideSkeleton();
	}

	@UiThread
	protected void onToggleSwitched(View view, boolean state)
	{
		if(state)
		{
			this.textSkeleton.showSkeleton();
		}
		else
		{
			this.textSkeleton.hideSkeleton();
		}
	}
}
