package com.example.my_contact_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends Fragment {

    EditText name,number;
    Button button_save;
    DatabaseHelper myDB;

    public void UpdateData(long id)
    {

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDB.updateData(String.valueOf(id),name.getText().toString(),number.getText().toString());
                //Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                if(isUpdated==true)
                {
                    Toast.makeText(getContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
                    System.out.println(name.getText().toString());
                    System.out.println(number.getText().toString());

                }
                else
                {
                    Toast.makeText(getContext(), " Sorry!! Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static EditContact newInstance(String param1, String param2) {
        EditContact fragment = new EditContact();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Long id = getArguments().getLong("id");
        String C_name=getArguments().getString("name");
        String C_number=getArguments().getString("number");
        Toast.makeText(getActivity(), id.toString(), Toast.LENGTH_SHORT).show();
        final View view =  inflater.inflate(R.layout.fragment_add_new_contact, container, false);
        myDB=new DatabaseHelper(getContext());
        name= name = view.findViewById(R.id.editText_name);
        name.setText(C_name);
        number = view.findViewById(R.id.editText_number);
        number.setText(C_number);
        button_save = view.findViewById(R.id.button_save);
        UpdateData(id);
        return  view;
    }
}