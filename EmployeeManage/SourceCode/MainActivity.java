package com.sinhvien.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button store, getAll;
    private EditText etName, etPhone, etAddress, etDepart;
    private EmployeeDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new EmployeeDAO(this);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etDepart = (EditText) findViewById(R.id.etDepartment);

        store = (Button) findViewById(R.id.btnStore);
        getAll = (Button) findViewById(R.id.btnGetAll);

        store.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.addEmployee(etName.getText().toString(), etPhone.getText().toString(), etAddress.getText().toString(), etDepart.getText().toString());
                etName.setText("");
                etPhone.setText("");
                etAddress.setText("");
                etDepart.setText("");
                Toast.makeText(MainActivity.this, "Add Employee Info Successfully", Toast.LENGTH_SHORT);
            }
        });

        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent viewAllEmployeeInfo = new Intent(MainActivity.this, GetAllEmployeeInfo.class);
                startActivity(viewAllEmployeeInfo);
            }
        });
    }
}