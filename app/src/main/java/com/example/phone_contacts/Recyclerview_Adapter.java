package com.example.phone_contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder> {

    Context context;
    ArrayList<Contact_Model> contactlist;

    public Recyclerview_Adapter(MainActivity mainActivity, ArrayList<Contact_Model> contactlist) {
        this.context = mainActivity;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public Recyclerview_Adapter.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item,parent,false);
        RecyclerviewHolder holder = new RecyclerviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_Adapter.RecyclerviewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt1.setText(""+contactlist.get(position).getName());
        holder.txt2.setText(""+contactlist.get(position).getNumber());
        holder.more_vert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.more_vert);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Database database = new Database(context);
                        if(menuItem.getItemId()==R.id.update)
                        {
                            Intent intent = new Intent(context,Add_Contacts.class);
                            intent.putExtra("id",contactlist.get(position).getId());
                            intent.putExtra("name",contactlist.get(position).getName());
                            intent.putExtra("number",contactlist.get(position).getNumber());
                            intent.putExtra("button","update");
                            context.startActivity(intent);
                            Toast.makeText(context, "item updated", Toast.LENGTH_SHORT).show();
                        }
                        if(menuItem.getItemId()==R.id.delete)
                        {
                            Toast.makeText(context, "item deleted", Toast.LENGTH_SHORT).show();
                            database.deletecontacts(contactlist.get(position).getId());
                            contactlist.remove(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
    return contactlist.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2;
        ImageButton more_vert;
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.contact_item_name);
            txt2 = itemView.findViewById(R.id.contact_item_number);
            more_vert = itemView.findViewById(R.id.more_vert);
        }
    }
}
