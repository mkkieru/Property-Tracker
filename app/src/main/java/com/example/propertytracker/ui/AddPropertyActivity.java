package com.example.propertytracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.propertytracker.R;
import com.example.propertytracker.models.Property;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPropertyActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mUploadImage;
    private EditText mPropertyName;
    private EditText mPropertyDescription;
    private EditText mPropertyPrice;

    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        mSubmit = findViewById(R.id.submit_button);
        mUploadImage = findViewById(R.id.upload_button);
        mPropertyName = findViewById(R.id.propertyName);
        mPropertyDescription = findViewById(R.id.propertyDescription);
        mPropertyPrice = findViewById(R.id.propertyPrice);


        db = FirebaseDatabase.getInstance();
        ref = db.getReference();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Property property = new Property(
                        mPropertyName.getText().toString(),
                        mPropertyDescription.getText().toString(),
                        mPropertyPrice.getText().toString());
                ref.child("properties").push().setValue(property);

            }
        });

        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}