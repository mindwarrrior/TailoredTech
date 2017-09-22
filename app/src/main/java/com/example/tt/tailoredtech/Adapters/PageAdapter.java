package com.example.tt.tailoredtech.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tt.tailoredtech.Fragments.PageFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private String[] messages;

    public PageAdapter(FragmentManager fragmentManager, String[] messages) {
        super(fragmentManager);
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.length;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(messages[position]);
    }
}