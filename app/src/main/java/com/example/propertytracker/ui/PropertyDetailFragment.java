package com.example.propertytracker.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.propertytracker.R;
import com.example.propertytracker.models.Property;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyDetailFragment extends Fragment {
    @BindView(R.id.propertyimage) ImageView mPropertyView;
    @BindView(R.id.propertyLocation) TextView mPropertyLocationView;
    @BindView(R.id.propertyTitle) TextView mPropertyTitleView;

    private Property mProperty;

    public  PropertyDetailFragment() {

    }
    public  static  PropertyDetailFragment newInstance(Property property){
        PropertyDetailFragment propertyDetailFragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("property", Parcels.wrap(property));
        propertyDetailFragment.setArguments(args);
        return  propertyDetailFragment;
    }

    @Override
    public  void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        assert  getArguments() != null;
        mProperty = Parcels.unwrap(getArguments().getParcelable("property"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_property_detail, container,false);
        ButterKnife.bind(this, view);
        mPropertyLocationView.setText(mProperty.getDescription());
        mPropertyTitleView.setText(mProperty.getTitle());
        return  view;
    }
}