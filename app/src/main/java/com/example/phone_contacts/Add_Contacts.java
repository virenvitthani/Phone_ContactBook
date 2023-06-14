package com.example.phone_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.phone_contacts.databinding.ActivityAddContactsBinding;
import java.util.ArrayList;

public class Add_Contacts extends AppCompatActivity {

    ActivityAddContactsBinding binding;
    Database database;
    ArrayList <String>  name = new ArrayList<>();
    ArrayList <String>  number = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new Database(Add_Contacts.this);
        binding = ActivityAddContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.addContacts(binding.contactsName.getText().toString(),binding.contactsNumber.getText().toString());
                Intent intent = new Intent(Add_Contacts.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}