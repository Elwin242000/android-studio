package com.sinhvien.midtermexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static String DATABASE_NAME = "employee_database";
    private static final int DATABASE_VERSION = 1;
    protected static final String TABLE_EMPLOYEES = "employees";
    protected static final String TABLE_EMPLOYEE_DEPART = "employee_depart";
    protected static final String KEY_ID = "id";
    protected static final String KEY_NAME = "name";
    protected static final String KEY_PHONE = "phone";
    protected static final String KEY_ADDRESS = "address";
    protected static final String KEY_DEPART = "department";

    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE "
            + TABLE_EMPLOYEES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT,"
            + KEY_PHONE + " TEXT,"
            + KEY_ADDRESS +" TEXT);";

    private static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE "
            + TABLE_EMPLOYEE_DEPART + "("
            + KEY_ID + " INTEGER,"
            + KEY_DEPART + " TEXT);";

    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        db.execSQL(CREATE_TABLE_DEPARTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EMPLOYEES + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EMPLOYEE_DEPART + "'");
        onCreate(db);
    }
}
