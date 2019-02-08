package com.example.admin.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int V = 3;
    private static volatile DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, "users.db", null, V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createProfileTable(db);
    }

    private void createProfileTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS [users] (\n" +
                " [id] INTEGER NOT NULL UNIQUE, " +
                " [first_name] TEXT, " +
                " [last_name] TEXT, " +
                " [middle_name] TEXT, " +
                " [gender] INTEGER, " +
                " [phone_number] TEXT, " +
                " [email] TEXT," +
                " CONSTRAINT [] PRIMARY KEY([id]) ON CONFLICT REPLACE);";
        db.execSQL(sql);
    }

    public static List<Profile> getProfiles(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {"id", "first_name", "last_name", "middle_name", "gender", "phone_number", "email"};

        Cursor cursor = db.query("users", columns, null, null, null, null, null);

        List<Profile> profiles = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);

            Profile profile = new Profile(id);
            profile.setFirstName(firstName);
            profile.setLastName(lastName);

            profiles.add(profile);
        }

        cursor.close();

        return profiles;
    }

    public static void delete(Context context, int id){
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("users", "id = " + id, null);
    }

    public static void update(Context context, int id, String fisrtName, String lastName){
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", fisrtName);
        contentValues.put("last_name", lastName);

        db.update("users", contentValues, "id = " + id, null);
    }

    public static void insert(Context context, Profile profile){
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", profile.getId());
        contentValues.put("first_name", profile.getFirstName());
        contentValues.put("last_name", profile.getLastName());
        contentValues.put("middle_name", profile.getMiddleName());
        contentValues.put("gender", profile.getGenderId());
        contentValues.put("phone_number", profile.getPhoneNumber());
        contentValues.put("email", profile.getEmail());

        db.insert("users", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS [users]");
        onCreate(db);
    }
}
