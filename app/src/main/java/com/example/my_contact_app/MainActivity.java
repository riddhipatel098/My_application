package com.example.my_contact_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDB;
private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private AddNewContact addnewcontact;
    private ContactList contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        frameLayout = findViewById(R.id.frameLayout);
        addnewcontact = new AddNewContact();
        contactList = new ContactList();
       // editContact=new EditContact();
        setFragment(contactList);
        myDB=new DatabaseHelper(this);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home :
                        setFragment(addnewcontact);
                        return true;
                    case R.id.nav_success :
                        setFragment(contactList);
                        return true;

                    default :
                        return false;
                }
            }
        });
    }

    // Bottom navigation Activities


    //Set Fragment
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }

}