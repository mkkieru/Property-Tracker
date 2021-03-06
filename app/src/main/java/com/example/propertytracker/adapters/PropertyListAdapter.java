package com.example.propertytracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.propertytracker.R;
import com.example.propertytracker.adapters.PropertyViewHolder;
import com.example.propertytracker.models.Property;
import com.example.propertytracker.ui.PropertyDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyViewHolder>{

    private List<Property> mProperties;
    private Context mContext;


    public PropertyListAdapter(Context Context , List<Property> mProperties){
        this.mProperties = mProperties;
        this.mContext = Context;
    }



    @NonNull
    @Override
    public PropertyListAdapter.PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_list_item,parent,false);
        PropertyViewHolder viewHolder = new PropertyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyListAdapter.PropertyViewHolder holder, int position) {
        holder.bindProperty(mProperties.get(position));
    }

    @Override
    public int getItemCount() {
        return mProperties.size();
    }

    public  class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.propertyimage) ImageView mPropertyView;
        @BindView(R.id.propertyLocation) TextView mPropertyLocationView;
        @BindView(R.id.propertyTitle) TextView mPropertyTitleView;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindProperty(Property property){
            mPropertyLocationView.setText(property.getDescription());
            mPropertyTitleView.setText(property.getTitle());
            // Add image
            Picasso.get().load(property.getImageUri()).into(mPropertyView);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PropertyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("properties", Parcels.wrap(mProperties));
            mContext.startActivity(intent);
        }

    }
}
