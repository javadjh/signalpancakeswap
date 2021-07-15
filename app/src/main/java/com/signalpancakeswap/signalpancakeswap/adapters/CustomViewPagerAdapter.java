package com.signalpancakeswap.signalpancakeswap.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.signalpancakeswap.signalpancakeswap.fragments.SendEmailFragment;
import com.signalpancakeswap.signalpancakeswap.fragments.SignalFragment;

public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
    public CustomViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //Active
                return new SendEmailFragment();
            case 1:
                //deActive
                return new SignalFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "کپی";
            case 1:
                return "سیگنال";
        }

        return super.getPageTitle(position);
    }


    @Override
    public int getCount() {
        return 2;
    }
}


