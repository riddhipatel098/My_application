package com.example.my_contact_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactList extends Fragment implements OnClickListener {

    RecyclerView recyclerView;

    public  static  RecyclerView.Adapter adapter;
    ArrayList<Contacts> contactlist;
    DatabaseHelper myDB;
    ContactList contactList;

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

      AdaptorContactList adapter = new AdaptorContactList(getContext(),contactlist,getActivity(),ContactList.this );
        recyclerView.setAdapter(adapter);
    }
    public void loadFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment,"edit");
        fragmentTransaction.detach(fragment);
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onclick(long id,String name,String number) {
        Toast.makeText(getActivity(), "Pressed", Toast.LENGTH_SHORT).show();
        EditContact editContact=new EditContact();
        Bundle bundle=new Bundle();
        bundle.putLong("id",id);
        bundle.putString("name",name);
        bundle.putString("number",number);
        editContact.setArguments(bundle);
        loadFragment(editContact);
    }

}