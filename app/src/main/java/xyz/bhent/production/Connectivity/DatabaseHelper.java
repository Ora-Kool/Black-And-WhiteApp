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
    public static final String RESERVATION_ITEM = "items";
    public static final String RESERVATION_ITEM_PRICE = "price";
    private static final String SELECT_ALL = "select * from "+DATABASE_TABLE_NAME;

    private static final String CREATE =
            "create table "+DATABASE_TABLE_NAME+"("+RESERVATION_COLUMN_ID+
                    " integer primary key, "+RESERVATION_ITEM+
                    " text, "+RESERVATION_ITEM_PRICE+" text)";

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
    public boolean insertItems(String item, String price){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESERVATION_ITEM, item);
        contentValues.put(RESERVATION_ITEM_PRICE, price);

        writableDatabase.insert(DATABASE_TABLE_NAME, null, contentValues);

        return true;
    }

    //GETTING ALL THE DATA FROM THE DATABASE
    public HashMap<String, String> getAllItems(){
        hashMap = new HashMap<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(SELECT_ALL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            hashMap.put(cursor.getString(cursor.getColumnIndex(RESERVATION_ITEM_PRICE)),
                    cursor.getString(cursor.getColumnIndex(RESERVATION_ITEM)));
            cursor.moveToNext();
        }

        return hashMap;
    }

    //REMOVING A SELECTED ITEM
    public boolean removeItem(String itemName){
        SQLiteDatabase writabeDatabase = this.getWritableDatabase();
        writabeDatabase.delete(DATABASE_TABLE_NAME, RESERVATION_ITEM+" = ? ", new String[]{itemName});
        return true;
    }
}
