package com.example.propertytracker.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.propertytracker.Constants;
import com.example.propertytracker.R;
import com.example.propertytracker.models.Property;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPropertyActivity extends AppCompatActivity {
    private Button mSubmit;
    private Button mUploadImage;
    private EditText mPropertyName;
    private EditText mPropertyDescription;
    private EditText mPropertyPrice;


    private FirebaseFirestore db;
    private CollectionReference ref;

    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 11;;
    private String currentPhotoPath;

    @BindView(R.id.propertyImageView) ImageView mImageLabel;

    private static final String TAG = "image creation value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        mSubmit = findViewById(R.id.submit_button);
        mUploadImage = findViewById(R.id.upload_button);
        mPropertyName = findViewById(R.id.propertyName);
        mPropertyDescription = findViewById(R.id.propertyDescription);
        mPropertyPrice = findViewById(R.id.propertyPrice);
        ButterKnife.bind(this);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveProperty();
            }
        });

        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                //onLaunchCamera();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AddPropertyActivity.this.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageLabel.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }


    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        Log.d("ImageEncoded", imageEncoded);

        db = FirebaseFirestore.getInstance();
        ref = FirebaseFirestore.getInstance().collection("properties");
        String newDocumentId = ref.document().getId();

        if (!(mPropertyName == null) && !(mPropertyDescription == null) && !(mPropertyPrice == null )) {

            Property newProperty = new Property(
                    mPropertyName.getText().toString(),
                    mPropertyDescription.getText().toString(),
                    mPropertyPrice.getText().toString(),
                    imageEncoded);
            newProperty.setId(newDocumentId);
            ref.document(newDocumentId).set(newProperty).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AddPropertyActivity.this, "Property Saved", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddPropertyActivity.this, MainActivity.class));
                    finish();

                }
            });
        }
        else {
            Toast.makeText(AddPropertyActivity.this, "Please make sure all entries are made ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
/*
    public void saveProperty() {
        db = FirebaseFirestore.getInstance();
        ref = FirebaseFirestore.getInstance().collection("properties");
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

 */

}