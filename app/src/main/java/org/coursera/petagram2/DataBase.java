package org.coursera.petagram2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by rodomualdo on 10/06/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
    }

    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE " + DataBaseConstants.TABLE_NAME +
                "(" +
                DataBaseConstants.DB_PET_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.DB_PET_NAME + " TEXT, " +
                DataBaseConstants.DB_PET_RATE + " INTEGER, " +
                DataBaseConstants.DB_PET_IMAGE + " INTEGER" +
                ")";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Pet> getAllData(){
        ArrayList<Pet> pets = new ArrayList<>();

        String query = "SELECT * FROM" + DataBaseConstants.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Pet pet = new Pet();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setRate(cursor.getInt(2));
            pet.setFoto(cursor.getInt(3));
            pets.add(pet);
        }

        db.close();
        return pets;
    }

    public void incrementLikesPet(int id){
        String query = "UPDATE " + DataBaseConstants.TABLE_NAME +
                " SET " + DataBaseConstants.DB_PET_RATE + "=" + DataBaseConstants.DB_PET_RATE
                + "+1 " +
                "WHERE " + DataBaseConstants.DB_PET_ID + "=" + Integer.toString(id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
