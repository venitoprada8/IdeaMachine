package hr.matvidako.ideamachine.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.IdeaListFragment;
import hr.matvidako.ideamachine.stats.StatsFragment;
import hr.matvidako.ideamachine.tag.TagListFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new IdeaListFragment(), getString(R.string.ideas));
        adapter.addFrag(new TagListFragment(), getString(R.string.tags));
        adapter.addFrag(new StatsFragment(), getString(R.string.stats));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
