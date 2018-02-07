package com.rahul.servicewale;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rahul.servicewale.fragments.ExternalDetailing;
import com.rahul.servicewale.fragments.InteranlDetailing;
import com.rahul.servicewale.fragments.Tab3;

public class CarDeatailingActivity extends AppCompatActivity {



    private TabLayout tabLayout;
    private ViewPager viewPager;


    ExternalDetailing externalDetailing;
    InteranlDetailing interanlDetailing;
    Tab3 tab3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_deatailing);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
      //  viewPager.setOffscreenPageLimit(3);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }



    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        externalDetailing = new ExternalDetailing();
         interanlDetailing = new InteranlDetailing();
        tab3 = new Tab3();
        adapter.addFragment(externalDetailing,"External");
        adapter.addFragment(interanlDetailing,"Internal");
        adapter.addFragment(tab3,"Tab3");
        viewPager.setAdapter(adapter);
    }
}
