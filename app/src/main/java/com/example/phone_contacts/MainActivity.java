package com.example.phone_contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.phone_contacts.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;

    ActivityMainBinding binding;
    Recyclerview_Adapter adapter;
    RecyclerView recyclerView;
    ArrayList <String> namelist = new ArrayList<>();
    ArrayList <String> numberlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Displayeddata();
        binding.addcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Contacts.class);
                startActivity(intent);
            }
        });
    }
    private void Displayeddata(){
        database = new Database(MainActivity.this);
        Cursor cursor = database.Displayeddata();
        while (cursor.moveToNext()) {
         namelist.add(cursor.getString(1));
         numberlist.add(cursor.getString(2));
        }
        adapter = new Recyclerview_Adapter(MainActivity.this,namelist,numberlist);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}