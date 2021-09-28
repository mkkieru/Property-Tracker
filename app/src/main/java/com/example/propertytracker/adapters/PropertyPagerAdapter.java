package com.example.propertytracker.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.propertytracker.models.Property;
import com.example.propertytracker.ui.PropertyDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class PropertyPagerAdapter extends FragmentPagerAdapter {
    private List<Property> mProperty;

    public PropertyPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Property> properties) {
        super(fm, behavior);
        mProperty =properties;
    }

    @Override
    public Fragment getItem(int position) {
        return PropertyDetailFragment.newInstance(mProperty.get(position));
    }

    @Override
    public int getCount() {
        return mProperty .size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mProperty .get(position).getTitle();
    }
}