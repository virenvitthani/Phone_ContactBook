package com.example.phone_contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder> {

    MainActivity mainActivity;
    ArrayList<String> namelist;
    ArrayList<String> numberlist;

    public Recyclerview_Adapter(MainActivity mainActivity, ArrayList<String> name, ArrayList<String> number) {
        this.mainActivity = mainActivity;
        this.namelist = name;
        this.numberlist = number;
    }

    @NonNull
    @Override
    public Recyclerview_Adapter.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.contact_list_item,parent,false);
        RecyclerviewHolder holder = new RecyclerviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_Adapter.RecyclerviewHolder holder, int position) {
        holder.txt1.setText(""+namelist.get(position));
        holder.txt2.setText(""+numberlist.get(position));
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {
       TextView txt1,txt2;
        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.contact_item_name);
            txt2 = itemView.findViewById(R.id.contact_item_number);
        }
    }
}
