package com.example.my_contact_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "Contacts_db";
    public final static String TABLE_NAME = "contacts";
    public final static String COL_ID = "ID";
    public final static String COL_NAME = "NAME";
    public final static String COL_NUMBER = "NUMBER";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_NAME + " TEXT,"
                    + COL_NUMBER + " TEXT"
                    + ")";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //insert Data
    public boolean InsertData(String name,String number)
    {
       SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_NUMBER,number);
       long result= db.insert(TABLE_NAME,null,contentValues);
       if(result==-1)
       {
           return  false;
       }
       else
       {
           return  true;
       }
    }

    //get the all notes
    public ArrayList<Contacts> getContacts() {
        ArrayList<Contacts> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts contacts = new Contacts();
                contacts.setId(cursor.getInt(0));
                contacts.setName(cursor.getString(1));
                contacts.setNumber(cursor.getString(2));
                arrayList.add(contacts);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //Update Contact
    public boolean updateData(String id,String name,String Contact )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_NUMBER,Contact);
        db.update(TABLE_NAME,contentValues,"id=?",new String[] {id});
        return true;
    }

}
