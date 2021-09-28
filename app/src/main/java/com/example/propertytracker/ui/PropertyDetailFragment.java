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
    @BindView(R.id.propertyImageView) ImageView mImageLabel;
    @BindView(R.id.propertyNameTextView) TextView mNameLabel;
    @BindView(R.id.descriptionTextView) TextView mDescription;
    @BindView(R.id.propertyPriceView) TextView mPrice;


//    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
//    @BindView(R.id.addressTextView) TextView mAddressLabel;



    private Property mProperty;

    public PropertyDetailFragment() {
        // Required empty public constructor
    }


    public static PropertyDetailFragment newInstance(Property Property) {
        PropertyDetailFragment restaurantDetailFragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("property", Parcels.wrap(Property));
        restaurantDetailFragment.setArguments(args);
        return PropertyDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mProperty = Parcels.unwrap(getArguments().getParcelable("property"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_property_detail, container, false);
        ButterKnife.bind(this, view);


        mNameLabel.setText( mProperty.getTitle());
        mDescription.setText(mDescription.getDescription());
        mPrice.setText(mPrice.getPrice().toString());

        return view;
    }
}