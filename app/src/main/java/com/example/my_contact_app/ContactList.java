package com.example.my_contact_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactList extends Fragment {

    RecyclerView recyclerView;

    public  static  RecyclerView.Adapter adapter;
    ArrayList<Contacts> contactlist;
    DatabaseHelper myDB;
    public ContactList(){


    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        myDB = new DatabaseHelper(getContext());
        displayContacts();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void displayContacts() {
        contactlist = new ArrayList<>(myDB.getContacts());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

      AdaptorContactList adapter = new AdaptorContactList(getContext(),contactlist,getActivity() );
        recyclerView.setAdapter(adapter);
    }


}