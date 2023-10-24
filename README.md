# SkeletonFactory

SkeletonFactory is a simple library for creating android skeletons inspired by [Faltenreich's SkeletonLayout library](https://github.com/Faltenreich/SkeletonLayout).
In contrast with Faltenreich's SkeletonLayout library, this library uses the ViewOverlay mechanism instead of strictly relying on view hierarchy.
This allows displaying skeletons without touching the view hierarchies of some potentially very delicate layouts (such as ConstraintLayout).

## Getting started

First, you need to import the library into your project.
```groovy
repositories {
    maven { url 'https://maven.enginecrafter77.dev/general' }
}

dependencies {
    implementation 'dev.enginecrafter77.skeletonfactory:skeletonfactory:<version>'
}
```
The version corresponds with git tags.

Then, it's time to create some skeletons.
```java
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

        // Create a skeleton covering single view
        Skeleton textSkeleton = DefaultSkeletonFactory.createSkeleton(this.binding.textField);
        
        // When using SkeletonLayout as parent (inherits from FrameLayout), you can bind to that directly
        Skeleton groupSkeleton = this.binding.skeletonLayout;
        
        // Create a skeleton in RecyclerView.
        // You need to supply the RecyclerView instance, then the number of parameters, and the item layout resource followed by IDs of items you wish to become skeletons.
        Skeleton list = DefaultSkeletonFactory.createRecyclerViewSkeleton(this.binding.testList, 3, R.layout.test_item, R.id.icon, R.id.item_list);

        // Group skeletons together using SkeletonGroup
        this.skeleton = SkeletonGroup.create(textSkeleton, groupSkeleton, list);
        
        // Alternatively, you can create SkeletonGroup from Views directly (for convenience)
        //DefaultSkeletonFactory.createSkeletonGroup(view1, view2, view3, ...);
        
        // At the time of creation, the state of skeletons is undefined, so we explicitly hide them
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
```

Additionally, to configure the skeletons, you can set the following attributes in your theme.
```xml
<!-- Default values are shown here -->
<style name="Theme.SkeletonFactory" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
    <item name="skeletonMaskColor">#E0E0E0</item>
    <item name="skeletonShimmerColor">#D5D5D5</item>
    <item name="skeletonShimmerAngle">5</item>
    <item name="skeletonBorderRadius">8</item>
    <item name="skeletonShimmerPeriodMs">1500</item>
</style>
```

## Credits
 - [Faltenreich](https://github.com/Faltenreich) for inspiration