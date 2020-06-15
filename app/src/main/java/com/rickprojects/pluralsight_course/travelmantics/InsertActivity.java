package com.rickprojects.pluralsight_course.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase; //This is the entryPoint for Accessing the Firebase Realtime Database.
    private DatabaseReference mDatabaseReference; //location in the db where be read/write data.
    private EditText txtTitle;
    private EditText txtDescription;
    private EditText txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("traveldeals");

        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveDeal() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String price = txtPrice.getText().toString();

        TravelDeal deal = new TravelDeal(title, description, price, "");
        mDatabaseReference.push().setValue(deal); //Adds items to the Database.
    }

    private void clean() {
        txtTitle.setText("");
        txtDescription.setText("");
        txtPrice.setText("");

        txtTitle.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }
}