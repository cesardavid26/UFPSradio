package com.example.ginoamaury.ufpsradio.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.fragment.PodcastFragment;
import com.example.ginoamaury.ufpsradio.fragment.ProgramacionFragment;
import com.example.ginoamaury.ufpsradio.fragment.RadioFragment;
import com.example.ginoamaury.ufpsradio.fragment.ViewPagerAdapter;


/**
 * author carlos22ivan
 * author gino
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private RadioFragment radioFragment;
    private ProgramacionFragment programacionFragment;
    private Context myContext;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        myContext = this;
        inicializarFragments();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_podcast:
               getSupportFragmentManager().beginTransaction().replace(R.id.listRecyclerPodcast,
                       new PodcastFragment()).commit();
            break;

         //   case R.id.nav_podcast:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                  //      new PodcastFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}

