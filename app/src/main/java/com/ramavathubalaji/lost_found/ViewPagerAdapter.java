package com.ramavathubalaji.lost_found;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAMAVATHU BALAJI on 13-06-2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> titlesList=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titlesList.size();
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return titlesList.get(position);
    }
    public void addFragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titlesList.add(title);
    }
}
