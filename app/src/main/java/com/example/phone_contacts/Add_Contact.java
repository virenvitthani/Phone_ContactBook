package com.example.phone_contacts;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.phone_contacts.databinding.ActivityAddContactBinding;
import com.example.phone_contacts.databinding.ActivityMainBinding;

public class Add_Contact extends AppCompatActivity {

    ActivityAddContactBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new Database(Add_Contact.this);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.addContact(binding.contactName.getText().toString(),binding.contactNumber.getText().toString());
            }
        });
    }
}