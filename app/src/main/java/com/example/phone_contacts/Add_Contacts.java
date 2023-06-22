package com.example.phone_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.phone_contacts.databinding.ActivityAddContactsBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Add_Contacts extends AppCompatActivity {

    ActivityAddContactsBinding binding;
    Database database;
    String imageview,imagepath;
    private static final int CAMERA_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=new Database(Add_Contacts.this);
        binding = ActivityAddContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String btn = getIntent().getStringExtra("button");


        if(btn.equals("add"))
        {
            binding.add.setVisibility(View.VISIBLE);
        }
        else if(btn.equals("update"))
        {
            binding.update.setVisibility(View.VISIBLE);
        }
        binding.addcontactsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagepath = imagepath+"/"+imagepath;
                Log.d("TTT", "onClick: new imagepath"+imagepath);
                database.addContacts(binding.contactsName.getText().toString(),binding.contactsNumber.getText().toString(),imagepath);
                Intent intent = new Intent(Add_Contacts.this,MainActivity.class);
                startActivity(intent);
            }
        });

        int id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        binding.contactsName.setText(name);
        binding.contactsNumber.setText(number);
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.updatecontacts(id,binding.contactsName.getText().toString(),
                        binding.contactsNumber.getText().toString());
                Intent intent = new Intent(Add_Contacts.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            binding.addcontactsImg.setImageBitmap(bitmap);
            imagepath=saveToInternalStorage(bitmap);
            Log.d("TTT", "onActivityResult: path="+imagepath);
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path,ImageView imageView)
    {

        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}