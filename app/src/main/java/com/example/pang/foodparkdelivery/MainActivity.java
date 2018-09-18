package com.example.pang.foodparkdelivery;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.example.pang.foodparkdelivery.Restaurant.FillFood;
import com.example.pang.foodparkdelivery.Restaurant.Home;
import com.example.pang.foodparkdelivery.Restaurant.ResLogin;
import com.example.pang.foodparkdelivery.Restaurant.profile;

public class MainActivity extends FragmentActivity {
    MyPageAdapter pageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  //full page not have title bar
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(new ResLogin());
        fList.add(new ResLogin());
        fList.add(new ResLogin());
        return fList;
    }



    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private int[] mResources;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments ) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position)
        {
            return this.fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return this.fragments.size();
        }
    }
}