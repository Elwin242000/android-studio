package com.sinhvien.midtermexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EmployeeDAO extends DatabaseHelper
{
    public EmployeeDAO(Context context)
    {
        super(context);
    }

    public void addEmployee(String name, String phone, String address, String department)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Them info vao table employees
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_PHONE, phone);
        cv.put(KEY_ADDRESS, address);

        long id = db.insertWithOnConflict(TABLE_EMPLOYEES, null, cv, SQLiteDatabase.CONFLICT_IGNORE);

        //Them info vào table employee_departments
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_DEPART, department);
        db.insert(TABLE_EMPLOYEE_DEPART, null, values);
    }

    public ArrayList<EmployeeModel> GetAllEmployeeInfo()
    {
        ArrayList<EmployeeModel> employeeInfos = new ArrayList<EmployeeModel>();

        String query = "SELECT * FROM " + TABLE_EMPLOYEES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst())
        {
            do
            {
                EmployeeModel em = new EmployeeModel();
                em.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                em.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                em.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                em.setAddress(c.getString(c.getColumnIndex(KEY_ADDRESS)));

                String selectQueryDepart = "SELECT * FROM " + TABLE_EMPLOYEE_DEPART + " WHERE " + KEY_ID + " = " + em.getId();

                Cursor cDepart =db.rawQuery(selectQueryDepart, null);

                if (cDepart.moveToFirst())
                {
                    do
                    {
                        em.setDepartment(cDepart.getString(cDepart.getColumnIndex(KEY_DEPART)));
                    }
                    while (cDepart.moveToNext());
                }

                employeeInfos.add(em);
            }
            while (c.moveToNext());
        }
        return employeeInfos;
    }

    public void updateUser(int id, String name, String phone, String address, String depart)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        db.update(TABLE_EMPLOYEES, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        // updating hobby in users_hobby table
        ContentValues valuesHobby = new ContentValues();
        valuesHobby.put(KEY_DEPART, depart);
        db.update(TABLE_EMPLOYEE_DEPART, valuesHobby, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id)
    {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_EMPLOYEES, KEY_ID + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_EMPLOYEE_DEPART, KEY_ID + " = ?", new String[]{String.valueOf(id)});

    }
}
