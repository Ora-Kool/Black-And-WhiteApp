package xyz.bhent.production.Connectivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 7/26/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "blacknwhite.db";
    public static final String DATABASE_TABLE_NAME = "reservations";
    public static final String RESERVATION_COLUMN_ID = "id";
    public static final String RESERVATION_ITEM_NAME = "items";
    public static final String RESERVATION_ITEM_QUANTITY = "quantity";
    public static final String RESERVATION_ITEM_PRICE = "price";
    private static final String SELECT_ALL = "select * from "+DATABASE_TABLE_NAME;

    private static final String CREATE =
            "create table "+DATABASE_TABLE_NAME+"("+RESERVATION_COLUMN_ID+
                    " integer primary key, "+RESERVATION_ITEM_NAME+
                    " text, "+RESERVATION_ITEM_QUANTITY+" text, "+RESERVATION_ITEM_PRICE+ " text)";

    private HashMap<String, String> hashMap;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE_NAME);
    }

    //INSERTING DATA INTO THE DATABASE
    public boolean insertItems(String itemName, String itemQuantity, String itemPrice){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESERVATION_ITEM_NAME, itemName);
        contentValues.put(RESERVATION_ITEM_QUANTITY, itemQuantity);
        contentValues.put(RESERVATION_ITEM_PRICE, itemPrice);

        writableDatabase.insert(DATABASE_TABLE_NAME, null, contentValues);

        return true;
    }

    //GETTING ALL THE DATA FROM THE DATABASE
    public HashMap<String, String> getAllItems(){
        hashMap = new HashMap<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(SELECT_ALL, null);
        cursor.moveToFirst();
        String itemName;
        String price;
        String quantity;
        while (cursor.isAfterLast() == false){


            hashMap.put(cursor.getString(cursor.getColumnIndex(RESERVATION_ITEM_NAME))+" = "+
                    cursor.getString(cursor.getColumnIndex(RESERVATION_ITEM_QUANTITY)),
                    cursor.getString(cursor.getColumnIndex(RESERVATION_ITEM_PRICE)));

            cursor.moveToNext();
        }

        return hashMap;
    }

    //REMOVING A SELECTED ITEM
    public boolean removeItem(String itemName){
        SQLiteDatabase writeDatabase = this.getWritableDatabase();
        writeDatabase.delete(DATABASE_TABLE_NAME, RESERVATION_ITEM_NAME+" = ? ", new String[]{itemName});
        return true;
    }
}
