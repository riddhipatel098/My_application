package com.example.my_contact_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptorContactList extends RecyclerView.Adapter<AdaptorContactList.HomeViewHolder> {

    Context context;
    ArrayList<Contacts> contactslist;
    RecyclerView show_contacts;
    Activity activity;

    final View.OnClickListener onClickListener=new MyOnClickListener();

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        LinearLayout recyclerClickContact;
        TextView character,contactName;
        ImageView edit;

        public HomeViewHolder(@NonNull View i) {
            super(i);

            character = i.findViewById(R.id.character);
            contactName = i.findViewById(R.id.contactName);
            edit=i.findViewById(R.id.Edit);
            recyclerClickContact = i.findViewById(R.id.recyclerClickContact);
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(, Edit_contact.class);
//
//                }
//            });
        }
    }
public  AdaptorContactList(Context context, ArrayList<Contacts> contactslist, Activity activity)
{
    this.context=context;
    this.contactslist=contactslist;
    this.activity=activity;
}
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact_list, parent, false);


        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int i) {
       // Contacts contacts=contactslist.get(i);
        holder.character.setText(contactslist.get(i).getName());
        holder.contactName.setText(contactslist.get(i).getNumber());

        holder.recyclerClickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return contactslist.size();    }


    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
        int position=show_contacts.getChildLayoutPosition(v);
        String item=contactslist.get(position).getName();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();

        }
    }
}

