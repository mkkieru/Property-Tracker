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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPropertyActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mUploadImage;
    private EditText mPropertyName;
    private EditText mPropertyDescription;
    private EditText mPropertyPrice;

    private FirebaseFirestore db;
    private CollectionReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        mSubmit = findViewById(R.id.submit_button);
        mUploadImage = findViewById(R.id.upload_button);
        mPropertyName = findViewById(R.id.propertyName);
        mPropertyDescription = findViewById(R.id.propertyDescription);
        mPropertyPrice = findViewById(R.id.propertyPrice);


        db = FirebaseFirestore.getInstance();
        ref = db.getInstance().collection("properties");

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Property property = new Property(
                        mPropertyName.getText().toString(),
                        mPropertyDescription.getText().toString(),
                        mPropertyPrice.getText().toString());
                ref.add(property);

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