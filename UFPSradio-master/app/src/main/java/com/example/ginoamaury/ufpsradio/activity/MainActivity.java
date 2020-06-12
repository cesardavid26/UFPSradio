package com.example.ginoamaury.ufpsradio.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.fragment.ProgramacionFragment;
import com.example.ginoamaury.ufpsradio.fragment.RadioFragment;
import com.example.ginoamaury.ufpsradio.fragment.ViewPagerAdapter;


/**
 * author carlos22ivan
 * author gino
 */
public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RadioFragment radioFragment;
    private ProgramacionFragment programacionFragment;
    private Context myContext;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myContext = this;
        inicializarFragments();
    }


    /**
     * incializa los fragments del activity
     */
    private void inicializarFragments() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        radioFragment = new RadioFragment();
        programacionFragment = new ProgramacionFragment();
        viewPagerAdapter.addFragment(radioFragment, "Radio");
        viewPagerAdapter.addFragment(programacionFragment, "Programacion");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.bringToFront();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

