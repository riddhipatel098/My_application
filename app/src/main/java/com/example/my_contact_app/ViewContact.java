package com.example.my_contact_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewContact extends AppCompatActivity {
    RecyclerView contacts;
    AdaptorContactList contactAdpter;
    SQLiteDatabase db;
    RecyclerView.LayoutManager layoutManager;
   List<Contacts> contactlist=new ArrayList<>();
   DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

    }
}