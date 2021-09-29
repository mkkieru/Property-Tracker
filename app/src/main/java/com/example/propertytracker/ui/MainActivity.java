package com.example.propertytracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertytracker.R;
import com.example.propertytracker.adapters.PropertyListAdapter;
import com.example.propertytracker.models.Property;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    final String TAG = "tag";
    private Button addNewProperty;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;



    PropertyListAdapter mAdapter;

    Property property1 = new Property("Beach Side Villa","Mombasa","10000");
    Property property2 = new Property("Pent House","Nairobi Upper Hill","10000");
    Property property3 = new Property("Pent House","Kisumu","10000");
    Property property4 = new Property("Waterfall view","Nyahururu","10000");
    Property property5 = new Property("Beach Side Villa","Mombasa","10000");
    Property property7 = new Property("Beach Side Villa","Mombasa","10000");

    Property[] properties = {property1,property2,property3,property4,property5,property7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new PropertyListAdapter(MainActivity.this, Arrays.asList(properties));
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setVisibility(View.VISIBLE);


        addNewProperty = findViewById(R.id.new_property_btn);
        addNewProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                adminAddProperty(userId);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private void adminAddProperty(String uid) {
        DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userLevel = documentSnapshot.getString("UserLevel");
                if ( userLevel.equals("admin")) {
                    startActivity(new Intent(getApplicationContext(),AddPropertyActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Please Contact Admin to be able to add property",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}