package com.rahul.servicewale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 7/14/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "noOfItems";

    // Contacts table name
    public static final String TABLE_ITEMS = "Items";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NO_OF_ITEMS = "noOfItems";
    private static final String KEY_NAME_OF_ITEMS = "nameOfItems";
    private static final String KEY_RATE_OF_ITEMS = "rateOfItems";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME_OF_ITEMS + " VARCHAR(50), "
                + KEY_RATE_OF_ITEMS + " INTEGER, "
                + KEY_NO_OF_ITEMS + " INTEGER " + ");";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);


    }

    public void addItems(String name,int rate, int noOfItems){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME_OF_ITEMS,name);
        contentValues.put(KEY_RATE_OF_ITEMS,rate);
        contentValues.put(KEY_NO_OF_ITEMS,noOfItems);
        db.insert(TABLE_ITEMS,null,contentValues);
        db.close();
    }


    public ExternalData getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS,new String[]
                {KEY_ID,KEY_NO_OF_ITEMS,KEY_NAME_OF_ITEMS,KEY_RATE_OF_ITEMS},
                KEY_ID + "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        ExternalData dataList = new ExternalData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
          return dataList;
    }
    public List<ExternalData> getAllContacts() {
        List<ExternalData> dataLists1 = new ArrayList<ExternalData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ExternalData dataList = new ExternalData();
                dataList.setId(Integer.parseInt(cursor.getString(0)));
                dataList.setTitle(cursor.getString(1));
                dataList.setDes(Integer.parseInt(cursor.getString(2)));
                dataList.setNoOfItems(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                dataLists1.add(dataList);
            } while (cursor.moveToNext());
        }

        // return list
        return dataLists1;
    }

    // Updating single contact
    public int updateContact(int position, int item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NO_OF_ITEMS, item);

        // updating row
        return db.update(TABLE_ITEMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(position) });
    }

    // Deleting single contact
   // public void deleteContact(Contact contact) {
     //   SQLiteDatabase db = this.getWritableDatabase();
       // db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
         //       new String[] { String.valueOf(contact.getID()) });
        //db.close();
    //}


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
