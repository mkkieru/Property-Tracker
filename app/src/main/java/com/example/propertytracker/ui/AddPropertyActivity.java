package com.example.propertytracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.EventLogTags;
=======
import android.os.Bundle;
>>>>>>> 11fa4800d77e016eb735c10fa5a491d01bdb348b
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.propertytracker.R;
import com.example.propertytracker.models.Property;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddPropertyActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mUploadImage;
    private EditText mPropertyName;
    private EditText mPropertyDescription;
    private EditText mPropertyPrice;

    private FirebaseFirestore db;
    private CollectionReference ref;

    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        mSubmit = findViewById(R.id.submit_button);
        mUploadImage = findViewById(R.id.upload_button);
        mPropertyName = findViewById(R.id.propertyName);
        mPropertyDescription = findViewById(R.id.propertyDescription);
        mPropertyPrice = findViewById(R.id.propertyPrice);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProperty();
            }
        });

        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
<<<<<<< HEAD

=======
>>>>>>> 11fa4800d77e016eb735c10fa5a491d01bdb348b
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void saveProperty() {
        db = FirebaseFirestore.getInstance();
        ref = db.getInstance().collection("properties");
        String newDocumentId = ref.document().getId();
        Property newProperty = new Property(
                mPropertyName.getText().toString(),
                mPropertyDescription.getText().toString(),
                mPropertyPrice.getText().toString());
        newProperty.setId(newDocumentId);
        ref.document(newDocumentId).set(newProperty).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AddPropertyActivity.this, "Property Saved",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddPropertyActivity.this,MainActivity.class));
                finish();

            }
        });

    }
}