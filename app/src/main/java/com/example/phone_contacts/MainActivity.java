package com.example.phone_contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import com.example.phone_contacts.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    RecyclerView recyclerView;
    ActivityMainBinding binding;
    SwipeRefreshLayout swipeRefreshLayout;
    Recyclerview_Adapter adapter;
    ArrayList <Contact_Model> contactlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.recyclerview);
        Displayeddata();

        binding.addcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Contacts.class);
                intent.putExtra("button","add");
                startActivity(intent);
            }
        });
    }

    private void Displayeddata() {
        database = new Database(MainActivity.this);
        Cursor cursor = database.Displayed();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            Contact_Model contactModel = new Contact_Model(id,name,number);
            contactlist.add(contactModel);
        }
        adapter = new Recyclerview_Adapter(MainActivity.this,contactlist);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}