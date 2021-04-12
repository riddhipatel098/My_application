package com.example.my_contact_app;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContact extends Fragment {
    EditText name,number;
    Button  button_save;
    DatabaseHelper myDB;
    ContactList contactList;

    public void AddNewContact()
    {
            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
               boolean isInserted = myDB.InsertData(name.getText().toString(),number.getText().toString());
               if(isInserted==true)
               {
                   Toast.makeText(getContext(), "Inserted Successfull", Toast.LENGTH_SHORT).show();
                   name.setText("");
                   number.setText("");
                   System.out.println(name.getText().toString());
                   System.out.println(number.getText().toString());
                    contactList=new ContactList();
                   FragmentManager fragmentManager = getFragmentManager();
                   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                   fragmentTransaction.replace(R.id.frameLayout,contactList);
                   fragmentTransaction.commit();
               }
               else
               {
                   Toast.makeText(getContext(), " Sorry!! Failed", Toast.LENGTH_SHORT).show();
               }
                }
            });
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_add_new_contact, container, false);
        myDB=new DatabaseHelper(getContext());
        name = view.findViewById(R.id.editText_name);
        number = view.findViewById(R.id.editText_number);
        button_save = view.findViewById(R.id.button_save);
        AddNewContact();
        return  view;
    }
}